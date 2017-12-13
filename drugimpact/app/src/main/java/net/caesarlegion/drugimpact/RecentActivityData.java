package net.caesarlegion.drugimpact;

import android.content.Context;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Control.GETObject;
import net.caesarlegion.drugimpact.Control.OnDownloadedListener;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class RecentActivityData {

    private static final int DATA_AMOUNT = 7;

    public static List<RecentActivity> getServerData(OnDownloadedListener<String> onDownloadedListener) {
        GETObject task = new GETObject();
        task.setListener(onDownloadedListener);
        task.execute( MainActivity.ADDRESS + "user/" + MainActivity.CURRENT_USER_ID + "/experiences");
        return null;
    }

    public static ArrayList<RecentActivity> parseServerDataToList(String s) {
        List<RecentActivityData> toReturn = null;
        return null;
    }
}
