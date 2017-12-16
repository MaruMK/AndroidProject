package ca.qc.johnabbott.cs616.server;


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

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

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
    }
}
