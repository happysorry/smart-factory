import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class make_con {
    static int t = 10;

    public static void main(String[] args) {
        try {
            
            FileWriter fw2 = new FileWriter("data.csv");
            int num = 0;
            String test="";
            for (int i = 0; i < t; i++){
                num = (int)(Math.random() *89999) + 10000;
                if(num%2==0)
                    test = "true";
                else
                    test ="false";
                // String req = String.valueOf(num)+","+test;
                fw2.write(test);
                fw2.write("\n");
                fw2.flush();
            }
            fw2.close();
      
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e);
            }
            
    }
}
