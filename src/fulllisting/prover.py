from random import SystemRandom, Random
import requests
from urllib.parse import urljoin
from requests import HTTPError

from protocol import TRNG, PRNG
from puf import Challenge, Response
import cfg as c


class WrongStateError(Exception):
    pass


class Prover():

    def __init__(self, device, verifier, cfg):
        self.v = verifier
        self.device = device
        self.cfg = cfg
        self._s = requests.Session()
        self._trng = TRNG()
        self._prng = None
        self._nonce = self._trng.make_nonce(self.cfg.NONCE_SIZE)

    def call(self, url, data=None):
        if data:
            return self._s.post(urljoin(self.v, url), data=data)
        else:
            return self._s.get(urljoin(self.v, url))

    def seed(self, other_nonce):
        self._prng = PRNG(other_nonce + self._nonce)

    def generate_challenges(self, count):
        if not self._prng:
            raise WrongStateError("PRNG is not yet initialized!")
        for _ in range(count):
            yield self._prng.make_challenge(self.device.width)

    def get_full_response(self, challenges):
        return Response([self.device.f(c) for c in challenges])



    def authenticate(self):
        try:
            # Initiate auth protocol for the device
            rsp = self.call('/initiate', {'deviceid': self.device.id})
            print(rsp.json().get('msg'))

            # Get the server nonce
            rsp = self.call('/nonce')
            nonce = rsp.json().get('nonce')

            # Seed the prng
            self.seed(nonce)

            # Send our own nonce
            rsp = self.call('/nonce', {'nonce': self._nonce})

            # Generate challenges
            cs = self.generate_challenges(self.cfg.RSP_LEN)

            # Get the full response string
            r = self.get_full_response(cs)

            # Choose an index
            idx = self._trng.choose_index(self.cfg.RSP_LEN)
            print(idx)
            substr = r.subseq(idx, self.cfg.SUBSTR_LEN)
            print(substr)
            print(r)
            rsp = self.call('/substring', {'substring': substr.to01()})
            return rsp.json().get('msg')
        except HTTPError as e:
            print(e)
            return




