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
>>>>>>> master
    private String name;
    private String url;

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

<<<<<<< HEAD
    public String getDrugName() {
        return name;
    }

    public void setDrugName(String drugName) {
        this.name = drugName;
=======
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
>>>>>>> master
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
