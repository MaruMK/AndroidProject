package ca.qc.johnabbott.cs616.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Date;

@Entity
public class Experiences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ExperienceId;

    public String Name;
    public String Drug;
    public String Description;
    public Date TimeStamp;

    public long getExperienceId() {
        return ExperienceId;
    }

    public void setExperienceId(long experienceId) {
        ExperienceId = experienceId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDrug() {
        return Drug;
    }

    public void setDrug(String drug) {
        Drug = drug;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        TimeStamp = timeStamp;
    }
}
