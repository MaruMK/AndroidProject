package net.caesarlegion.drugimpact.Model;

/**
 * Created by 1521039 on 2017-12-07.
 */

//This class will be used to contain all the specifics for each substance
    //based on weight and amount taken

public class DrugSafety {

    private long SubstanceId;
    private double Amount;
    private double MetabolizationRate;
    private double WeightMin;
    private int WarningLevel;
    private String WarningMessage;


    //Default Constructor
    public DrugSafety(){}

    //Constructor used to create a working DrugSafety Entry
    public DrugSafety(long substanceId, double amount, double metabolizationRate, double weightMin, int warningLevel, String warningMessage) {
        SubstanceId = substanceId;
        Amount = amount;
        MetabolizationRate = metabolizationRate;
        WeightMin = weightMin;
        WarningLevel = warningLevel;
        WarningMessage = warningMessage;
    }

    //Getters-Setters ****************************************************************************************************************************
    public long getSubstanceId() {
        return SubstanceId;
    }

    public void setSubstanceId(long substanceId) {
        SubstanceId = substanceId;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getMetabolizationRate() {
        return MetabolizationRate;
    }

    public void setMetabolizationRate(double metabolizationRate) {
        MetabolizationRate = metabolizationRate;
    }

    public double getWeightMin() {
        return WeightMin;
    }

    public void setWeightMin(double weightMin) {
        WeightMin = weightMin;
    }

    public int getWarningLevel() {
        return WarningLevel;
    }

    public void setWarningLevel(int warningLevel) {
        WarningLevel = warningLevel;
    }

    public String getWarningMessage() {
        return WarningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        WarningMessage = warningMessage;
    }
    //********************************************************************************************************************************************
}
