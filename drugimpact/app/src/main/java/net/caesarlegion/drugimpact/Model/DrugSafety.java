package net.caesarlegion.drugimpact.Model;

/**
 * Created by 1521039 on 2017-12-07.
 */

//This class will be used to contain all the specifics for each substance
    //based on weight and amount taken

public class DrugSafety {
    final int NO_WARNING = 0;
    final int WARNING = 1;
    final int LETHAL = 2;

    public int SubstanceId;
    public double Amount;
    public double WeightRangeMin;
    public double WeightRangeMax;
    public int WarningLevel;

}
