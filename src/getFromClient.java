import java.io.*;
import java.net.*;

public class getFromClient extends clientfileTranser{
    ServerSocket fileSS ;
    Socket fileS;

    public getFromClient(String fileName, int port) {


        try {
            // try to get the file from client in the same socket at the same port.

            System.out.println("Uploading file from client.");
            fileSS = new ServerSocket(port+1);
            fileS = new Socket();

            fileS = fileSS.accept();

            File f = new File(".\\DownloadedFiles\\"+fileName);
            FileOutputStream fis = new FileOutputStream(f);
            InputStream is = fileS.getInputStream();

            System.out.println("Client is uploading file "+ fileName);
            byte buffer[] = new byte[1024];

            while(is.read(buffer) > 0){
                fis.write(buffer);
            }

            System.out.println("File uploaded from client.");

            fis.close();
            is.close();
            fileS.close();
            fileSS.close();


        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
