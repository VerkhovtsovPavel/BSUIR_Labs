from random import SystemRandom
from random import Random
from puf import Challenge


class TRNG():
    def __init__(self):
        self._rng = SystemRandom()

    def make_nonce(self, size):
        noncefmt = '{0:0' + str(size) / 4 + 'x}'
        nonce = self._rng.getrandbits(size)
        return noncefmt.format(nonce)

    def choose_index(max):
        SystemRandom().randint(1, max)


class PRNG():
    def __init__(self, seed):
        self._rng = Random(seed)

    def make_challenge(self, size):
        cfmt = '{0:0' + str(size) + 'b}'
        c = self._rng.getrandbits(size)
        return Challenge(cfmt.format(c))
