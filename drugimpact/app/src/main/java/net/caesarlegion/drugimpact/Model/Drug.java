package net.caesarlegion.drugimpact.Model;

<<<<<<< HEAD
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by Maru on 2017-11-27.
 */

public class Drug {
    /*========== Class Fields =============================*/
    private int drugId;
    private String name;
    private String url;
    /*=====================================================*/

    /*========== Constructors =============================*/
    public Drug() {}
    /*=====================================================*/

    /*========== Getters and Setters ======================*/
    public int getDrugId() {
        return drugId;
    }

    public Drug setDrugId(int drugId) {
        this.drugId = drugId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Drug setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Drug setUrl(String url) {
        this.url = url;
        return this;
    }
    /*========================================================*/


    /*============== Methods =================================*/
    public static Drug parse(String json) {
        Gson gson = new GsonBuilder()
                        .create();

        return gson.fromJson(json, Drug.class);
    }

    /*========================================================*/
=======
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
>>>>>>> master
}
