package ca.qc.johnabbott.cs616.server;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

/**
 * Created by Gabriel Charlebois on 2017-12-10.
 */
@Entity
public class DrugData {

    @Id
    private long drugId;
    private Integer count;

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
