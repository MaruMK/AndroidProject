package net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter;

import java.util.Date;
/**
 * Created by 1555220 on 2017-11-27.
 */

public class ExperienceActivity {
    public String Username;
    public String Description;
    public Date TimeWritten;

    public ExperienceActivity(String username,String Description,Date TimeWritten) {
        this.Username = username;
        this.Description = Description;
        this.TimeWritten = TimeWritten;
    }


}
