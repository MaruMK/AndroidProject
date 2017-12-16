package net.caesarlegion.drugimpact.Model;


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
    private String unit;

    /*=====================================================*/

    /*========== Constructors =============================*/
    public Drug() {}

    public Drug(long id, String name, String unit) {
        this.drugId = (int) id;
        this.name = name;
        this.unit = unit;
    }

    public Drug(long id, String name, String unit, String url){
        this.drugId = (int) id;
        this.name = name;
        this.unit = unit;
        this.url = url;
    }
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    /*========================================================*/


    /*============== Methods =================================*/
    public static Drug parse(String json) {
        Gson gson = new GsonBuilder()
                .create();

        return gson.fromJson(json, Drug.class);
    }

    /*========================================================*/
}