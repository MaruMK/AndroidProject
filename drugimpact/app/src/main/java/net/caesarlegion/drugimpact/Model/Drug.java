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
}
