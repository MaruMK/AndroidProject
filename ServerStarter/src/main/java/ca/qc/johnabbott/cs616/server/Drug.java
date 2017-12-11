package ca.qc.johnabbott.cs616.server;

<<<<<<< HEAD

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long drugId;

    private String drugName;
    private String drugUrl;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Gabriel Charlebois on 2017-12-10.
 */
@Entity
public class Drug {


    @Id
    @GeneratedValue
    private long drugId;
    private String name;
    private String url;
>>>>>>> master

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

<<<<<<< HEAD
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugUrl() {
        return drugUrl;
    }

    public void setDrugUrl(String drugUrl) {
        this.drugUrl = drugUrl;
=======
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
>>>>>>> master
    }
}
