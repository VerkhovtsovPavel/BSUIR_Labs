#include <iostream>
#include "cplusplus_NativeCalls.h"

JNIEXPORT void JNICALL Java_cplusplus_NativeCalls_deleteFile (JNIEnv *, jclass, jcharArray fileName){
      int result = _unlink (fileName); 
      if (value == 0) 
        printf ("Erased \"%s\" from disk\n", fileName); 
      else 
      { 
        perror("Unable to erase file"); 
      }
}