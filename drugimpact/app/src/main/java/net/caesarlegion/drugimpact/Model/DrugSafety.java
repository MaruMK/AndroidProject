package net.caesarlegion.drugimpact.Model;

/**
 * Created by 1521039 on 2017-12-07.
 */

//This class will be used to contain all the specifics for each substance
    //based on weight and amount taken

public class DrugSafety {

    public int SubstanceId;
    public double Amount;
    public double WeightRangeMin;
    public double WeightRangeMax;
    public int WarningLevel;
    public String WarningMessage;

    //Default Constructor
    public DrugSafety(){}

    //Constructor used to create a working DrugSafety Entry
    public DrugSafety(int substanceId, double amount, double weightRangeMin, double weightRangeMax, int warningLevel, String warningMessage) {
        SubstanceId = substanceId;
        Amount = amount;
        WeightRangeMin = weightRangeMin;
        WeightRangeMax = weightRangeMax;
        WarningLevel = warningLevel;
        WarningMessage = warningMessage;
    }
}
