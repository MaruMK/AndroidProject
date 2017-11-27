package net.caesarlegion.drugimpact.Model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maru on 2017-11-27.
 */

public class Druglist {
    private static List<Drug> drugList;

    static {
        drugList = new ArrayList<>();

        // Temporary data
        drugList.add(new Drug()
                .setDrugID(1)
                .setTypeID(1)
                .setName("AAA")
                .setDescription("AAA desc")
        );
        drugList.add(new Drug()
                .setDrugID(2)
                .setTypeID(2)
                .setName("AAB")
                .setDescription("AAB desc")
        );
        drugList.add(new Drug()
                .setDrugID(3)
                .setTypeID(2)
                .setName("AAC")
                .setDescription("AAC desc")
        );
        drugList.add(new Drug()
                .setDrugID(4)
                .setTypeID(4)
                .setName("AB")
                .setDescription("AB desc")
        );
        drugList.add(new Drug()
                .setDrugID(5)
                .setTypeID(5)
                .setName("ABA")
                .setDescription("ABA desc")
        );
        drugList.add(new Drug()
                .setDrugID(6)
                .setTypeID(6)
                .setName("ABB")
                .setDescription("ABB desc")
        );
        drugList.add(new Drug()
                .setDrugID(7)
                .setTypeID(7)
                .setName("AC")
                .setDescription("AC desc")
        );
        drugList.add(new Drug()
                .setDrugID(8)
                .setTypeID(8)
                .setName("ACA")
                .setDescription("ACA desc")
        );
        drugList.add(new Drug()
                .setDrugID(9)
                .setTypeID(9)
                .setName("ACB")
                .setDescription("ACB desc")
        );
        drugList.add(new Drug()
                .setDrugID(10)
                .setTypeID(10)
                .setName("AF")
                .setDescription("AF desc")
        );
    }



    public static List<Drug> getData() {
        return drugList;
    }
}
