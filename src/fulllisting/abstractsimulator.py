class AbstractSimulator(object):

    def __init__(self, nb=1024):
        self.nb = nb
        self.bit_flips = None
        if (not os.path.isdir(os.path.dirname(self.setupFile))):
            os.makedirs(os.path.dirname(self.setupFile))

    def setup(self, param_mu=10, param_sd=0.00001, noise_mu=0, noise_sd=0.025, numVirtChips=32):
        self.params = {'param_mu':param_mu, 'param_sd':param_sd, 'noise_mu':noise_mu, 'noise_sd':noise_sd}
        if (os.path.isfile(self.setupFile)):
            self.loadFromFile()
        else:
            self.generateSetup()
        print "Done."

    def loadFromFile(self):
        print "Loading simulator state... ",
        mytree = etree.parse(self.setupFile)
        myroot = mytree.getroot()

        self.realValues = []
        self.chipNames = []
        for child in myroot:
            if (child.tag == 'setup'):
                self.params = dict(zip(child.attrib.keys(), [float(val) for val in child.attrib.values()]))
            elif (child.tag == 'virtchip'):
                self.chipNames.append(child.attrib['name'])
                for chipchild in child:
                    if (chipchild.tag == 'realvalues'):
                        chipRealValues = []
                        for value in chipchild:
                            chipRealValues.append(float(value.text.strip()))
                        self.realValues.append(chipRealValues)
        self.numVirtChips = len(self.realValues)
        self.numElements = len(self.realValues[0])

    def generateSetup(self):
        raise NotImplemented()

    def close(self):
        return True

    def getChipName(self, index):
        return 'v%03d' % (index+1)

    def getSetupStr(self):
        return "P_mu=%1.1f, P_sd=%1.1f, E_mu=%1.3f, E_sd=%1.3f" % (
                        self.params['param_mu'], self.params['param_sd'], self.params['noise_mu'], self.params['noise_sd'])

    def noise(self):
        return random.normalvariate(self.params['noise_mu'], self.params['noise_sd'])

    def next(self, virtChipIndex=0):
        raise NotImplemented()




def NoiseWorker(argTuple):
    chipIndex, iterations = argTuple
    mySim = AbstractSimulator()
    mySim.setup()
    enrollment = mySim.next(chipIndex)
    noise_hds = [hd(enrollment, mySim.next(chipIndex)) for measIndex in range(iterations)]
    print "Chip v%03d (of %d): %d / %d = %0.3f %%" % (chipIndex+1, mySim.numVirtChips, sum(noise_hds), iterations * mySim.nb, (100 * float(sum(noise_hds)) / iterations / mySim.nb))
    return float(sum(noise_hds)) / iterations / mySim.nb

if __name__=="__main__":
    import multiprocessing, itertools
    print "Running self-test"
    mySim = AbstractSimulator()
    mySim.setup() # setup with defaults
    p = multiprocessing.Pool(multiprocessing.cpu_count())
    argIter = itertools.izip(range(mySim.numVirtChips), itertools.repeat(2 ** 6))
    results = p.map(NoiseWorker, argIter)

    print "Average noise Hamming distance: %f" % (sum(results) / mySim.numVirtChips)
    print "Test done"

