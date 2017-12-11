package net.caesarlegion.drugimpact.Model;

import java.util.Date;

/**
 * Created by Main on 2017-12-10.
 */

public class History {

    public long ReminderId;
    public long DrugId;
    public double Amount;
    public Date TimeOfConsumption;

    public History() {
    }

    public History(long reminderId, long drugId, double amount, Date timeOfConsumption) {
        ReminderId = reminderId;
        DrugId = drugId;
        Amount = amount;
        TimeOfConsumption = timeOfConsumption;
    }

    //Getters-Setters ****************************************************************************************************************************

    public long getReminderId() {
        return ReminderId;
    }

    public void setReminderId(long reminderId) {
        ReminderId = reminderId;
    }

    public long getDrugId() {
        return DrugId;
    }

    public void setDrugId(long drugId) {
        DrugId = drugId;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public Date getTimeOfConsumption() {
        return TimeOfConsumption;
    }

    public void setTimeOfConsumption(Date timeOfConsumption) {
        TimeOfConsumption = timeOfConsumption;
    }
    //********************************************************************************************************************************************
}
