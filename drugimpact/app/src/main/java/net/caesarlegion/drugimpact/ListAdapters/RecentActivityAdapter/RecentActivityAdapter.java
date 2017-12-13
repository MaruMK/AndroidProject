package net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.caesarlegion.drugimpact.R;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class RecentActivityAdapter extends ArrayAdapter<RecentActivity> {
    public RecentActivityAdapter(@NonNull Context context) {
        super(context, -1);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root;
        if (convertView != null)
            root = convertView;
        else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(R.layout.partial_welcome_recent_activity, parent, false);
        }
        TextView textViewTitle = root.findViewById(R.id.recent_activity_title);
        TextView textViewStamp = root.findViewById(R.id.recent_activity_stamp);
        TextView textViewAbstract = root.findViewById(R.id.recent_activity_abstract);
        ImageView image = root.findViewById(R.id.imageView_recent);

        RecentActivity item = getItem(position);

        textViewTitle.setText(Html.fromHtml(item.constructTitle()), TextView.BufferType.SPANNABLE);
        textViewStamp.setText( item.stamp.toString());
        textViewAbstract.setText(item.abstrct);

        switch(item.type){
            case NEW_COMMENT:
                root.setBackgroundResource(R.color.welcome_newcomment);
                image.setImageDrawable(null);
                break;
            case SELF_POST:
                root.setBackgroundResource(R.color.welcome_selfpost);
                image.setImageDrawable(null);

        }

        return root;
    }
}
