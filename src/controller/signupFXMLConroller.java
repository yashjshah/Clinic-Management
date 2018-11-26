package controller;

import DataBase.DataSource;
import Main.Signin;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 *
 * @author
 */

public class signupFXMLConroller implements Initializable {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    Stage stage;
    static Signin s;

    public void main(Signin s) {
        signupFXMLConroller.s = s;

    }
    static DataSource dataSource;

    public signupFXMLConroller(){

        dataSource=new DataSource();
    }

    private static PreparedStatement stat = null;


    private static String sqlInsert;



    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign up ");
        alert.setHeaderText("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    




    @FXML
    void regist(ActionEvent event) {
        try {
            if (firstName.getText().isEmpty() || lastName.getText().isEmpty()
                    | userName.getText().isEmpty() || password.getText().isEmpty()) {


            showError("Please fill all fields");
                
            } else {

                new UserService().insert(firstName.getText().toString() + " " + lastName.getText().toString(), userName.getText().toString(), password.getText().toString(),s);

            }
        } catch (NumberFormatException c) {
            showError(c.getMessage());

        } catch (NullPointerException cc) {
            showError("Please , Fill all fields");

        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());

        }
    }

    @FXML
    void cancelAction(ActionEvent event) {
        s.signUPClose();
        s.signinWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
