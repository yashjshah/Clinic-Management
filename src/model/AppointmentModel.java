package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by mohamed on 11/10/2017.
 */
public class AppointmentModel extends RecursiveTreeObject<AppointmentModel> {


    public StringProperty name,date,treatment;

    public AppointmentModel(String name, String date, String treatment) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.treatment = new SimpleStringProperty(treatment);
    }

    public String getName() {
        return name.get();
    }



    public String getDate() {
        return date.get();
    }



    public String getTreatment() {
        return treatment.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setTreatment(String treatment) {
        this.treatment.set(treatment);
    }
}
