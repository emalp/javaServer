import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by EMALP on 9/3/2017.
 */
public class commandProcesser {

    File folder = new File(".\\UploadFiles");

    Logger logger = Logger.getLogger(commandProcesser.class.getName());

    ArrayList<String> names;

    public String[] ls() {

        int n = folder.listFiles().length;
        if(n == 0){
            String a[] = {"This folder is empty"};
            return a;

        }
        else {

            names = new ArrayList<String>();


            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {


                    names.add("DIR : " + fileEntry.getName() + "\t");

//                    System.out.println("INTERNAL:: DIR : " + fileEntry.getName() + "\t");
                    logger.log(Level.INFO, "Sending directory names");


                } else {

                    names.add("FIL : " + fileEntry.getName() + "\t");
//                    System.out.println("INTERNAL:: FIL : " + fileEntry.getName());
                    logger.log(Level.INFO, "Sending file Names.");
                }
            }
            String a[] = new String[n - 1];
            a = names.toArray(a);
            return a;
            //return null;

        }
    }

    public String pwd(){

//        System.out.println("Current Working Directory = " +
//                System.getProperty("user.dir"));

        logger.log(Level.INFO, "Sending current directory name");

        return("Current Working Directory = " +
                System.getProperty("user.dir"));
    }
}
