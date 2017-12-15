package net.caesarlegion.drugimpact.Model;

import java.net.URL;

/**
 * Created by Main on 2017-12-10.
 */

//This class will be sued to locally hold
public class Drug {

    private long DrugId;
    private String Name;
    private String Unit;
    private URL url;

    public Drug() { }

    public Drug(long drugId, String name, String unit) {

        DrugId = drugId;
        Name = name;
        Unit = unit;
    }

    public Drug(long drugId, String name, String unit, URL url) {
        DrugId = drugId;
        Name = name;
        Unit = unit;
        this.url = url;
    }

    //Getters-Setters ****************************************************************************************************************************
    public long getDrugId() {
        return DrugId;
    }

    public void setDrugId(long drugId) {
        DrugId = drugId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
    //********************************************************************************************************************************************
}
