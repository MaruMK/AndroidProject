package net.caesarlegion.drugimpact.Model;

import android.text.method.HideReturnsTransformationMethod;

import java.util.Date;

/**
 * Created by Main on 2017-12-10.
 * A history is a certain amount of a substance on a certain date.
 */

public class History {

    public long ReminderId;
    public long DrugId;
    public double Amount;
    public Date TimeOfConsumption;

    public History() {
        ReminderId = -1;
    }

    public History(long id){
        this.ReminderId = id;
    }

    public History(long drugId, double amount, Date timeOfConsumption) {
        this();
        DrugId = drugId;
        Amount = amount;
        TimeOfConsumption = timeOfConsumption;
    }

    //Getters-Setters ****************************************************************************************************************************

    public long getReminderId() {
        return ReminderId;
    }

    public History setReminderId(long reminderId) {
        ReminderId = reminderId;
        return this;
    }

    public long getDrugId() {
        return DrugId;
    }

    public History setDrugId(long drugId) {
        DrugId = drugId;
        return this;
    }

    public double getAmount() {
        return Amount;
    }

    public History setAmount(double amount) {
        Amount = amount;
        return this;
    }

    public Date getTimeOfConsumption() {
        return TimeOfConsumption;
    }

    public History setTimeOfConsumption(Date timeOfConsumption) {
        TimeOfConsumption = timeOfConsumption;
        return this;
    }
    //********************************************************************************************************************************************
}
