using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace HTTPServer
{
    public class RequestEventArgs : EventArgs
    {
        private HttpListenerRequest Request { get; set; }

        private string BodyContent { get; set; }

        public RequestEventArgs(HttpListenerRequest request, string bodyContent)
        {
            Request = request;
            BodyContent = bodyContent;
        }

        public override string ToString()
        {
            StringBuilder retString = new StringBuilder();

            retString.AppendLine(Request.HttpMethod + " " + Request.RawUrl + " " + Request.ProtocolVersion);

            foreach (var header in Request.Headers)
            {
                retString.AppendLine(header.ToString() + ": " + Request.Headers.GetValues(header.ToString()).First().ToString());
            }

            retString.AppendLine();
            retString.AppendLine(BodyContent);
            retString.AppendLine();

            return retString.ToString();
        }
    }
}