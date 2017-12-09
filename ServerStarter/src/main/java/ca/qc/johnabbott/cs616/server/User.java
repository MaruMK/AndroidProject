package ca.qc.johnabbott.cs616.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Gabriel Charlebois on 2017-12-09.
 */
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String email;
    private String pass;
    private String displayName;
    private String dob;
    private double weight;
    private String encryptionKey;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEncryptionKey() { return encryptionKey; }

    public void setEncryptionKey(String encryptionKey) { this.encryptionKey = encryptionKey; }


}
