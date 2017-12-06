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
        data.add(new ExperienceActivity("Timmy","This stuff was pretty good",new Date()));
        return data;
    }
}
