using System.Text;
using System.Security.Cryptography;


namespace Lab2 {
    public class HashHelper {
        public static byte[] GetHash(byte[] message) {
            return SHA1.Create().ComputeHash(message, 0, message.Length);
        }

        public static byte[] GetHash(string message) {
            var data = Encoding.UTF8.GetBytes(message);
            return SHA1.Create().ComputeHash(data, 0, data.Length);
        }

        public static bool VerifyHash(string message, byte[] hash) {
            var data = Encoding.UTF8.GetBytes(message);
            byte[] hashTemp = SHA1.Create().ComputeHash(data, 0, data.Length);

            return Equals(hash, hashTemp);
        }
    }
}
