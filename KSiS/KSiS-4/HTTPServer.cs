using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace HTTPServer
{
    public class HTTPServer
    {
        #region Properties
        public string ServerURLPrefix { get; set; }

        public string ServerCatalog { get; set; }

        private Thread ServerThread { get; set; }

        private HttpListener ServerListener { get; set; }

        private List<string> ServerStorage { get; set; }
        #endregion

        #region Events and Delegates
        public delegate void RequestEventHandler(object sender, RequestEventArgs e);

        public delegate void ResponseEventHandler(object sender, ResponseEventArgs e);

        public event RequestEventHandler Request;

        public event ResponseEventHandler Response;
        #endregion

        #region Ctors
        public HTTPServer(string serverURLPrefix, string serverCatalog)
        {
            ServerURLPrefix = serverURLPrefix;
            ServerCatalog = serverCatalog;

            ServerStorage = new List<string>();
        }
        #endregion

        #region Public methods
        public void Start()
        {
            ServerThread = new Thread(ServerProcedure);
            ServerThread.Start();
        }

        public void Stop()
        {
            ServerListener.Stop();
            ServerThread.Abort();
        }
        #endregion

        #region Protected methods
        protected virtual void OnRequest(object sender, RequestEventArgs e)
        {
            if (Request != null)
            {
                Request(sender, e);
            }
        }

        protected virtual void OnResponse(object sender, ResponseEventArgs e)
        {
            if (Response != null)
            {
                Response(sender, e);
            }
        }
        #endregion

        #region Private methods
        private void ServerProcedure()
        {
            ServerListener = new HttpListener();

            ServerListener.Prefixes.Add(ServerURLPrefix);

            ServerListener.Start();

            while (ServerListener.IsListening)
            {
                HttpListenerContext httpContext = ServerListener.GetContext();

                HTTPContextProcess(httpContext);
            }
        }

        private void HTTPContextProcess(HttpListenerContext httpContext)
        {
            switch (httpContext.Request.HttpMethod)
            {
                case "GET":
                    {
                        GETRequestProcess(httpContext);
                        break;
                    }

                case "HEAD":
                    {
                        HEADRequestProcess(httpContext);
                        break;
                    }

                case "POST":
                    {
                        POSTRequestProcess(httpContext);
                        break;
                    }
            }

            httpContext.Response.Close();
        }

        public void GETRequestProcess(HttpListenerContext httpContext)
        {
            byte[] responseContent;
            string content = GetContent(httpContext.Request);
            
            OnRequest(this, new RequestEventArgs(httpContext.Request, content));

            if (File.Exists(ServerCatalog + httpContext.Request.RawUrl))
            {
                httpContext.Response.StatusCode = 200;
                httpContext.Response.Headers.Set(HttpResponseHeader.ContentType, "text/html");
                httpContext.Response.Headers.Set(HttpResponseHeader.Server, "HTTPServer");
                responseContent = File.ReadAllBytes(ServerCatalog + httpContext.Request.RawUrl);
            }
            else
            {
                httpContext.Response.StatusCode = 404;
                responseContent = Encoding.UTF8.GetBytes(string.Empty);
            }

            OnResponse(this, new ResponseEventArgs(httpContext.Response, Encoding.UTF8.GetString(responseContent)));
            httpContext.Response.OutputStream.Write(responseContent, 0, responseContent.Count());
        }

        public void HEADRequestProcess(HttpListenerContext httpContext)
        {
            string content = GetContent(httpContext.Request);
            OnRequest(this, new RequestEventArgs(httpContext.Request, content));

            if (File.Exists(ServerCatalog + httpContext.Request.RawUrl))
            {
                httpContext.Response.StatusCode = 200;
                httpContext.Response.Headers.Set(HttpResponseHeader.ContentType, "text/html");
                httpContext.Response.Headers.Set(HttpResponseHeader.Server, "HTTPServer");
            }
            else
            {
                httpContext.Response.StatusCode = 404;
            }

            OnResponse(this, new ResponseEventArgs(httpContext.Response, string.Empty));
        }

        public void POSTRequestProcess(HttpListenerContext httpContext)
        {
            byte[] responseContent;
            string content = GetContent(httpContext.Request);
            
            OnRequest(this, new RequestEventArgs(httpContext.Request, content));

            if (File.Exists(ServerCatalog + httpContext.Request.RawUrl))
            {
                if (httpContext.Request.RawUrl == "/form.html")
                {
                    if (content != null && content.Substring(content.IndexOf('=') + 1, content.Length - (content.IndexOf('=') + 1)) != string.Empty)
                    {
                        ServerStorage.Add(content.Substring(content.IndexOf('=') + 1, content.Length - (content.IndexOf('=') + 1)));

                        string values = string.Empty;

                        foreach(string str in ServerStorage)
                        {
                            values += str + " ";
                        }

                        httpContext.Response.StatusCode = 201;
                        responseContent = Encoding.UTF8.GetBytes("Created. Value was added to storage. Values: " + values);
                    }
                    else
                    {
                        httpContext.Response.StatusCode = 400;
                        responseContent = Encoding.UTF8.GetBytes("Bad request. Value can not be empty");
                    }
                }
                else
                {
                    httpContext.Response.StatusCode = 405;
                    responseContent = Encoding.UTF8.GetBytes("Method Not Allowed");
                }
            }
            else
            {
                httpContext.Response.StatusCode = 404;
                responseContent = Encoding.UTF8.GetBytes("Not found");
            }

            OnResponse(this, new ResponseEventArgs(httpContext.Response, Encoding.UTF8.GetString(responseContent)));
            httpContext.Response.OutputStream.Write(responseContent, 0, responseContent.Count());
        }

        private string GetContent(HttpListenerRequest request)
        {
            string postData = null;

            using (Stream requestBody = request.InputStream)
            {
                using (StreamReader reader = new StreamReader(requestBody, request.ContentEncoding))
                {
                    postData = reader.ReadToEnd();
                }
            }

            return postData;
        }
        #endregion
    }
}
