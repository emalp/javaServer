import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class login {

    Logger logger = Logger.getLogger(login.class.getName());

    private String username;
    private String passwod;
    public database dtbse;
    private String result;

    public String login(String uname, String pswd) throws Exception{

        dtbse = new database();
        dtbse.start();

        this.username = uname;
        this.passwod = pswd;

//        int totalNames = dtbse.names.size();

        if (dtbse.names.contains(username)) {
            String r = checkPswd(dtbse.names.indexOf(username));
            result = r;
        } else {
            String r = "no";
            result = r;
        }

        return result;
    }


    public String checkPswd(int indy){

        if(passwod.equals(dtbse.password.get(indy))){
                return("accept");
        }
        else {
            logger.log(Level.WARNING, "Passowrd wrong.");
            System.out.println("returning no as result");
            return("no");

        }

    }

}
