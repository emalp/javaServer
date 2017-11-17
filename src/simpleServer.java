/**
 * Created by EMALP on 8/25/2017.
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

import java.util.logging.*;

public class simpleServer {

    static String clmsg;
    final int PORT = 2000;
    ServerSocket ss;
    Socket s;
    BufferedReader br;
    PrintWriter pw;
    commandProcesser cp = new commandProcesser();

    Logger logger = Logger.getLogger(simpleServer.class.getName());

    public simpleServer() {
        try {

            ss = new ServerSocket(PORT);
            System.out.println("EM-SERVER started at port\t" + PORT + "\n");

            //  CREATE AN INFINITE LOOP TO ACCEPT CLIENTS.

            while (true) {

                s = new Socket();
                s = ss.accept();

                System.out.println("Socket connected!: "+ s.getRemoteSocketAddress());
                logger.log(Level.INFO, "Socket connected!: "+ s.getRemoteSocketAddress());

                if (!ss.isBound()) {
                    ss.bind(s.getLocalSocketAddress());
                }
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                pw = new PrintWriter(s.getOutputStream(), true);



                // GEt THE CLIENT TO LOG IN FIRST~!
                log();

            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void log() {

        try {
            //Scanner sc = new Scanner(System.in);

           // System.out.println("INTERNAL : Getting username");
            logger.log(Level.INFO, "Getting username");

            String un = br.readLine();

            //System.out.println("INTERNAL : Getting Password");
            logger.log(Level.INFO, "Getting Password");

            String pwd = br.readLine();

            // uses the login class here to validate the input from user
            login logi = new login();
            String result = logi.login(un, pwd);


            //prints out the result, if user is accepted or not.

            logger.log(Level.INFO, "Result is : "  + result);

            pw.println(result);

            if(result.equals("accept")){
                mainController(); // IF ACCEPTED, LET THE CLIENT NOW READ AND WRITE TO SERVER.
            }
            else{
             log();
            }
        }
        catch(Exception ioe){
            System.out.println(ioe.getMessage());
        }

    }

    public void mainController(){
        try {

            while(!(clmsg=br.readLine()).equals("end"))
            {
                commandReader();
            }

           // System.out.println("Client socket closed " + s.getLocalSocketAddress());
            logger.log(Level.INFO, "Client socket closed " + s.getLocalSocketAddress());

            s.close();
            br.close();
            pw.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void commandReader(){
        try {

                System.out.println("Recieved a command! ");
                System.out.println(clmsg);

                String[] cmd = clmsg.split("\\s");


                // LS command to show files and folders in a directory
                if (cmd[0].equals("ls")) {

                    String allFiles[]= cp.ls();

                    for(String a:allFiles){
                        pw.println(a);
                    }

                    pw.println((String) null);

                }

                 else if (cmd[0].equals("pwd")) {

                    pw.println(cp.pwd());
                    pw.println((String) null);
                }

                else if(cmd[0].equals("switch")){
                    log();
                }

                else if(cmd[0].equals("upload")){
                    getFromClient get = new getFromClient(cmd[1], PORT);

                }
                else if(cmd[0].equals("download")){
                    sendToClient send = new sendToClient(cmd[1], PORT);
                }

                else {
                    pw.println("Command not understood");
                    pw.println((String) null);

                   logger.log(Level.WARNING, "Command not understood");
                    System.out.println("INTERNAL : Command not understood");

                }

        }

        catch(Exception exp){
            System.out.println(exp.getMessage());
        }

    }

}
