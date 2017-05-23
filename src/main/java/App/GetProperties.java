package App;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jasonkelly on 23/05/2017.
 */
public class GetProperties {


    public String getProperty (String propValue) {

        Properties prop = new Properties();
        InputStream input = null;


        try {
            String propFileName = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(input==null){
                System.out.println("Sorry, unable to find ");
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);


            return prop.getProperty(propValue);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }

}
