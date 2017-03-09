import csv
import cfg
from random import SystemRandom
from puf import Challenge
from database import database

device = database['1234']

rnd = SystemRandom()
with open('outputs/1234.out.csv', 'w') as out:
    csvout = csv.writer(out)
    for _ in range(2048):
        i = rnd.getrandbits(cfg.RSP_LEN)
        c = Challenge(('{0:0' + str(cfg.RSP_LEN) + 'b}')
                      .format(i))
        r = device.f(c)
        csvout.writerow(list(c.to01()) + [r])
