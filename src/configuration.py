import os
import ConfigParser


class Configuration(dict):

    keys = ['nonce_size', 'rsp_len', 'substr_len', 'threshold']

    def __init__(self, *args, **kwargs):
        self.update(*args, **kwargs)

    @staticmethod
    def from_file(ini_file):
        with open(ini_file, 'r') as ini:
            c = ConfigParser.ConfigParser()
            c.readfp(ini)
        return Configuration((k, v) for (k, v) in c.items('protocol')
                             if k in Configuration.keys)

    @staticmethod
    def from_env():
        return Configuration((k, v) for (k, v) in os.environ.items()
                             if k in Configuration.keys)
