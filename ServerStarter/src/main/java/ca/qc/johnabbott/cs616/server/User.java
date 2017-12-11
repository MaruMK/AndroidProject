package ca.qc.johnabbott.cs616.server;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<Comment>();

    @OneToMany(mappedBy = "user")
    private Set<Experience> experiences = new HashSet<Experience>();

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

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
