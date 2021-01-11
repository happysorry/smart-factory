package file;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class AE {

    /**
     * 1.discovery available mn1
     *      find mn1 IP address
     * 2.register AE to mn1
     *      create AE and container on mn1 
     * @param args
     */
    public static void main(String[]args){
        
        String mn = discovery_mn();
        create_AE(mn,"AE1","AE1");
        mn += "/mn-name/AE1";
        create_container(mn,"RFID_Container_for_stage0");
        sub_ae(mn,"RFID_Container_for_stage0");
        create_container(mn,"RFID_Container_for_stage1");
        sub_ae(mn,"RFID_Container_for_stage1");
        create_container(mn,"Liquid_Level_Container");
        sub_ae(mn,"Liquid_Level_Container");
        create_container(mn,"RFID_Container_for_stage2");
        sub_ae(mn,"RFID_Container_for_stage2");
        create_container(mn,"Color_Container");
        sub_ae(mn,"Color_Container");
        create_container(mn,"RFID_Container_for_stage3");
        sub_ae(mn,"RFID_Container_for_stage3");
        create_container(mn,"Contrast_Data_Container");
        sub_ae(mn,"Contrast_Data_Container");
        create_container(mn,"RFID_Container_for_stage4");
        sub_ae(mn,"RFID_Container_for_stage4");
        // CON = new create_container(mn,"Defective_Product_Container");
        // System.out.println(mn);
    }

    public static void create_AE(String target, String ae_name, String poa) {
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

    public static void create_container(String target,String cnt_name){
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

    public static String discovery_mn(){
        String mn = "";
        mn = "http://140.116.247.73:8282/~/mn-cse";
        return mn;
    }

    public static String sub_ae(String target , String cnt_name){
        try {
            target = target + "/" + cnt_name;
            URL url = new URL(target);
            try {
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setDoOutput(true);
                http.setRequestProperty("X-M2M-Origin", "admin:admin");
                http.setRequestProperty("Content-Type", "application/json;ty=23");
                http.setRequestMethod("POST");
                http.connect();
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
                /**
                 * <m2m:sub xmlns:m2m="http://www.onem2m.org/xml/protocols">
                        <nu>mn-name/test_ipe</nu>
                        <nct>2</nct>
                    </m2m:sub>
                 * 
                 */
                String request = "{\"m2m:sub\":{\"rn\":\"sub\",\"nu\":\"http://mnae1:4444\",\"nct\":\"2\"}}";
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
        return null;
        
    }
}