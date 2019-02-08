using System;
using System.Runtime.InteropServices;

namespace Lab3 {
    public class CryptoWrapper {
        #region Consts
        public const Int32 ALG_CLASS_HASH = (4 << 13);
        public const Int32 ALG_TYPE_ANY = (0);
        public const Int32 ALG_SID_MD5 = 3;
        public const Int32 ALG_SID_HMAC = 9;

        public const Int32 ALG_CLASS_DATA_ENCRYPT = (3 << 13);
        public const Int32 ALG_TYPE_BLOCK = (3 << 9);
        public const Int32 ALG_SID_3DES_112 = 9;

        public const Int32 CALG_MD5 = (ALG_CLASS_HASH | ALG_TYPE_ANY | ALG_SID_MD5);
        public const Int32 CALG_SHA1 = (4 << 13) | 4;
        public const Int32 CALG_HMAC = (ALG_CLASS_HASH | ALG_TYPE_ANY | ALG_SID_HMAC);
        public const Int32 CALG_3DES_112 = (ALG_CLASS_DATA_ENCRYPT | ALG_TYPE_BLOCK | ALG_SID_3DES_112);

        public const Int32 PROV_RSA_FULL = 0x00000001;
        public const Int32 CRYPT_VERIFYCONTEXT = -268435456;

        public const Int32 HP_HMAC_INFO = 0x00000005;
        public const Int32 HP_HASHVAL = 0x00000002;

        public const Int32 AT_KEYEXCHANGE = 1;
        public const Int32 AT_SIGNATURE = 2;

        public const Int32 PUBLICKEYBLOB = 0x06;

        public const Int32 CRYPT_EXPORTABLE = 0x00000001;

        public const Int32 BUFF_SIZE = 8;
        #endregion

        #region Structs
        //        typedef struct _HMAC_Info {
        //  ALG_ID HashAlgid;
        //  BYTE   *pbInnerString;
        //  DWORD  cbInnerString;
        //  BYTE   *pbOuterString;
        //  DWORD  cbOuterString;
        //} 
        [StructLayout(LayoutKind.Sequential)]
        public struct HMAC_INFO {
            public Int32 HashAlgId;
            public Byte[] pbInnerString;
            public uint cbInnerString;
            public Byte[] pbOuterString;
            public uint cbOuterString;
        }
        #endregion

        #region Functions

