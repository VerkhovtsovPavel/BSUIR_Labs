using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Lab3 {
    public static class Crypto {
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
    }
}
