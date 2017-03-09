from random import SystemRandom, Random
from puf import Challenge, Response
from database import database
import cfg
import sys

from flask import Flask, request, session, redirect, url_for, jsonify

verifiers = {}

app = Flask(__name__)
app.secret_key = hex(SystemRandom().getrandbits(256))


class UnknownDeviceError(Exception):
    def __init__(self, deviceid):
        self.deviceid = deviceid


class Verifier():

    def __init__(self, deviceid):
        self._model = self._load_model(deviceid)
        self._nonce = format(SystemRandom().getrandbits(cfg.NONCE_SIZE), 'x')
        self._prng = None

    def _load_model(self, deviceid):
        try:
            return database[deviceid]
        except KeyError:
            raise UnknownDeviceError(deviceid)

    def get_response(self):
        # Generate challenges
        cs = [Challenge(('{0:0' + str(self._model.width) + 'b}')
                        .format(self._prng.getrandbits(self._model.width)))
              for _ in range(cfg.RSP_LEN)]

        # Get the full response string
        return Response([self._model.f(c) for c in cs])

    @property
    def nonce(self):
        return self._nonce

    def seed(self, other_nonce):
        self._prng = Random(self.nonce + other_nonce)


def wrong_step():
    return 'This action is invalid for current authentication step {0}' \
           .format(session.get('auth_step'))


@app.route('/initiate', methods=['POST'])
def initiate():
    try:
        deviceid = request.form['deviceid']
        verifier = Verifier(deviceid)
    except UnknownDeviceError as ude:
        return jsonify(msg='Device ID ' + ude.deviceid +
                           ' not found in the database.'), 401
    verifiers[deviceid] = verifier
    session['deviceid'] = deviceid
    session['auth_step'] = 'nonce_exchange'
    return jsonify(msg='Authentication protocol for device {0} initiated'
                       .format(deviceid))


@app.route('/nonce', methods=['GET', 'POST'])
def nonce():
    if session.get('auth_step') != 'nonce_exchange':
        return jsonify(msg=wrong_step()), 401
    else:
        v = verifiers[session.get('deviceid')]
        if request.method == 'GET':
            return jsonify(nonce=v.nonce)
        elif request.method == 'POST':
            v.seed(request.form['nonce'])
            session['auth_step'] = 'challenge'
            return jsonify(msg='Seed created, ready for challenge rounds')


@app.route('/substring', methods=['POST'])
def substring():
    if session.get('auth_step') != 'challenge':
        return jsonify(msg=wrong_step()), 401
    substring = Response(request.form['substring'])
    print(substring)
    response = verifiers[session.get('deviceid')].get_response()
    print(response)
    result, mindiff = response.fuzzysearch(substring, cfg.THRESHOLD)
    if result:
        return jsonify(msg='Authorized!'), 200
    else:
        return jsonify(msg='Not authorized.'), 401
