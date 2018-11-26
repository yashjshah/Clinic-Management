package controller;

import DataBase.DataSource;
import Main.Signin;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.patientModel;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */

public class ClinicManagementController implements Initializable {

    @FXML
    private JFXTreeTableView<patientModel> tableView;
    ObservableList<patientModel> patientList;
    @FXML
    private TextField searchTF;

    @FXML
    private TextField doctorNameTF, patientNameTF, ageTF;
    @FXML
    private TextArea treatmentTF, diagnosisTF;

    @FXML
    private GridPane InsertGridPane;




    @FXML
    private DatePicker datePicker;
    String Pname, Dname, Page, Pdate, Pdiagnosis, Ptreatment,Pappointment;

    private static PreparedStatement stat = null;

    private static String sqlInsert;
    ResultSet result;

    static DataSource dataSource;

    public ClinicManagementController(){

        dataSource=new DataSource();
    }

    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    Signin signin;
    Stage stage;

    public void main(Signin signin, Stage stage) {
        this.signin = signin;
        this.stage = stage;
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {




        TreeTableColumn<patientModel, String> PNcolumn = new TreeTableColumn<>("Pateint Name");

        PNcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().patientName;

            }
        });

        TreeTableColumn<patientModel, String> DNcolumn = new TreeTableColumn<>("Doctor Name");

        DNcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().doctorName;

            }
        });

        TreeTableColumn<patientModel, String> Agecolumn = new TreeTableColumn<>("Age");

        Agecolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().Age;

            }
        });

        TreeTableColumn<patientModel, String> Datecolumn = new TreeTableColumn<>("Date");

        Datecolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().Date;

            }
        });

        TreeTableColumn<patientModel, String> Diagnosiscolumn = new TreeTableColumn<>("Diagnosis");

        Diagnosiscolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().diagnosis;

            }
        });

        TreeTableColumn<patientModel, String> Teatmentcolumn = new TreeTableColumn<>("Treatment");
        Teatmentcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().treatment;

            }
        });



        patientList = FXCollections.observableArrayList();
        addrowsToTable();

       TreeItem<patientModel> root = new RecursiveTreeItem<patientModel>(patientList, RecursiveTreeObject::getChildren);
        tableView.getColumns().addAll(PNcolumn, DNcolumn, Agecolumn, Datecolumn, Diagnosiscolumn, Teatmentcolumn);
        tableView.setRoot(root);
       tableView.setShowRoot(false);

        searchTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                tableView.setPredicate(new Predicate<TreeItem<patientModel>>() {

                    @Override
                    public boolean test(TreeItem<patientModel> t) {

                        boolean flag = t.getValue().patientName.getValue().contains(newValue)
                                || t.getValue().doctorName.getValue().contains(newValue)
                                || t.getValue().Age.getValue().contains(newValue)
                                || t.getValue().Date.getValue().contains(newValue)
                                || t.getValue().diagnosis.getValue().contains(newValue)
                                || t.getValue().treatment.getValue().contains(newValue);
                        return flag;

                    }
                });
            }

        });

        tableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );
    }

    public void showDetails(TreeItem<patientModel> pModel) {

        patientNameTF.setText(pModel.getValue().getPatientName());
        doctorNameTF.setText(pModel.getValue().getDoctorName());
        ageTF.setText(pModel.getValue().getAge());
        datePicker.setValue(LocalDate.parse(pModel.getValue().getDate()));
        diagnosisTF.setText(pModel.getValue().getDiagnosis());
        treatmentTF.setText(pModel.getValue().getTreatment());



        Pname = pModel.getValue().getPatientName();
        Dname = pModel.getValue().getDoctorName();
        Page = pModel.getValue().getAge();
        Pdate = pModel.getValue().getDate();
        Pdiagnosis = pModel.getValue().getDiagnosis();
        Ptreatment = pModel.getValue().getTreatment();

    }

    private static void insert(String patientname, String doctorname, int age, String date, String diagnosis, String treatment) {
        try {

            sqlInsert = "INSERT INTO test." + adminMainWindowController.tableName + "(patientname,doctorname,age,date,diagnosis,treatment) VALUES (?,?,?,?,?,?)";


            stat = dataSource.getConnection().prepareStatement(sqlInsert);

            stat.setString(1, patientname);
            stat.setString(2, doctorname);
            stat.setInt(3, age);
            stat.setString(4, date);
            stat.setString(5, diagnosis);
            stat.setString(6, treatment);

            stat.executeUpdate();

        } catch (SQLException ex) {
            showError(ex.getMessage());
        } catch (NumberFormatException c) {
            showError(c.getMessage());

        } catch (NullPointerException cc) {
            showError(cc.getMessage());

        }
        catch (Error e) {
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

    @FXML
    void insertPatientData(ActionEvent event) {
        try {

            insert(patientNameTF.getText(), doctorNameTF.getText(), Integer.parseInt(ageTF.getText()), datePicker.getValue().toString(), diagnosisTF.getText(), treatmentTF.getText());
            patientList.add(new patientModel(patientNameTF.getText(),
            doctorNameTF.getText(), ageTF.getText(), datePicker.getValue().toString(), diagnosisTF.getText(), treatmentTF.getText()));

            addrowsToTable();
        }
        catch (NullPointerException cc) {
            showError("Please , All inputs are requires");

        } 
        catch (NumberFormatException c) {
            showError("Age must be number");
            
        } catch (Error e) {
            showError("appointment duplicate value");
        } catch (Exception f) {
            showError(f.getMessage());

        }

    }

    void addrowsToTable() {

        patientList.removeAll(patientList);

        String sqlSelect = "select * from test." + adminMainWindowController.tableName + " ";

        try {


            stat = dataSource.getConnection().prepareStatement(sqlSelect);

            result = stat.executeQuery();

            while (result.next()) {
                patientList.add(new patientModel(result.getString(1), result.getString(2), result.getInt(3) + "", result.getString(4), result.getString(5), result.getString(6)));

            }
        } catch (SQLException r) {
            showError(r.getMessage());
        } catch (NullPointerException l) {
            showError(l.getMessage());
        } finally {
            try {
                stat.close();
            } catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }

    }

    @FXML
    void delelePatientRow(ActionEvent event) {
        try {
            TreeItem<patientModel> model = tableView.getSelectionModel().getSelectedItem();
            String sqlSelect = "delete  from test." + adminMainWindowController.tableName + " where patientname='" + model.getValue().getPatientName() + "' ";
            stat = dataSource.getConnection().prepareStatement(sqlSelect);
            stat.executeUpdate();
            clear();
            patientList.remove(tableView.getSelectionModel().getSelectedIndex());

        } catch (SQLException r) {
            showError(r.getMessage());
        } catch (NullPointerException l) {
            showError(l.getMessage());
        } finally {
            try {
                stat.close();
            } catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }

    }

    @FXML
    void updatePatientRow(ActionEvent event) {

        int index = tableView.getSelectionModel().getSelectedIndex();
        TreeItem<patientModel> pModel = tableView.getSelectionModel().getSelectedItem();

        patientModel PatientModel = new patientModel(patientNameTF.getText(), doctorNameTF.getText(), ageTF.getText(), datePicker.getValue().toString(), diagnosisTF.getText(), treatmentTF.getText());
        pModel.setValue(PatientModel);

        String sqlUpdat = "UPDATE  test." + adminMainWindowController.tableName + " SET patientname='" + patientNameTF.getText() + "' ,doctorname='" + doctorNameTF.getText() + "' , "
                + "  age='" + ageTF.getText() + "',date='" + datePicker.getValue().toString() + "',diagnosis='" + diagnosisTF.getText() + "', treatment='" + treatmentTF.getText() + "' "
                + " WHERE patientname='" + Pname + "' and" + " doctorname='" + Dname + "' and"
                + " age='" + Integer.parseInt(Page) + "' and"
                + " date='" + Pdate + "' and"
                + " diagnosis='" + Pdiagnosis + "' and"
                + " treatment='" + Ptreatment + "' ";
        try {

            stat = dataSource.getConnection().prepareStatement(sqlUpdat);
            stat.executeUpdate();

        } catch (SQLException e) {
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
    }

    @FXML
    void clearFields(ActionEvent event) {
        clear();

    }

    public void clear() {

        patientNameTF.setText(null);
        ageTF.setText(null);
        doctorNameTF.setText(null);
        datePicker.setValue(null);
        diagnosisTF.setText(null);
        treatmentTF.setText(null);

    }

    @FXML
    void deleteAll(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confimation");
        
        alert.setHeaderText("Confirmation");
        alert.setContentText("Make sure that You Will delete all patient data ... ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                patientList.removeAll(patientList);
                String sqlSelect = "delete  from test." + adminMainWindowController.tableName;

                stat = dataSource.getConnection().prepareStatement(sqlSelect);
                stat.executeUpdate();
                clear();
            } catch (SQLException r) {
                showError(r.getMessage());
            } catch (NullPointerException l) {
                showError(l.getMessage());
            } finally {
                try {
                    stat.close();
                } catch (SQLException rr) {
                    showError(rr.getMessage());
                }
            }
        } else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    @FXML
    void back(ActionEvent event) {
        signin.clinicsWindow();
        signin.SubClinicWindowClose();
    }

}
