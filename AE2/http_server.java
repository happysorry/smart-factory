
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class http_server {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(2222), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("server started");
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            InputStream is = t.getRequestBody();
            InputStreamReader isReader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isReader);
            
            //code to read and print headers
            String headerLine = null;
            String result = "";
            while((headerLine = br.readLine())!= null){
                // System.out.println(headerLine);
                    
                result += headerLine + "\n";
            }
            
            // System.out.println(result);//讀取一行字串資料
            System.out.println(t.getRemoteAddress());

            /**
             * dealing with post request
             */
            if("POST".equals(t.getRequestMethod())){
                System.out.println("POST");
            }
            /**
             * dealing with get request
             */
            if("GET".equals(t.getRequestMethod())){
                System.out.println("GET");
            }
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    /**
     * split out label ,content
     * check whether content value is true or false
     * if false , put it into defected list container
     * @param result
     */
    public static void do_post(String result){
        String sp[] = result.split("<lbl>");
        String sp1[] = sp[1].split("</lbl>");
        String label = sp1[0];

        sp = result.split("<con>");
        sp1 = sp[1].split("</con>");
        String content = sp1[0];

        if(content.equals("false")){
            sp = result.split("rn=\"");
            sp1 = sp[1].split("\">");
            String rn = sp1[0];
            // send_defect(rn);
        }

    }

}