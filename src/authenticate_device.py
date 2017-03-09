import sys
from database import database
from prover import Prover

if __name__ == '__main__':
    if len(sys.argv >= 1):
        device_id = sys.argv[1]
    else:
        print('No device id provided')
    if len(sys.argv >= 2):
        server_url = sys.argv[2]
    else:
        server_url = 'http://localhost:5000'
    device = database.get(device_id)
    if not device:
        print('No device with id {0} found in the database'.format(device_id))
    prover = Prover(device, server_url)
    prover.authenticate()
