package net.caesarlegion.drugimpact.Model;

/**
 * Created by 1521039 on 2017-12-07.
 */

//This class will be used to contain all the specifics for each substance
    //based on weight and amount taken

public class DrugSafety {

    public long SubstanceId;
    public double Amount;
    public double MetabolizationRate;
    public double WeightMin;
    public int WarningLevel;
    public String WarningMessage;


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
}
