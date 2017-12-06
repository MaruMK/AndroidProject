package net.caesarlegion.drugimpact;

import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1555220 on 2017-12-04.
 */

public class ExperienceData {
    public static List<ExperienceActivity> getData() {
        List<ExperienceActivity> data = new ArrayList<>();
        data.add(new ExperienceActivity("Timmy","Weed","This stuff was pretty good",new Date()));
        data.add(new ExperienceActivity("Bill","Coco","We going back to the future",new Date()));
        data.add(new ExperienceActivity("KABOOM","OXY CLEAN","WASH YOUR DETERGENT NOW",new Date()));
        return data;
    }
}
