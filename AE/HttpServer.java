import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer extends Thread {
    private boolean outserver = false;
    private ServerSocket server;
    private int port = 828;

    public HttpServer() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run(){
        Socket socket;
        while(!outserver){
            socket = null;

            synchronized(server){
                try {
                    socket = server.accept();
                    socket.setSoTimeout(10000);
                    BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                    byte[] buf = new byte[1024];
                    String data = "";
                    int length;
                    while((length = in.read(buf)) > 0){
                        data += new String(buf, 0, length);
                    }
                    in.close();
                    in = null;
                    socket.close();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]){
        new HttpServer().start();
    }
    
}
