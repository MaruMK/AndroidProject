package net.caesarlegion.drugimpact.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Connor on 2017-12-15.
 */

public class PostExperience {

    private long userId;
    //private long experienceId;
    private Integer drug1;
    private Integer drug2;
    private String title;
    private Integer userRating;
    private Integer othersRating;
    private String timeStamp;
    private String content;
    private Links _links;

    public PostExperience(Integer drug1,Integer drug2,String title,Integer userRating,Integer othersRating,String content) {
        this.userId = 3;
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.title = title;
        this.userRating = userRating;
        this.othersRating = othersRating;
        this.timeStamp = "End me";
        this.content = content;
        this._links = new Links();
    }
    private static class Links
    {
        private Self self;
        private Experience experience;
        private User user;
        private Comments comments;
    }
    private static class Self
    {
        private String href;
    }
    private static class Experience
    {
        private String href;
    }
    private static class User
    {
        private String href;
    }
    private static class Comments
    {
        private String href;
    }
}
