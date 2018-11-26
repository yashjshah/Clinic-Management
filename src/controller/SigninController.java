package controller;

import Main.Signin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UserService;
/**
 *
 */

public class SigninController implements Initializable {
    @FXML
    private ImageView setting;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ImageView logoImage;
    
    @FXML
    private Button signinBTN;

    @FXML
    private Button signupBTN;

    @FXML
    private TextField usernameTF;

    @FXML
    private Button forgotPasswordBRN;


    UserService userService;
    @FXML
    private PasswordField passwordTF;


    
        public static String usernameforHome="";
        public static String nameforHome="";

        Signin su;
        Stage stage;
        public  void Main( Signin su,Stage stage){
        this.stage=stage;
        this.su=su;
        
        }
    

        
    @FXML
    void signinAction(ActionEvent event) {
        
        userService.signin(usernameTF.getText().toString(),passwordTF.getText().toString(),su);
          
          }
    
      
    
    @FXML
    void signupAction(ActionEvent event) {
        try {
          su.signInClose();
          su.signupWindow();
        } catch (Exception ex) {
        }
    }
   
     
    @Override
	public void initialize(URL url, ResourceBundle rb) {

        


         userService=new UserService();


    }    

   
    
}
