def next(self, virtChipIndex=0):
    if type(virtChipIndex) == str:
        virtChipIndex = int(virtChipIndex[1:4]) - 1
    bits = bitstring.BitArray()

    noiseValues = [self.noise() for i in range(self.nb+1)]

    for i in range(0, self.nb):
        lhs = self.realValues[virtChipIndex][i] + noiseValues[i]
        rhs = self.realValues[virtChipIndex][i+1] + noiseValues[i+1]
        bits.append(bitstring.Bits(bool=(lhs < rhs)))

    return bits

def NoiseWorker(argTuple):
    chipIndex, iterations = argTuple
    mySim = Simulator()
    mySim.setup()
    enrollment = mySim.next(chipIndex)
    noise_hds = [hd(enrollment, mySim.next(chipIndex)) for measIndex in range(iterations)]
    return float(sum(noise_hds)) / iterations / mySim.nb
