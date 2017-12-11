package net.caesarlegion.drugimpact.Model;

/**
 * Created by Maru on 2017-11-27.
 */

public class Drug {
    /*========== Class Fields =============================*/
    private int DrugID;
    private int TypeID;
    private String Name;
    private String Description;
    /*=====================================================*/

    /*========== Constructors =============================*/
    public Drug() {}
    /*=====================================================*/

    /*========== Getters and Setters ======================*/
    public int getDrugID() {
        return DrugID;
    }

    public Drug setDrugID(int drugID) {
        DrugID = drugID;
        return this;
    }

    public int getTypeID() {
        return TypeID;
    }

    public Drug setTypeID(int typeID) {
        TypeID = typeID;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Drug setName(String name) {
        Name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public Drug setDescription(String description) {
        Description = description;
        return this;
    }
    /*========================================================*/


}
