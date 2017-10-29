// KSiS1.cpp: определяет точку входа для консольного приложения.
//

#include "stdafx.h"
#include <windows.h>

#include <stdio.h>
#include <winnetwk.h>

#include <iphlpapi.h>
#include <stdlib.h>
#include <Windows.h>
#include <lm.h>
#include <assert.h>
#include <conio.h>


#pragma comment(lib, "Netapi32.lib")

#pragma comment(lib, "mpr.lib")

BOOL WINAPI EnumerateFunc(LPNETRESOURCE lpnr);
void DisplayStruct(int i, LPNETRESOURCE lpnrLocal);

// Prints the MAC address stored in a 6 byte array to stdout
static void PrintMACaddress(unsigned char MACData[])
{
	printf("MAC Address: %02X-%02X-%02X-%02X-%02X-%02X\n",
		MACData[0], MACData[1], MACData[2], MACData[3], MACData[4], MACData[5]);
}

// Fetches the MAC address and prints it
static void GetMACaddress(void)
{
	unsigned char MACData[8];						// Allocate data structure for MAC (6 bytes needed)

	WKSTA_TRANSPORT_INFO_0 *pwkti;					// Allocate data structure for Netbios
	DWORD dwEntriesRead;
	DWORD dwTotalEntries;
	BYTE *pbBuffer;

	// Get MAC address via NetBios's enumerate function
	NET_API_STATUS dwStatus = NetWkstaTransportEnum(
		NULL,						// [in]  server name
		0,							// [in]  data structure to return
		&pbBuffer,					// [out] pointer to buffer
		MAX_PREFERRED_LENGTH,		// [in]  maximum length
		&dwEntriesRead,				// [out] counter of elements actually enumerated
		&dwTotalEntries,			// [out] total number of elements that could be enumerated
		NULL);						// [in/out] resume handle
	assert(dwStatus == NERR_Success);

	pwkti = (WKSTA_TRANSPORT_INFO_0 *)pbBuffer;		// type cast the buffer

	for (DWORD i = 1; i< dwEntriesRead; i++)			// first address is 00000000, skip it
	{												// enumerate MACs and print
		swscanf((wchar_t *)pwkti[i].wkti0_transport_address, L"%2hx%2hx%2hx%2hx%2hx%2hx",
			&MACData[0], &MACData[1], &MACData[2], &MACData[3], &MACData[4], &MACData[5]);
		PrintMACaddress(MACData);
	}

	// Release pbBuffer allocated by above function
	dwStatus = NetApiBufferFree(pbBuffer);
	assert(dwStatus == NERR_Success);
}

int _tmain(int argc, _TCHAR* argv[])
{
	LPNETRESOURCE lpnr = NULL;
	GetMACaddress();
	printf("\n");
	if (EnumerateFunc(lpnr) == FALSE) {
		
		printf("Call to EnumerateFunc failed\n");
	}
	
	getchar();
	return 0;
}



BOOL WINAPI EnumerateFunc(LPNETRESOURCE lpnr)
{
	DWORD dwResult, dwResultEnum;
	HANDLE hEnum;
	DWORD cbBuffer = 16384;
	DWORD cEntries = -1;
	LPNETRESOURCE lpnrLocal;
	DWORD i;

	dwResult = WNetOpenEnum(RESOURCE_GLOBALNET, RESOURCETYPE_ANY, 0, lpnr, &hEnum);

	if (dwResult != NO_ERROR)
	{
		printf("WnetOpenEnum failed with error %d\n", dwResult);
		return FALSE;
	}

	lpnrLocal = (LPNETRESOURCE)GlobalAlloc(GPTR, cbBuffer);
	if (lpnrLocal == NULL) {
		printf("WnetOpenEnum failed with error %d\n", dwResult);
		return FALSE;
	}

	do {
		ZeroMemory(lpnrLocal, cbBuffer);

		dwResultEnum = WNetEnumResource(hEnum,
			&cEntries,
			lpnrLocal,
			&cbBuffer);

		if (dwResultEnum == NO_ERROR)
		{
			for (i = 0; i < cEntries; i++)
			{
				DisplayStruct(i, &lpnrLocal[i]);

				if (RESOURCEUSAGE_CONTAINER == (lpnrLocal[i].dwUsage
					& RESOURCEUSAGE_CONTAINER))
				if (!EnumerateFunc(&lpnrLocal[i]))
					printf("EnumerateFunc returned FALSE\n");
			}
		}
		else if (dwResultEnum != ERROR_NO_MORE_ITEMS)
		{
			printf("WNetEnumResource failed with error %d\n", dwResultEnum);
			break;
		}
	} while (dwResultEnum != ERROR_NO_MORE_ITEMS);

	GlobalFree((HGLOBAL)lpnrLocal);
	dwResult = WNetCloseEnum(hEnum);

	if (dwResult != NO_ERROR)
	{
		printf("WNetCloseEnum failed with error %d\n", dwResult);
		return FALSE;
	}

	return TRUE;
}

void DisplayStruct(int i, LPNETRESOURCE lpnrLocal)
{
	printf("--------- WorkGroup %d -------\nScope: ", i + 1);
	switch (lpnrLocal->dwScope) {
	case (RESOURCE_CONNECTED) :
		printf("connected\n");
		break;
	case (RESOURCE_GLOBALNET) :
		printf("all resources\n");
		break;
	case (RESOURCE_REMEMBERED) :
		printf("remembered\n");
		break;
	default:
		printf("unknown scope %d\n", lpnrLocal->dwScope);
		break;
	}

	printf("Type: ");
	switch (lpnrLocal->dwType) {
	case (RESOURCETYPE_ANY) :
		printf("any\n");
		break;
	case (RESOURCETYPE_DISK) :
		printf("disk\n");
		break;
	case (RESOURCETYPE_PRINT) :
		printf("print\n");
		break;
	default:
		printf("unknown type %d\n", lpnrLocal->dwType);
		break;
	}

	printf("DisplayType: ");
	switch (lpnrLocal->dwDisplayType) {
	case (RESOURCEDISPLAYTYPE_GENERIC) :
		printf("generic\n");
		break;
	case (RESOURCEDISPLAYTYPE_DOMAIN) :
		printf("domain\n");
		break;
	case (RESOURCEDISPLAYTYPE_SERVER) :
		printf("server\n");
		break;
	case (RESOURCEDISPLAYTYPE_SHARE) :
		printf("share\n");
		break;
	case (RESOURCEDISPLAYTYPE_FILE) :
		printf("file\n");
		break;
	case (RESOURCEDISPLAYTYPE_GROUP) :
		printf("group\n");
		break;
	case (RESOURCEDISPLAYTYPE_NETWORK) :
		printf("network\n");
		break;
	default:
		printf("unknown display type %d\n", lpnrLocal->dwDisplayType);
		break;
	}

	printf("Usage: 0x%x = ", lpnrLocal->dwUsage);
	if (lpnrLocal->dwUsage & RESOURCEUSAGE_CONNECTABLE)
		printf("connectable ");
	if (lpnrLocal->dwUsage & RESOURCEUSAGE_CONTAINER)
		printf("container ");
	printf("\n");

	printf("Localname: %S\n", lpnrLocal->lpLocalName);
	printf("Remotename: %S\n", lpnrLocal->lpRemoteName);
	printf("Comment: %S\n", lpnrLocal->lpComment);
	printf("Provider: %S\n", lpnrLocal->lpProvider);
	printf("\n");
}
