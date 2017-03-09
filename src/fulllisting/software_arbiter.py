from .device import Device
from shiba.puf import Challenge, Response


class SoftwareArbiter(Device):
    def __init__(self, deviceid, deltas):
        super().__init__(deviceid)
        self.deltas = deltas
        self._width = len(deltas) - 1

    def f(self, c):
        if len(c) != len(self.deltas) - 1:
            raise ValueError("Wrong number of challenge bits (expected {0}, "
                             "got {1})".format(len(self.deltas) - 1, len(c)))
        r = self.deltas[-1]
        for i in range(len(c)):
            if c[i:].count() % 2 == 1:
                r -= self.deltas[i]
            else:
                r += self.deltas[i]
        return r > 0

