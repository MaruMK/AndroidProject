package net.caesarlegion.drugimpact.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Data for the DrugSafety class, will be used to
 * Created by Maxime on 2017-12-09.
 */

public class DrugSafetyData {

    public static final SimpleDateFormat format;

    public final static int NO_WARNING = 0;
    public final static int WARNING = 1;
    public final static int LETHAL = 2;

    final static int MIN_REALISTIC_WEIGHT = 0;

    //This part will contain the constants for every drugs
    public final static long ALCOHOL_ID = 1;
    public final static long CAFFEINE_ID = 2;
    public final static long NICOTINE_CIGARETTE_ID = 3;
    public final static long NICOTINE_VAPORIZER_ID = 4;

    final static double OZ_TO_ML = 29.5735;

    public static List<Drug> knownDrugs;
    static {

        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        knownDrugs = new ArrayList<>();
        knownDrugs.add(new Drug(DrugSafetyData.ALCOHOL_ID,
                "Alcohol",
                "Drinks-Ml"));
        knownDrugs.add(new Drug(DrugSafetyData.CAFFEINE_ID,
                "Caffeine",
                "Cups-Ml"));
        knownDrugs.add(new Drug(DrugSafetyData.NICOTINE_CIGARETTE_ID,
                "Nicotine - Cigarette",
                "Cigarettes"));
        knownDrugs.add(new Drug(DrugSafetyData.NICOTINE_VAPORIZER_ID,
                "Nicotine - Vaporizer",
                "Mg/Ml"));
    }

    public static Drug GetDrugById(long id){
        for(Drug drug : knownDrugs){
            if(drug.getDrugId() == id)
                return drug;
        }
        return new Drug();
    }

    public static List<DrugSafety> data;

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
                120,
                1,
                "Driving significantly impaired"
        ));
    }

    //This list will contain the methods of input for caffeine
    //These will be displayed in the caffeine methods spinner
    public static List<String> caffeineMethods;
    static{
        caffeineMethods = new ArrayList<>();
        caffeineMethods.add("Soft Drink");
        caffeineMethods.add("Coffee - Weak");
        caffeineMethods.add("Coffee - Medium");
        caffeineMethods.add("Coffee - Strong");
        caffeineMethods.add("Energy Drink");
        caffeineMethods.add("Green Tea");
        caffeineMethods.add("Black Tea");
    }

    //LIQUID SUBSTANCES *******************************************************************************************************************************
    //These methods will convert back and forth between ounces
    //and milliliters.
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
    public static double ConvertAlcoholVolumeToDrinks(double milliliters, double concentration){
        return milliliters*(concentration/100)/ALCOHOL_PER_DRINK;
    }
    //*************************************************************************************************************************************************

    //CAFFEINE SHENANIGANS ****************************************************************************************************************************

    //Source: http://energydrink-ca.redbull.com/en/red-bull-caffeine
    final static double CAFFEINE_MG_PER_ML_SOFT_DRINK = 0.13;
    final static double CAFFEINE_MG_PER_ML_WEAK_COFFEE = 0.27;
    final static double CAFFEINE_MG_PER_ML_MEDIUM_COFFEE = 0.36;
    final static double CAFFEINE_MG_PER_ML_STRONG_COFFEE = 0.51;
    final static double CAFFEINE_MG_PER_ML_ENERGY_DRINK = 0.32;
    final static double CAFFEINE_MG_PER_ML_GREEN_TEA = 0.11;
    final static double CAFFEINE_MG_PER_ML_BLACK_TEA = 0.25;

    //This method will return the total amount of caffeine in a given drink in Milliliters
    public static double GetCaffeineInMg(String method, double amount){
        switch (method){
            case "Soft Drink":
                return CAFFEINE_MG_PER_ML_SOFT_DRINK*amount;
            case "Coffee - Weak":
                return CAFFEINE_MG_PER_ML_WEAK_COFFEE*amount;
            case "Coffee - Medium":
                return CAFFEINE_MG_PER_ML_MEDIUM_COFFEE*amount;
            case "Coffee - Strong":
                return CAFFEINE_MG_PER_ML_STRONG_COFFEE*amount;
            case "Energy Drink":
                return CAFFEINE_MG_PER_ML_ENERGY_DRINK*amount;
            case "Green Tea":
                return CAFFEINE_MG_PER_ML_GREEN_TEA*amount;
            case "Black Tea":
                return CAFFEINE_MG_PER_ML_BLACK_TEA*amount;
        }
        return 0.0;
    }

    //*************************************************************************************************************************************************
}
