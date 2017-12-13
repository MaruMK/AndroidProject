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
    private Integer drugId;

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

}
