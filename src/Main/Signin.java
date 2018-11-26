package Main;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MOHAMMED
 */
public class Signin extends Application {

    static Stage stageprim, stage1, stage2, stage4, stage3, stage5, stage6,stage7,stage8,stage9;
    public String clinicName = "";

    @Override
    public void start(Stage stage) throws Exception {
        Signin.stageprim = stage;
        signinWindow();
    }




    public void signinWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/Signin.fxml"));
            AnchorPane pane = loader.load();
            SigninController controller = loader.getController();
            stageprim = new Stage();
            controller.Main(this, stageprim);
            Scene scene = new Scene(pane);
            stageprim.setTitle("Sign in");
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stageprim.setResizable(false);
            stageprim.setScene(scene);
            stageprim.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signInClose() {
        stageprim.close();
    }

    public void signupWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/signup.fxml"));
            AnchorPane pane = loader.load();
            signupFXMLConroller controller = loader.getController();
            controller.main(this);
            stage2 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage2.setTitle("Register");
            stage2.setResizable(false);
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUPClose() {
        stage2.close();
    }

    public void clinicsWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/FXML_homeDocument.fxml"));
            AnchorPane pane = loader.load();
            adminMainWindowController controller = loader.getController();
            stage3 = new Stage();
            controller.main(this, stage3);
            Scene scene = new Scene(pane);
            stage3.setTitle("Home");
            stage3.setResizable(true);
            stage3.setScene(scene);
            stage3.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clinicsWindowClose() {
        stage3.close();
    }

    public void SubClinicWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/ManagementWindow.fxml"));
            AnchorPane pane = loader.load();
            ClinicManagementController controller = loader.getController();
            stage4 = new Stage();
            controller.main(this, stage4);
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage4.setTitle(clinicName + " Clinic");
            stage4.setResizable(true);
            stage4.setScene(scene);

            stage4.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void appointmentMainWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/appointment _main.fxml"));
            AnchorPane pane = loader.load();
            AppointmentMainController controller = loader.getController();
            stage8 = new Stage();
            controller.main(this, stage8);
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage8.setTitle("Appointments");
            stage8.setResizable(true);
            stage8.setResizable(false);
            stage8.setScene(scene);

            stage8.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void appointmentMaanagementWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/AppointmentsWindow.fxml"));
            AnchorPane pane = loader.load();
            AppointmentsController controller = loader.getController();
            stage9 = new Stage();
            controller.main(this, stage9);
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage9.setTitle("Book Appointment");
            stage9.initModality(Modality.APPLICATION_MODAL);
            stage9.setResizable(true);
            stage9.setScene(scene);

            stage9.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void SubClinicWindowClose() {
        stage4.close();
    }

    public static void StatisticsWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/statistics.fxml"));
            AnchorPane pane = loader.load();
            StatisticsController controller = loader.getController();
            stage5 = new Stage();
            // controller.Main(this,stage5);      
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage5.setTitle("Statistics");
            stage5.setResizable(true);
            stage5.setScene(scene);
            stage5.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AboutWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/aboutFxmlDocument.fxml"));
            AnchorPane pane = loader.load();
            aboutController controller = loader.getController();
            stage6 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage6.setTitle("About");
            stage6.setResizable(false);
            stage6.setScene(scene);
            stage6.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public  void profileWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/profile.fxml"));
            AnchorPane pane = loader.load();
            stage7 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage7.setTitle("Profile");
            stage7.setResizable(false);
            stage7.initOwner(stageprim);
            stage7.initModality(Modality.WINDOW_MODAL);
            stage7.setScene(scene);
            stage7.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
