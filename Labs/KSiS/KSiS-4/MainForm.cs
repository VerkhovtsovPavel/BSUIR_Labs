using System;
using System.Linq;
using System.Windows.Forms;

namespace HTTPServer
{
    public partial class MainForm : Form
    {
        private HTTPServer server;

        public MainForm()
        {
            InitializeComponent();

            try
            {
                server = new HTTPServer("http://localhost:8080/", "HTTPServerCatalog");

                server.Request += server_NewRequest;
                server.Response += server_NewResponse;

                server.Start();
            }
            catch
            {
                MessageBox.Show("Во время выполнения программы произошла ошибка");
            }
        }

        private void addTextToOutput(string text)
        {
            richTextBoxOutput.Invoke(new Action(() => { richTextBoxOutput.Text = text + richTextBoxOutput.Text; }));
        }

        private void server_NewRequest(object sender, RequestEventArgs e)
        {
            addTextToOutput("Сервер получил запрос\n" + e.ToString());
        }

        void server_NewResponse(object sender, ResponseEventArgs e)
        {
            addTextToOutput("Сервер отправил ответ\n" + e.ToString());
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            server.Stop();
        }
    }
}
