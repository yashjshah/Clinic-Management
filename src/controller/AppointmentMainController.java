package controller;

import DataBase.DataSource;
import Main.Signin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.ClinicManagementController.showError;

/**
 * Created by mohamed on 11/10/2017.
 */
public class AppointmentMainController implements Initializable {

    Signin su;
    Stage stage;
    public  void main( Signin su,Stage stage){
        this.stage=stage;
        this.su=su;

    }

    private static PreparedStatement stat = null;

    ResultSet result;

    DataSource dataSource;

    public AppointmentMainController(){

        dataSource=new DataSource();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void bookAction(ActionEvent event) {
        su.appointmentMaanagementWindow();
    }

    @FXML
    void cancelAction(ActionEvent event) {
    	
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conformation");
        alert.setHeaderText("Confirm");
        alert.setContentText("Do you want to delete your appointments ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            String sql = "DELETE FROM test.appointments WHERE name='"+ SigninController.nameforHome+"' ";
            try {

                stat = dataSource.getConnection().prepareStatement(sql);
                stat.executeUpdate();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("information");
                alert1.setHeaderText("info");
                alert1.setContentText("appointments deleted successfully ..");
                alert1.show();

            } catch (SQLException e) {
                e.printStackTrace();
                showError(e.getMessage());
            } catch (NumberFormatException f) {
                showError(f.getMessage());

            } catch (NullPointerException l) {
                showError(l.getMessage());

            } finally {
                try {
                    stat.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }


        } else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

}
