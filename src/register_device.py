import sys
from prover import Prover
from device import RealDevice

if __name__ == '__main__':
    if len(sys.argv >= 1):
        server_url = sys.argv[1]
    else:
        server_url = 'http://localhost:5000'
    device_id = sys.argv[2]
    inport = sys.argv[3]
    outport = sys.argv[4]
    device = RealDevice(inport, outport)
    prover = Prover(device, server_url)
    prover.register()
