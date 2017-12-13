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
    private long drugId;

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

}
