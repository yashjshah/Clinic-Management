package controller;

import Main.Signin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author
 */
public class adminMainWindowController implements Initializable {


    @FXML
    private ImageView profile;

    Signin signin;
    Stage stage;
    public static String tableName = "";

    public void main(Signin signin, Stage stage) {
        this.signin = signin;
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        profile.setOnMouseClicked(event -> {
            signin.profileWindow();
        });


    }

    @FXML
    public void OpenClinicWindow0(ActionEvent event) {
        try {

            tableName = "internalmedicine";
            signin.clinicName = "Internal Medicine";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();
        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow1(ActionEvent event) {
        try {
            tableName = "physiotherapy";
            signin.clinicName = "Physiotherapy";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow2(ActionEvent event) {
        try {
            tableName = "neurology";
            signin.clinicName = "Neurology";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow3(ActionEvent event) {
        try {
            tableName = "cardiology";
            signin.clinicName = "cardiology";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow4(ActionEvent event) {
        try {
            tableName = "earnose";
            signin.clinicName = "Ear and Nose";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow5(ActionEvent event) {
        try {
            tableName = "orthopedics";
            signin.clinicName = "Orthopedics";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow6(ActionEvent event) {
        try {
            tableName = "dentalclinic";
            signin.clinicName = "Dental";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow7(ActionEvent event) {
        try {
            tableName = "dermatology";
            signin.clinicName = "Dermatology";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow8(ActionEvent event) {
        try {
            tableName = "ophthalmology";
            signin.clinicName = "Ophthalmology";
            signin.SubClinicWindow();
            Signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }
}
