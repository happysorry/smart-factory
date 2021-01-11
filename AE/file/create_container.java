package file;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * create container under above AE
 */
public class create_container {
    public create_container(String target,String cnt_name){
            URL url;
            try {
                url = new URL(target);
                HttpURLConnection http;
                try {
                    http = (HttpURLConnection) url.openConnection();
                    http.setDoOutput(true);
                    http.setRequestProperty("X-M2M-Origin", "admin:admin");
                    http.setRequestProperty("Content-Type", "application/json;ty=3");
                    http.setRequestMethod("POST");
                    http.connect();
                    DataOutputStream out = new DataOutputStream(http.getOutputStream());
                    
    
                    
                    //'{"m2m:cnt": {"rn": "EXAMPLE_CONTAINER_NAME"}}'
                    String request = "{\"m2m:cnt\": {\"rn\": \""+cnt_name+"\", \"rr\": \"FALSE\"}}";
                    out.write(request.toString().getBytes("UTF-8")); 
                    out.flush();
                    out.close();
                    System.out.println(http.getResponseCode());
                     
                    
    
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        
    }
}
