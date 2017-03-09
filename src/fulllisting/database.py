import os
from devices import SoftwareArbiter

INDIR = 'inputs'

database = {}

for deltas_file in os.listdir(INDIR):
    device_id = deltas_file.replace('.deltas', '')
    with open(os.path.join(INDIR, deltas_file)) as dd:
        deltas = list(map(float, dd.read().split()))
        database[device_id] = SoftwareArbiter(device_id, deltas)
