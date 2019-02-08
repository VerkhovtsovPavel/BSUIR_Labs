using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace HTTPServer
{
    public class ResponseEventArgs : EventArgs
    {
        private HttpListenerResponse Response { get; set; }

        private string BodyContent { get; set; }

        public ResponseEventArgs(HttpListenerResponse response, string bodyContent)
        {
            Response = response;
            BodyContent = bodyContent;
        }

        public override string ToString()
        {
            StringBuilder retString = new StringBuilder();

            retString.AppendLine(Response.ProtocolVersion + " " + Response.StatusCode + " " + Response.StatusDescription);
            retString.AppendLine("Date: " + DateTime.Now);
            
            foreach(var header in Response.Headers)
            {
                retString.AppendLine(header.ToString()+": "+ Response.Headers.GetValues(header.ToString()).First().ToString());
            }

            retString.AppendLine();
            retString.AppendLine(BodyContent);
            retString.AppendLine();

            return retString.ToString();
        }
    }
}
