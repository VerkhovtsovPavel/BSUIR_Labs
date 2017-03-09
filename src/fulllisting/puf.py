from bitarray import bitarray


class Challenge(bitarray):
    pass


class Response(bitarray):

    def subseq(self, idx, length):
        return self[idx:min(idx+length, len(self))] + \
               self[0:max(0, idx+length-len(self))]

    def subseqs(self, length):
        for i in range(len(self)):
            yield self.subseq(i, length)

    def fuzzysearch(self, needle, threshold):
        mindiff = threshold + 1
        result = None
        for subseq in self.subseqs(len(needle)):
            diff = (subseq ^ needle).count()
            if diff < mindiff:
                mindiff = diff
                result = subseq
                if diff == 0:
                    break
        return (result, mindiff)
