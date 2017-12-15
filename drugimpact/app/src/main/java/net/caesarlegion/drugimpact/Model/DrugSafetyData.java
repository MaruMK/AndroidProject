package net.caesarlegion.drugimpact.Model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public final static int WEIGHT_FOR_TESTING = 150;

    //This part will contain the constants for every drugs
    public final static long ALCOHOL_ID = 1;
    public final static long CAFFEINE_ID = 2;
    public final static long NICOTINE_ID = 3;

    final static double OZ_TO_ML = 29.5735;

    public static String EMERGENCY_NUMBER;
    public static String EMERGENCY_MESSAGE;

    public static List<Drug> knownDrugs;
    static {

        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        knownDrugs = new ArrayList<>();
        knownDrugs.add(new Drug(DrugSafetyData.ALCOHOL_ID,
                "Alcohol",
                "Drinks"));
        knownDrugs.add(new Drug(DrugSafetyData.CAFFEINE_ID,
                "Caffeine",
                "mg"));
        knownDrugs.add(new Drug(DrugSafetyData.NICOTINE_ID,
                "Nicotine",
                "Cigarettes"));
    }


    //Accepts a drug id as input and returns the matching
    //drug object
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
                0,
                0.5,
                0,
                NO_WARNING,
                ""
        ));
        data.add(new DrugSafety(ALCOHOL_ID,
                2,
                0.5,
                0,
                WARNING,
                "Driving significantly impaired"
                ));
        data.add(new DrugSafety(ALCOHOL_ID,
                3,
                0.5,
                120,
                WARNING,
                "Driving significantly impaired"
        ));
        data.add(new DrugSafety(ALCOHOL_ID,
                6,
                0.5,
                0,
                WARNING,
                "You are now considered legally intoxicated"
        ));
        data.add(new DrugSafety(ALCOHOL_ID,
                10,
                0.5,
                0,
                LETHAL,
                "DANGER: Possible death"
        ));
        data.add(new DrugSafety(CAFFEINE_ID,
                0,
                (5),
                0,
                NO_WARNING,
                ""));
        data.add(new DrugSafety(CAFFEINE_ID,
                 50,
                (10),
                0,
                NO_WARNING,
                ""));
        data.add(new DrugSafety(CAFFEINE_ID,
                200,
                (15),
                0,
                WARNING,
                "You've reached the maximum recommended daily caffeine dosage"));

        data.add(new DrugSafety(CAFFEINE_ID,
                400,
                (15),
                0,
                LETHAL,
                "DANGER: Your risks of heart issues are elevated"));
        data.add(new DrugSafety(NICOTINE_ID,
                 0,
                1,
                0,
                NO_WARNING,
                ""));
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

    //ALL SUBSTANCES FUNCTIONS ************************************************************************************************************************
    public static int CalculateSecondsTillSober(History h){
        DrugSafety currentSafetyBracket;
        int seconds = 0;
        double amountLeft = h.getAmount();
        while(amountLeft > 0){
             currentSafetyBracket = FindSafetyBracket(h.getDrugId(), amountLeft, WEIGHT_FOR_TESTING);
             if(h.getDrugId() == CAFFEINE_ID){
                seconds = (int)currentSafetyBracket.getMetabolizationRate()*60*60;
                seconds = seconds + (int)(h.getTimeOfConsumption().getTime()/1000 - new Date().getTime()/1000);
                amountLeft = 0;
             }
             else {
                 if (currentSafetyBracket.getMetabolizationRate() <= amountLeft) {
                     amountLeft -= currentSafetyBracket.getMetabolizationRate();
                     seconds += 60 * 60;
                 } else {
                     seconds += amountLeft * 60 * 60;
                     amountLeft = 0;
                 }
             }
        }
        return seconds;
    }

    //This function will take a substance id, the amount consumed and the weight of the user and
    //it will find the correct data associated with it
    public static DrugSafety FindSafetyBracket(long substanceId, double amount, double weight){
        DrugSafety dsMatch = new DrugSafety();
        for (DrugSafety ds:
             data) {
            if(substanceId == ds.getSubstanceId()){
                if(amount > ds.getAmount()){
                    if(weight > ds.getWeightMin()){
                        dsMatch = ds;
                    }
                }
            }
        }
        return dsMatch;
    }
    //*************************************************************************************************************************************************

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
