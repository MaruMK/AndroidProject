package ca.qc.johnabbott.cs616.server;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
/**
 * Created by Gabriel Charlebois on 2017-12-10.
 */
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    private String timeStamp;
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name="experience_id")
    @JsonBackReference
    private Experience experience;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


}
