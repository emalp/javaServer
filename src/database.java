
import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class database {

    ArrayList<String > names = new ArrayList<>(10);
    ArrayList<String > password = new ArrayList<>(10);

    File file =new File("data.csv");
    File ini;

    FileReader anoth;
    BufferedReader br;

    BufferedWriter bw;
    FileWriter an;

    PrintWriter p;

    String totalline;


    public  void start() throws Exception{


        anoth = new FileReader(file);
        br = new BufferedReader(anoth);

        String username;
        String pas;


        String aa[] = new String[2];

        while((totalline = br.readLine()) != null){
            aa= totalline.split(",");

            username = aa[0];
            pas = aa[1];

            names.add(username);
            password.add(pas);

            System.out.println();
        }

        anoth.close();
        br.close();

    }

    public boolean add(String user, String pass){

        try {
            an = new FileWriter("data.csv", true);
            bw = new BufferedWriter(an);

            p = new PrintWriter(bw);

            String completeLine = user + "," + pass;

            System.out.println(completeLine);

            p.println();
            p.append(completeLine);
            p.flush();

            return true;

        }
        catch(Exception ex){
            System.out.println("This is in the add method of database"+ex);
            return false;
        }

    }

    public void remove(String user){

        try {
            ini = new File("newdata.csv");
            ini.createNewFile();

                anoth = new FileReader(file);

                br = new BufferedReader(anoth);

                PrintWriter p2 = new PrintWriter(ini);

                String aa[] = new String[2];

                if (hasUser(user)) {

                while ((totalline = br.readLine()) != null) {
                    aa = totalline.split(",");
                    if (aa[0].equals(user)) {
                        System.out.println("User deleted!");
                       // p2.print("");

                    } else {
                        p2.println(totalline);
                    }
                }
            } else {
                System.out.println("There is no such user");
            }

            anoth.close();
            br.close();
            p2.flush();
            p2.close();

            boolean t = file.delete();
            boolean sx = ini.renameTo(file);

            if(!sx){
                System.out.println("Could not rename back the file");
            }

            System.out.println("User deletion complete.\n");

        }
        catch(Exception ex){
            System.out.println("Inside datase remove: " + ex);
        }
    }

    public boolean hasUser(String username){

        boolean result;


        if (this.names.contains(username)) {
            result = true;
        } else {

            result = false;
        }

        return result;
    }

}
