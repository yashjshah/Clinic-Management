package controller;

import DataBase.DataSource;
import Main.Signin;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.AppointmentModel;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.signupFXMLConroller.showError;

/**
 * Created by mohamed on 11/10/2017.
 */
public class AppointmentsController implements Initializable {
    @FXML
    private TextField nameTF;

    @FXML
    private TreeTableColumn<AppointmentModel, String> treamentCol;

    @FXML
    private TreeTableColumn<AppointmentModel, String> dateCol;

    @FXML
    private TreeTableColumn<AppointmentModel, String> nameCol;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> treatmentCombo;

    @FXML
    private JFXTreeTableView<AppointmentModel >treeTableView;

    ObservableList<AppointmentModel> list;

    Signin su;
    Stage stage;
    public  void main( Signin su,Stage stage){
        this.stage=stage;
        this.su=su;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        treatmentCombo.getItems().add("Internal Medicine");
        treatmentCombo.getItems().add("Physiotherapy");
        treatmentCombo.getItems().add("Neurology");
        treatmentCombo.getItems().add("cardiology");
        treatmentCombo.getItems().add("Ear and Nose");
        treatmentCombo.getItems().add("Orthopedics");
        treatmentCombo.getItems().add("Dermatology");
        treatmentCombo.getItems().add("Ophthalmology");
        treatmentCombo.getItems().add("Dental");

        list= FXCollections.observableArrayList();

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AppointmentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AppointmentModel, String> param) {
                return param.getValue().getValue().name;
            }
        });

        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AppointmentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AppointmentModel, String> param) {
                return param.getValue().getValue().date;
            }
        });

        treamentCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AppointmentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AppointmentModel, String> param) {
                return param.getValue().getValue().treatment;
            }
        });



        TreeItem<AppointmentModel> root = new RecursiveTreeItem<AppointmentModel>(list, RecursiveTreeObject::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
         dataSource=new DataSource();

        addrowsToTable();


    }
    DataSource dataSource;
    PreparedStatement stat;
    ResultSet result;


    void addrowsToTable() {

        String sqlSelect = "select * from test.appointments WHERE  name='"+ SigninController.nameforHome+"'";

        try {

            stat = dataSource.getConnection().prepareStatement(sqlSelect);
            result = stat.executeQuery();

            while (result.next()) {
                list.add(new AppointmentModel(result.getString(2), result.getString(4),result.getString(3)));

            }
        } catch (SQLException r) {
            ClinicManagementController.showError(r.getMessage());
        } catch (NullPointerException l) {
            ClinicManagementController.showError(l.getMessage());
        } finally {
            try {
                stat.close();
            } catch (SQLException rr) {
                ClinicManagementController.showError(rr.getMessage());
            }
        }

    }

    @FXML
    void addAppointment(ActionEvent event) {

           String sqlInsert = "INSERT INTO test.appointments(name,doctorName,date,treatment) VALUES (?,?,?,?)";

            try {

                stat =dataSource.getConnection() .prepareStatement(sqlInsert);
                stat.setString(1, SigninController.nameforHome);
                stat.setString(2, nameTF.getText());
                stat.setString(3, datePicker.getValue().toString());
                stat.setString(4, treatmentCombo.getSelectionModel().getSelectedItem());
                stat.executeUpdate();

                list.addAll(new AppointmentModel(nameTF.getText(),datePicker.getValue().toString(),treatmentCombo.getSelectionModel().getSelectedItem()));

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
