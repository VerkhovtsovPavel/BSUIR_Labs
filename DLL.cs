using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OSiSP_lab1
{
    class DLL 
    {
        public int ReturnFive(byte b){
            return 5;
        }

        public int ReturnTwo(int i) {
            return 2;
        }

        public string ReturnString(string str) {
            return str;
        }

        private void Void(int i){
            i++;
        }
    }
}
