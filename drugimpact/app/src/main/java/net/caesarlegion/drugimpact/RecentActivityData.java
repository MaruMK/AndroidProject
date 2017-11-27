package net.caesarlegion.drugimpact;

import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class RecentActivityData {
    public static List<RecentActivity> getData() {
        List<RecentActivity> data = new ArrayList<>();
        data.add(new RecentActivity(RecentActivity.activityType.REMINDER,"Beer with friends", "For 2 shots of 40 proof vodka.",new Date()));
        data.add(new RecentActivity(RecentActivity.activityType.NEW_COMMENT,"Trip at the beach","I hate sand. It's so coarse and gets everywhere.",new Date()));
        data.add(new RecentActivity(RecentActivity.activityType.SELF_POST,"Trip at the beach","So let me start of by saying I am not the best at telling stories, but here we go...",new Date()));
        data.add(new RecentActivity(RecentActivity.activityType.REMINDER,"Acidic beverage","For 100 mg of Sunny-D Capri.",new Date()));
        return data;
    }
}
