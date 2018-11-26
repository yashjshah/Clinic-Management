package interfaces;

import Main.Signin;

/**
 * Created by mohamed on 11/10/2017.
 */
public interface UserInterface {

    public void signin(String username, String password, Signin signin);
    public  void insert(String name, String user_name, String password,Signin signin);

}
