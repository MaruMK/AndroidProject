package net.caesarlegion.drugimpact.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Connor on 2017-12-15.
 */

public class PostExperience {
    private Integer drug1;
    private Integer drug2;
    private String title;
    private Integer userRating;
    private Integer othersRating;
    private String timeStamp;
    private String content;

    public PostExperience(Integer drug1,Integer drug2,String title,Integer userRating,Integer othersRating,String content) {
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.title = title;
        this.userRating = userRating;
        this.othersRating = othersRating;
        this.timeStamp = new Date().toString();
        this.content = content;
    }
}
