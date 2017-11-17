/**
 * Created by DELL on 8/25/2017.
 */

import java.io.File;
import java.util.Scanner;
public class serverMain {
    public static void main(String args[]){

      //serverGUI sg = new serverGUI();

        File f = new File(".\\DownloadedFiles");
        File f2 = new File(".\\UploadFiles");
       if(!((f.exists()) && f2.exists())){
           f.mkdir();
           f2.mkdir();
       }

        Scanner s = new Scanner(System.in);
        System.out.println("***** Welcome to EM-SERVER ******");
        System.out.println("Press 1 to start the server.");
        System.out.println("Press 2 to Manipulate the user database.\n");

        int output = s.nextInt();

        switch(output){
            case 1: {
                simpleServer ss = new simpleServer();

            }
            case 2:{
                try {
                    manipulateDatabase mainuplate = new manipulateDatabase();
                }
                catch(Exception ex ){
                    System.out.println("Cant manipulate databasse");
                }
            }

        }

    }
}
