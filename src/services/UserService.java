package services;

import DataBase.DataSource;
import Main.Signin;
import controller.SigninController;
import interfaces.UserInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.signupFXMLConroller.showError;
import static controller.signupFXMLConroller.showInfo;

/**
 * Created by mohamed on 11/10/2017.
 */
public class UserService implements UserInterface {

    DataSource dataSource;
    private static PreparedStatement stat=null;

    private static String sqlInsert;
    ResultSet result;
    public UserService(){

        dataSource=new DataSource();
    }


    @Override
    public void signin(String username, String password, Signin signin) {
        String sqlSelect="select * from test.register where username='"+username+"' and password='"+password+"' ";

        try {

            stat=dataSource.getConnection().prepareStatement(sqlSelect);

            result=stat.executeQuery();

            if (username.isEmpty() || password.isEmpty()) {

                showError("please enter username and password ");

            }

            else{

                if (result.next()){
                    if ((result.getString("username")).equals(username)
                            && (result.getString("password")).equals(password)  &&result.getString("userType").equals("admin") ) {

                        SigninController.nameforHome=result.getString("name");
                        SigninController.usernameforHome=result.getString("username");
                            signin.clinicsWindow();
                            signin.signInClose();



                    }
                    else if ((result.getString("username")).equals(username)
                            && (result.getString("password")).equals(password)  &&result.getString("userType").equals("patient") ) {

                            SigninController.nameforHome=result.getString("name");
                            SigninController.usernameforHome=result.getString("username");
                            signin.appointmentMainWindow();
                            signin.signInClose();

                        }

                    }

                else if (!result.next()) {
                    showError("username or password is invalid ");
                }



            }

        } catch (SQLException r) {
            showError(r.getMessage());
        } catch(NullPointerException l){
            showError(l.getMessage());
        } finally{
            try {
                stat.close();
            }


            catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }

    }

    @Override
    public void insert(String name, String user_name, String password, Signin signin) {

        sqlInsert = "INSERT INTO test.register(name,username,password,userType) VALUES (?,?,?,?)";

        try {

            stat =dataSource.getConnection() .prepareStatement(sqlInsert);

            stat.setString(1, name);
            stat.setString(2, user_name);
            stat.setString(3, password);
            stat.setString(4, "patient");

            stat.executeUpdate();
            showInfo(user_name + " has registered successfully");
            signin.signUPClose();
            signin.signinWindow();

        } catch (SQLException ex) {
            showError(ex.getMessage());
        } catch (NumberFormatException c) {
            showError(c.getMessage());

        } catch (NullPointerException cc) {
            showError(cc.getMessage());

        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());

        } finally {
            try {
                stat.close();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        }
    }
}
