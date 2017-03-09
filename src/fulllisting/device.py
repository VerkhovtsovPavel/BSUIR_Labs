from time import sleep
from random import SystemRandom
import numpy as np


class Device(object):

    def __init__(self, deviceid):
        self._id = deviceid

    @property
    def id(self):
        return self._id

    @property
    def width(self):
        return self._width

    def f(self, c):
        raise NotImplementedError()

    def f_sub(self, c, idx, l):
        raise NotImplementedError()



def gen_deltas(count, minmax=16384):
    r = SystemRandom()

    def _next():
        sleep(1.0 / r.randint(25, 250))
        return r.randint(-minmax, minmax)

    deltas = np.array([_next() for _ in range(count)])
    return (deltas - np.mean(deltas)) / np.std(deltas)
