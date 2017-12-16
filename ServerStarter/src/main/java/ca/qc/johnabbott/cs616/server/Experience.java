package ca.qc.johnabbott.cs616.server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Connor King & Gabriel Charlebois on 2017-12-8.
 */
@Entity
public class Experience {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long experienceId;
/*
    @ManyToMany
    @JoinColumn(
            name = "entry",
            joinColumns = @JoinColumn(name = "experience_id", referencedColumnName = "experience_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )*/

    private Integer drug1;
    private Integer drug2;
    private String title;
    private Integer userRating;
    private Integer othersRating;
    private String timeStamp;
    private String content;

    @OneToMany(mappedBy = "experience")
    private Set<Comment> comments = new HashSet<Comment>();

    private long userId;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(long experienceId) {
        this.experienceId = experienceId;
    }

    public Integer getDrug1() {
        return drug1;
    }

    public void setDrug1(Integer drug1) {
        this.drug1 = drug1;
    }

    public Integer getDrug2() {
        return drug2;
    }

    public void setDrug2(Integer drug2) {
        this.drug2 = drug2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public Integer getOthersRating() {
        return othersRating;
    }

    public void setOthersRating(Integer othersRating) {
        this.othersRating = othersRating;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