        // BOOL WINAPI CryptAcquireContext(
        //		HCRYPTPROV* phProv,
        //		LPCTSTR pszContainer,
        //		LPCTSTR pszProvider,
        //		DWORD dwProvType,
        //		DWORD dwFlags
        // );
        [DllImport("advapi32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        public static extern bool CryptAcquireContext(
            ref IntPtr hProv,
            String pszContainer,
            String pszProvider,
            Int32 dwProvType,
            Int32 dwFlags
            );

        // BOOL WINAPI CryptReleaseContext(
        //		HCRYPTPROV hProv,
        //		DWORD dwFlags
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptReleaseContext(
            IntPtr hProv,
            Int32 dwFlags
            );

        // BOOL WINAPI CryptCreateHash(
        //		HCRYPTPROV hProv,
        //		ALG_ID Algid,
        //		HCRYPTKEY hKey,
        //		DWORD dwFlags,
        //		HCRYPTHASH* phHash
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptCreateHash(
            IntPtr hProv,
            Int32 Algid,
            IntPtr hKey,
            Int32 dwFlags,
            ref IntPtr phHash
            );

        // BOOL WINAPI CryptHashData(
        //		HCRYPTHASH hHash,
        //		BYTE* pbData,
        //		DWORD dwDataLen,
        //		DWORD dwFlags
        // );
        [DllImport("Advapi32.dll", SetLastError = true)]
        public static extern Boolean CryptHashData(
            IntPtr hHash,
            Byte[] pbData,
            Int32 dwDataLen,
            Int32 dwFlags
            );

        // BOOL WINAPI CryptGenKey(
        //		HCRYPTPROV hProv,
        //		ALG_ID Algid,
        //		DWORD dwFlags,
        //		HCRYPTKEY* phKey
        // );	
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptGenKey(
            IntPtr hProv,
            Int32 Algid,
            Int32 dwFlags,
            ref IntPtr phKey
            );

        // BOOL WINAPI CryptGetUserKey(
        //		HCRYPTPROV hProv,
        //		DWORD dwKeySpec,
        //		HCRYPTKEY* phUserKey
        // );
        [DllImport("Advapi32.dll", SetLastError = true)]
        public static extern Boolean CryptGetUserKey(
            IntPtr hProv,
            Int32 dwKeySpec,
            ref IntPtr phUserKey
            );


        // BOOL WINAPI CryptExportKey(
        //		HCRYPTKEY hKey,
        //		HCRYPTKEY hExpKey,
        //		DWORD dwBlobType,
        //		DWORD dwFlags,
        //		BYTE* pbData,
        //		DWORD* pdwDataLen
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptExportKey(
            IntPtr hKey,
            IntPtr hExpKey,
            Int32 dwBlobType,
            Int32 dwFlags,
            Byte[] pbData,
            ref Int32 pdwDataLen
            );

        // BOOL WINAPI CryptImportKey(
        //		HCRYPTPROV hProv,
        //		BYTE* pbData,
        //		DWORD dwDataLen,
        //		HCRYPTKEY hPubKey,
        //		DWORD dwFlags,
        //		HCRYPTKEY* phKey
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptImportKey(
            IntPtr hProv,
            Byte[] pbData,
            Int32 dwDataLen,
            IntPtr hPubKey,
            Int32 dwFlags,
            ref IntPtr phKey
            );

        // BOOL WINAPI CryptGenKey(
        //		HCRYPTPROV hProv,
        //		ALG_ID Algid,
        //		DWORD dwFlags,
        //		HCRYPTKEY* phKey
        // );	
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptDeriveKey(
            IntPtr hProv,
            Int32 Algid,
            IntPtr hHash,
            Int32 dwFlags,
            ref IntPtr phKey
            );

        // BOOL WINAPI CryptEncrypt(
        //      HCRYPTKEY hKey,
        //      HCRYPTHASH hHash,
        //      BOOL Final,
        //      DWORD dwFlags,
        //      BYTE *pbData,
        //      DWORD *pdwDataLen,
        //      DWORD dwBufLen
        // );
        [DllImport("Advapi32.dll", SetLastError = true)]
        public static extern Boolean CryptEncrypt(
            IntPtr hKey,
            IntPtr hHash,
            bool Final,
            Int32 dwFlags,
            Byte[] pbData,
            ref Int32 pdwDataLen,
            Int32 dwBufLen
            );

        // BOOL WINAPI CryptDecrypt(
        //      HCRYPTKEY hKey,
        //      HCRYPTHASH hHash,
        //      BOOL Final,
        //      DWORD dwFlags,
        //      BYTE *pbData,
        //      DWORD *pdwDataLen
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptDecrypt(
            IntPtr hKey,
            IntPtr hHash,
            bool Final,
            Int32 dwFlags,
            byte[] pbData,
            ref Int32 pdwDataLen
            );

        // BOOL WINAPI CryptDestroyHash(
        //      HCRYPTHASH hHash
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptDestroyHash(
            IntPtr hHash
            );

        // BOOL WINAPI CryptDestroyKey(
        //		HCRYPTKEY hKey
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptDestroyKey(
            IntPtr hKey
            );

        // BOOL WINAPI CryptGetHashParam(
        //		HCRYPTHASH hHash,
        //		DWORD dwParam,
        //		BYTE* pbData,
        //		DWORD* pdwDataLen,
        //		DWORD dwFlags
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptGetHashParam(
            IntPtr hHash,
            Int32 dwParam,
            Byte[] pbData,
            ref Int32 pdwDataLen,
            Int32 dwFlags
            );

        // BOOL WINAPI CryptSetHashParam(
        //		HCRYPTHASH hHash,
        //		DWORD dwParam,
        //		BYTE* pbData,
        //		DWORD dwFlags
        // );
        [DllImport("advapi32.dll", SetLastError = true)]
        public static extern bool CryptSetHashParam(
            IntPtr hHash,
            Int32 dwParam,
            Byte[] pbData,
            Int32 dwFlags
            );

        // BOOL WINAPI CryptSignHash(
        //		HCRYPTHASH hHash,
        //		DWORD dwKeySpec,
        //		LPCTSTR sDescription,
        //		DWORD dwFlags,
        //		BYTE* pbSignature,
        //		DWORD* pdwSigLen
        // );
        [DllImport("Advapi32.dll", SetLastError = true)]
        public static extern Boolean CryptSignHash(
            IntPtr hHash,
            Int32 dwKeySpec,
            String sDescription,
            Int32 dwFlags,
            Byte[] pbSignature,
            ref Int32 pdwSigLen
            );

        // BOOL WINAPI CryptVerifySignature(
        //		HCRYPTHASH hHash,
        //		BYTE* pbSignature,
        //		DWORD dwSigLen,
        //		HCRYPTKEY hPubKey,
        //		LPCTSTR sDescription,
        //		DWORD dwFlags
        // );
        [DllImport("advapi32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        public static extern bool CryptVerifySignature(
            IntPtr hHash,
            Byte[] pbSignature,
            Int32 dwSigLen,
            IntPtr hPubKey,
            String sDescription,
            Int32 dwFlags
            );

        #endregion
    }
}
