#!/usr/bin/python

import os
import random
import bitstring
from bitstringutils import *
import xml.etree.ElementTree as etree
from abstractsimulator import AbstractSimulator

class Simulator(AbstractSimulator):
    def setup(self, param_mu=10, param_sd=1, noise_mu=0, noise_sd=0.0225, numVirtChips=2 ** 5):
        self.params = {'param_mu':param_mu, 'param_sd':param_sd, 'noise_mu':noise_mu, 'noise_sd':noise_sd}
        self.numVirtChips = numVirtChips
        if (os.path.isfile(self.setupFile)):
            self.loadFromFile()
        else:
            self.generateSetup()

        print "Done."

    def generateSetup(self):
        print "Generating virtual chips...",
        self.numElements = self.nb + 1
        self.realValues = [[random.normalvariate(self.params['param_mu'], self.params['param_sd']) for index in range(self.numElements)] for chip in range(self.numVirtChips)]
        self.chipNames = [('v%03d' % (index + 1)) for index in range(self.numVirtChips)]

        myxml = etree.Element('xml', attrib={'version':'1.0', 'encoding':'UTF-8'})
        myxml.text = "\n"
        setupEl = etree.SubElement(myxml, 'setup', attrib=dict(zip(self.params.keys(), [str(val) for val in self.params.values()])))
        setupEl.tail = "\n"
        for index in range(self.numVirtChips):
            virtChipEl = etree.SubElement(myxml, 'virtchip', attrib={'name':self.chipNames[index]})
            virtChipEl.text = "\n"
            virtChipEl.tail = "\n"
            valsEl = etree.SubElement(virtChipEl, 'realvalues')
            valsEl.text = "\n"
            valsEl.tail = "\n"
            for param in self.realValues[index]:
                child = etree.SubElement(valsEl, 'value')
                child.text = str(param)
                child.tail = "\n"

        xmlfile = open(self.setupFile, 'w')
        xmlfile.write(etree.tostring(myxml))
        xmlfile.flush()
        xmlfile.close()


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
    # Instead of generating the number of iterations for each process, I could create my own iterator object and pass that in as the argument
    mySim = Simulator()
    mySim.setup()
    enrollment = mySim.next(chipIndex)
    noise_hds = [hd(enrollment, mySim.next(chipIndex)) for measIndex in range(iterations)]
    print "Chip v%03d (of %d): %d / %d = %0.3f %%" % (chipIndex+1, mySim.numVirtChips, sum(noise_hds), iterations * mySim.nb, (100 * float(sum(noise_hds)) / iterations / mySim.nb))
    return float(sum(noise_hds)) / iterations / mySim.nb

if __name__=="__main__":
    import multiprocessing, itertools
    print "Running self-test"
    mySim = Simulator()
    mySim.setup() # setup with defaults
    p = multiprocessing.Pool(multiprocessing.cpu_count())
    argIter = itertools.izip(range(mySim.numVirtChips), itertools.repeat(2 ** 6))
    results = p.map(NoiseWorker, argIter)

    print "Average noise Hamming distance: %f" % (sum(results) / mySim.numVirtChips)
    print "Test done"

