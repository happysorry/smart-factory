
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class send_sensor extends java.util.TimerTask{
    public static void main(String[]args){
        Timer timer = new Timer();
        timer.schedule(new send_sensor(),1000,100);

    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        String sensor = "sensor1";
        String RFID = String.valueOf((int) (Math.random()*89999)+10000);
        post_mn1(RFID,sensor);
    }


    public static void post_mn1(String RFID,String sensor){
        try {
            
            int val = value(sensor);
            String values = String.valueOf(val);

            URL url = new URL("http://192.168.99.103:8282/~/mn-cse/mn-name/factory/" + sensor);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            // http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("X-M2M-Origin", "admin:admin");
            http.setRequestProperty("Content-Type", "application/json;ty=4");
            try {
                http.setRequestMethod("POST");
                http.connect();
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
                String request = "{" + '"' + "m2m:cin" + '"' + ": {" + '"' + "con" + '"' + ": " + '"' + values.toString()
                        + '"' + ", " + '"' + "cnf" + '"' + ": " + '"' + "text/plain:0" + '"' + "," + '"' + "rn" + '"'
                        + ": " + '"' + RFID + '"' + "}}";
                // '{"m2m:cin": {"con": "EXAMPLE_VALUE", "cnf": "text/plain:0"}}'
                out.write(request.toString().getBytes("UTF-8"));
                out.flush();
                out.close();
                int status = http.getResponseCode();
                
                System.out.println(status);
               
                if(status == 201){
                    if(sensor == "sensor1"){
                        sensor = "sensor2";
                        post_mn1(RFID,sensor);
                    }
                    else if(sensor == "sensor2"){
                        sensor = "sensor3";
                        post_mn1(RFID,sensor);
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static int value(String sensor){
        if(sensor == "sensor1")
            return (int) (Math.random() *10)+600;
        else
            return (int) (Math.random() *1);
    }

   
    
}

