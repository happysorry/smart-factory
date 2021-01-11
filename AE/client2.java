import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * 
 * 詢問discovery server mn的IPaddress
 */
public class client2 extends Thread
{
    public static void main(String[] argv)
    {
        new client2("127.0.0.1", 8000).start();//建立物件，傳入IP和Port並執行等待接受連線的動作
        //由於此範例都在自己電腦上執行，所以IP設定為127.0.0.1
    }
    private Socket m_socket;//和伺服器端進行連線
    
    public client2(String ip, int port)
    {
        try
        {
            m_socket = new Socket(ip, port);//建立連線。(ip為伺服器端的ip，port為伺服器端開啟的port)
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        try
        {
            if (m_socket != null)//連線成功才繼續往下執行
            {

                //由於Server端使用 PrintStream 和 BufferedReader 來接收和寄送訊息，所以Client端也要相同
                PrintStream writer = new PrintStream(m_socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
            
                writer.println("現在時間是？");
                
                writer.println("jjjj");
                writer.flush();
                writer.close();
                System.out.println("Server:" + reader.readLine());
            
                m_socket.close();

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}