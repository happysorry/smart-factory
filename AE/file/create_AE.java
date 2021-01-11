package file;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * create AE on mn1
 */
public class create_AE {
    public create_AE(String target, String ae_name, String poa) {
        URL url;
        try {
            url = new URL(target);
            HttpURLConnection http;
            try {
                http = (HttpURLConnection) url.openConnection();
                http.setDoOutput(true);
                http.setRequestProperty("X-M2M-Origin", "admin:admin");
                http.setRequestProperty("Content-Type", "application/json;ty=2");
                http.setRequestMethod("POST");
                http.connect();
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
                
                

                //'{"m2m:ae": {"rn": "EXAMPLE_APP_NAME", "api": "placeholder", "rr": "TRUE"}}'
                
                String request = "{\"m2m:ae\": {\"rn\": \""+ae_name+"\", \"api\": \""+ae_name+"\", \"rr\": \"FALSE\",\"poa\": \""+poa+"\"}}";
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
