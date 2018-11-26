package controller;

import Main.Signin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author
 */
public class aboutController implements Initializable{
 @FXML
    private ImageView twitter;

    @FXML
    private ImageView in;

    @FXML
    private ImageView fb;

    
 Signin su;
        Stage stage;
        public  void Main( Signin su,Stage stage){
            this.stage=stage;
        this.su=su;
        }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
