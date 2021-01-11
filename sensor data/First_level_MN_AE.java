
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class First_level_MN_AE {
public static String IN = "127.0.0.1:8080/~/in-cse";

    public static void main(String args []){

    }

    /**
     * discovery available mn1
     * and call split1() to split out available path of mn1
     */
    public void discover_mn1(){
        try {
            URL url = new URL(IN + "?fu=1&ty=16");
            
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestProperty("Accept", "application/xml");
            http.setRequestProperty("X-M2M-Origin", "admin:admin");
            // http.setRequestProperty("Authorization",basicAuth);
            http.setDoInput(true);
            http.setRequestMethod("GET");

            int satus = http.getResponseCode();
            System.out.println(satus);
            InputStream in = http.getInputStream();

            if (in != null) {
                InputStreamReader reader = new InputStreamReader(in, "UTF-8");
                BufferedReader input = new BufferedReader(reader);
                String line = "";
                String result = "";
                while ((line = input.readLine()) != null) {
                    result += (line + "\n");
                }
                /**
                 * result example:
                 * <?xml version="1.0" encoding="UTF-8"?>
                    <m2m:uril xmlns:m2m="http://www.onem2m.org/xml/protocols" xmlns:hd="http://www.onem2m.org/xml/protocols/homedomain">/in-cse/in-name/mn-name</m2m:uril>
                 */
                split1(result);
            } else
                System.out.println("failed");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.println("mal");
            e.printStackTrace();
        } catch (IOException e) {
            // System.out.println("IOE");
        }
    }

    /**
     * split out discovery result from in
     * and call discovery_mn2() to get path of mn1
     */
    public void split1(String result){
        String [] sp = result.split("homedomain\">");
        String value = sp[1];
        String [] sp2 = value.split("</m2m:uril>");
        value = sp2[0];
        discover_mn2(value);
    }
    /**
     * find real ip of mn1
     * URL would be IN + path
     * @param path
     */
    public void discover_mn2(String path){
        try {
            URL url = new URL(IN + path);
            
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestProperty("Accept", "application/xml");
            http.setRequestProperty("X-M2M-Origin", "admin:admin");
            // http.setRequestProperty("Authorization",basicAuth);
            http.setDoInput(true);
            http.setRequestMethod("GET");

            int satus = http.getResponseCode();
            System.out.println(satus);
            InputStream in = http.getInputStream();

            if (in != null) {
                InputStreamReader reader = new InputStreamReader(in, "UTF-8");
                BufferedReader input = new BufferedReader(reader);
                String line = "";
                String result = "";
                while ((line = input.readLine()) != null) {
                    result += (line + "\n");
                }
                /**
                 * result example:
                 * <?xml version="1.0" encoding="UTF-8"?>
                    <m2m:csr xmlns:m2m="http://www.onem2m.org/xml/protocols" xmlns:hd="http://www.onem2m.org/xml/protocols/homedomain" rn="mn-name">
                    // <ty>16</ty>
                    // <ri>/in-cse/csr-335987571</ri>
                    // <pi>/in-cse</pi>
                    // <ct>20201210T135038</ct>
                    // <lt>20201210T135038</lt>
                    // <acpi>/in-cse/acp-686072830</acpi>
                    // <poa>http://127.0.0.1:8282/</poa>
                    // <cb>//om2m.org/mn-cse</cb>
                    // <csi>/mn-cse</csi>
                    // <rr>true</rr>
                    </m2m:csr>
                 */

            } else
                System.out.println("failed");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.println("mal");
            e.printStackTrace();
        } catch (IOException e) {
            // System.out.println("IOE");
        }
    }

   /**
     * split out discovery result from in
     * and call discovery_mn2() to get real ip of mn1
     */
    public void split2(String result){
        String [] sp = result.split("<poa>");
        String value = sp[1];
        String [] sp2 = value.split("</poa>");
        value = sp2[0];
        post_MN_AE(value);
    }
    /**
     * registery mnae to mn1
     * 
     * @param IP
     */
    public static void post_MN_AE(String IP) {
        try {
            
            URL url = new URL(IP + "/~/mn-cse");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            // http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("X-M2M-Origin", "admin:admin");
            http.setRequestProperty("Content-Type", "application/json;ty=4");
            try {
                http.setRequestMethod("POST");
                http.connect();
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
               

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {

        }
    } 

}
