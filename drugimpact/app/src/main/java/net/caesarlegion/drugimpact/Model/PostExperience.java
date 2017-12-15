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

    private long experienceId;
    private Integer drug1;
    private Integer drug2;
    private String title;
    private Integer userRating;
    private Integer othersRating;
    private String timeStamp;
    private String content;
    private Links _links;

    public PostExperience(Integer drug1,Integer drug2,String title,Integer userRating,Integer othersRating,String content) {
        this.experienceId = 3;
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.title = title;
        this.userRating = userRating;
        this.othersRating = othersRating;
        this.timeStamp = new Date().toString();
        this.content = content;
        this._links = new Links();

        _links.self = new Self();
        _links.self.href = "ha";

        _links.experience = new Experience();
        _links.experience.href = "ha";

        _links.user = new User();
        _links.user.href = "ha";

        _links.comments = new Comments();
        _links.comments.href = "ha";
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
    public String toJSON()
    {
        return "{\"drug1\":\"" + this.drug1 + "\", \"drug2\":\"" + this.drug2 + "\", \"title\":\"" + this.title + "\", \"userRating\":\"" + this.userRating + "\", \"othersRating\":\"" + this.othersRating + "\", \"timeStamp\":\"" + this.timeStamp + "\", \"content\":\"" + this.content +"\"}";
    }
}
