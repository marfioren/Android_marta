using System;
using System.IO.Ports;
using System.Threading;
using System.Net;

namespace Arduino
{
    class Program
    {
        static void Main(string[] args)
        {
            SerialPort myport = new SerialPort();
            myport.BaudRate = 9600;
            myport.PortName = "COM4";
            myport.Open();
  
            while (true)
            {
                String data = myport.ReadLine();
                Console.WriteLine(data);
                String[] podaci = data.Split(',');
                float vlaznost = float.Parse(podaci[0]);
                float temp = float.Parse(podaci[1]);
                String url = "https://mjerenje.info/services/podaci.php?vlaznost=" + vlaznost + "&temp=" + temp;
                using (WebClient client = new WebClient())
                {
                    string pagesource = client.DownloadString(url);
                }

                    Thread.Sleep(2000);
            }
        }
    }
}
