package net.caesarlegion.drugimpact.Model;

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
                .setDrugId(1)
                .setName("AAA")
                .setUrl("AAA desc")
        );
        drugList.add(new Drug()
                .setDrugId(2)
                .setName("AAB")
                .setUrl("AAB desc")
        );
        drugList.add(new Drug()
                .setDrugId(3)
                .setName("AAC")
                .setUrl("AAC desc")
        );
        drugList.add(new Drug()
                .setDrugId(4)
                .setName("AB")
                .setUrl("AB desc")
        );
        drugList.add(new Drug()
                .setDrugId(5)
                .setName("ABA")
                .setUrl("ABA desc")
        );
        drugList.add(new Drug()
                .setDrugId(6)
                .setName("ABB")
                .setUrl("ABB desc")
        );
        drugList.add(new Drug()
                .setDrugId(7)
                .setName("AC")
                .setUrl("AC desc")
        );
        drugList.add(new Drug()
                .setDrugId(8)
                .setName("ACA")
                .setUrl("ACA desc")
        );
        drugList.add(new Drug()
                .setDrugId(9)
                .setName("ACB")
                .setUrl("ACB desc")
        );
        drugList.add(new Drug()
                .setDrugId(10)
                .setName("AF")
                .setUrl("AF desc")
        );
    }



    public static List<Drug> getData() {
        return drugList;
    }
}