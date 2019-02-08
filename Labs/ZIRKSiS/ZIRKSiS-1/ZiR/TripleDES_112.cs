using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Security.Principal;
using System.Text;

namespace ZIRKSiS1 {
    internal class TripleDES_112 {
        private readonly int _realkeyByteSize = 24;
        private readonly int _keyByteSize = 16;
        private readonly int _blockByteSize = 8;
        private readonly SymmetricAlgorithm _cryptoManager;
        private readonly bool _isUseSessionKey;

        public TripleDES_112() {
            byte[] iv = GetIV();
            byte[] key = SHA256.Create().ComputeHash(iv);
            Array.Resize(ref key, _realkeyByteSize);
            Array.Copy(key, 0, key, _keyByteSize, _realkeyByteSize - _keyByteSize);
            _cryptoManager = new TripleDESCryptoServiceProvider {
                IV = iv,
                Key = key,
            };
            _isUseSessionKey = true;
        }

        public TripleDES_112(string key) {
            byte[] keyhash = SHA256.Create().ComputeHash(Encoding.Default.GetBytes(key));
            Array.Resize(ref keyhash, _realkeyByteSize);
            Array.Copy(keyhash, 0, keyhash, _keyByteSize, _realkeyByteSize - _keyByteSize);
            _cryptoManager = new TripleDESCryptoServiceProvider {
                IV = GetIV(),
                Key = keyhash
            };
            _isUseSessionKey = false;
        }

        private byte[] GetIV() {
            var result = Encoding.Default.GetBytes(WindowsIdentity.GetCurrent().User.Value);
            Array.Resize(ref result, _blockByteSize);
            return result;
        }

        public byte[] Encrypt(byte[] source) {
            var encryptor = _cryptoManager.CreateEncryptor();
            if (!_isUseSessionKey) {
                return encryptor.TransformFinalBlock(source, 0, source.Length);
            }
            _cryptoManager.GenerateKey();
            List<byte> result = encryptor.TransformFinalBlock(_cryptoManager.Key, 0, _cryptoManager.Key.Length).ToList();

            encryptor = _cryptoManager.CreateEncryptor();
            result.AddRange(encryptor.TransformFinalBlock(source, 0, source.Length));

            return result.ToArray();
        }

        public byte[] Decrypt(byte[] source) {
            var decryptor = _cryptoManager.CreateDecryptor();
            if (!_isUseSessionKey) {
                return decryptor.TransformFinalBlock(source, 0, source.Length);
            }
            byte[] key = source.ToList().GetRange(0, _realkeyByteSize + _blockByteSize).ToArray();
            _cryptoManager.Key = decryptor.TransformFinalBlock(key, 0, key.Length);
            decryptor = _cryptoManager.CreateDecryptor();
            byte[] data = source.ToList().GetRange(_realkeyByteSize + _blockByteSize, source.Length - (_realkeyByteSize + _blockByteSize)).ToArray();
            return decryptor.TransformFinalBlock(data, 0, data.Length);
        }
    }
}
