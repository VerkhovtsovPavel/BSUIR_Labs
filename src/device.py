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
