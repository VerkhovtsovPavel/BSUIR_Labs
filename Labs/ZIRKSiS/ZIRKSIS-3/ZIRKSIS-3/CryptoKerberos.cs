using System;

namespace Lab3 {
    public static class CryptoKerberos {
        public static byte[] HashData(byte[] data) {
            var hProv = IntPtr.Zero;
            var hHash = IntPtr.Zero;

            if (!CryptoWrapper.CryptAcquireContext(ref hProv, null, null, CryptoWrapper.PROV_RSA_FULL, CryptoWrapper.CRYPT_VERIFYCONTEXT))
                return data;
            if (!CryptoWrapper.CryptCreateHash(hProv, CryptoWrapper.CALG_SHA1, IntPtr.Zero, 0, ref hHash))
                return data;

            if (!CryptoWrapper.CryptHashData(hHash, data, data.Length, 0))
                return data;

            var hash = GetHashFromProv(hHash);
            CryptoWrapper.CryptReleaseContext(hProv, 0);
            return hash;
        }

        private static byte[] GetHashFromProv(IntPtr hHash) {
            var hashLen = 0;
            if (CryptoWrapper.CryptGetHashParam(hHash, CryptoWrapper.HP_HASHVAL, null, ref hashLen, 0)) {
                var hash = new byte[hashLen];
                if (CryptoWrapper.CryptGetHashParam(hHash, CryptoWrapper.HP_HASHVAL, hash, ref hashLen, 0)) {
                    return hash;
                }
            }
            return new byte[0];
        }

        private static IntPtr GetKey(byte[] password) {
            IntPtr hProv = IntPtr.Zero;
            IntPtr hKey = IntPtr.Zero;
            IntPtr hHash = IntPtr.Zero;
            if (CryptoWrapper.CryptAcquireContext(ref hProv, null, null, CryptoWrapper.PROV_RSA_FULL, CryptoWrapper.CRYPT_VERIFYCONTEXT))
                if (CryptoWrapper.CryptCreateHash(hProv, CryptoWrapper.CALG_MD5, hKey, 0, ref hHash))
                    if (CryptoWrapper.CryptHashData(hHash, password, password.Length, 0))
                        if (CryptoWrapper.CryptDeriveKey(hProv, CryptoWrapper.CALG_3DES_112, hHash, 0, ref hKey)) {
                            CryptoWrapper.CryptDestroyHash(hHash);
                            return hKey;
                        }

            return hKey;
        }

        private static byte[] ScaleData(byte[] data, int newLength) {
            var newData = new byte[newLength];
            var minLength = Math.Min(data.Length, newLength);
            for (int i = 0; i < minLength; i++) {
                newData[i] = data[i];
            }
            return newData;
        }

        public static byte[] EncryptData(byte[] data, byte[] password) {
            var key = GetKey(password);
            var dwCount = data.Length;
            var scaleData = ScaleData(data, 1008);
            var d = CryptoWrapper.CryptEncrypt(key, IntPtr.Zero, true, 0, scaleData, ref dwCount, 1008);
            CryptoWrapper.CryptDestroyKey(key);
            return ScaleData(scaleData, dwCount);
        }



        public static byte[] DecryptData(byte[] data, byte[] password) {
            var key = GetKey(password);
            var dwCount = data.Length;
            var scaleData = ScaleData(data, 1008);
            var d = CryptoWrapper.CryptDecrypt(key, IntPtr.Zero, true, 0, scaleData, ref dwCount);
            CryptoWrapper.CryptDestroyKey(key);
            return ScaleData(scaleData, dwCount);
        }
    }
}
