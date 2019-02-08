using System.Security.Cryptography;

namespace Lab2 {
    public class DigitalSignatureHelper {
        private RSAParameters _mPublic;

        public byte[] CreateSignature(byte[] hash) {
            var rsa = new RSACryptoServiceProvider();
            var rsaFormatter = new RSAPKCS1SignatureFormatter(rsa);
            rsaFormatter.SetHashAlgorithm("SHA1");
            _mPublic = rsa.ExportParameters(false);
            return rsaFormatter.CreateSignature(hash);
        }

        public bool VerifySignature(byte[] hash, byte[] signedhash) {
            var rsa = new RSACryptoServiceProvider();
            var rsaKeyInfo = new RSAParameters {
                Modulus = _mPublic.Modulus,
                Exponent = _mPublic.Exponent
            };
            rsa.ImportParameters(rsaKeyInfo);
            var rsaDeformatter = new RSAPKCS1SignatureDeformatter(rsa);
            rsaDeformatter.SetHashAlgorithm("SHA1");
            return rsaDeformatter.VerifySignature(hash, signedhash);
        }
    }
}
