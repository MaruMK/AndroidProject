package net.caesarlegion.drugimpact.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Data for the DrugSafety class, will be used to
 * Created by Maxime on 2017-12-09.
 */

public class DrugSafetyData {

    final static int NO_WARNING = 0;
    final static int WARNING = 1;
    final static int LETHAL = 2;

    final static int MIN_REALISTIC_WEIGHT = 0;
    final static int MAX_REALISTIC_WEIGHT = 1000;

    //This part will contain the constants for every drugs
    final static long ALCOHOL_ID = 1;

    final static double OZ_TO_ML = 29.5735;

    private static List<DrugSafety> data;

    static {
        data = new ArrayList<>();
        data.add(new DrugSafety(ALCOHOL_ID,
                                2,
                                0.016,
                                0,
                                1,
                                "Driving significantly impaired"
                ));
        data.add(new DrugSafety(ALCOHOL_ID,
                3,
                0.016,
                121,
                1,
                "Driving significantly impaired"
        ));
    }

    //LIQUID SUBSTANCES *******************************************************************************************************************************
    //These methods will convert back and forth between ounces
    //and millileters.
    public static double OzToMl(double ounces){
        return ounces*OZ_TO_ML;
    }
    public static double MlToOz(double milliliters){
        return milliliters/OZ_TO_ML;
    }
    //*************************************************************************************************************************************************

    //ALCOHOL SHENANIGANS *****************************************************************************************************************************

    final static double ALCOHOL_PER_DRINK = 17.75;

    //This method will convert an amount of alcohol in milliliters to a number of "drinks"
    //One drink is equivalent to:
    //12oz of 5% alcohol (beer)
    //5oz of 12% alcohol (wine)
    //1.5oz of 40% alcohol (hard liquor)
    public static double ConvertAlcoholVolumeToDrinks(double milliliters, double percentage){
        return milliliters*percentage/ALCOHOL_PER_DRINK;
    }
    //*************************************************************************************************************************************************
}
