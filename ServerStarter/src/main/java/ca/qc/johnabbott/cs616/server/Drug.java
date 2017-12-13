package ca.qc.johnabbott.cs616.server;


import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long drugId;

    private String name;
    private String url;

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return name;
    }

    public void setDrugName(String drugName) {
        this.name = drugName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
