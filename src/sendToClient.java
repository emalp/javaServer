import java.io.File;
import java.io.FileOutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class sendToClient extends clientfileTranser{

   private ServerSocket fileSS ;
   private Socket fileS;

    public sendToClient(String fileName, int port){

        try {
            // try to get the file from client in the same socket at the same port.

            System.out.println("Sending file to client.");
            fileSS = new ServerSocket(port+1);
            fileS = new Socket();

            fileS = fileSS.accept();

            File f = new File(".\\UploadFiles\\"+fileName);
            FileInputStream fis = new FileInputStream(f);
            OutputStream os = fileS.getOutputStream();

            System.out.println("Client is downloading file "+ fileName);
            byte buffer[] = new byte[1024];

            while(fis.read(buffer) > 0){
                os.write(buffer);
            }

            System.out.println("File downloaded to client.");

            fis.close();
            os.close();
            fileS.close();
            fileSS.close();


        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
