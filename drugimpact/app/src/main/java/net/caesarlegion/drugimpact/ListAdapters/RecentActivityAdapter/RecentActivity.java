package net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter;

import java.util.Date;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class  RecentActivity {
    public enum activityType {
        NEW_COMMENT,
        SELF_POST
    }

    public activityType type;
    public String specifics;
    public String abstrct;
    public Date stamp;

    public RecentActivity(activityType type, String specifics, String abstrct, Date stamp) {
        this.type = type;
        this.specifics = specifics;
        this.abstrct = abstrct;
        this.stamp = stamp;
    }

    public String constructTitle(){
        switch(this.type){
            case NEW_COMMENT:
                return "<u>New</u> comment on '" + specifics + "'";
            case SELF_POST:
                return "<b>Posted Experience</b> '" + specifics + "'";
        }
        return null;
    }

}
