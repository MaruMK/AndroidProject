package net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter;

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

import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;
import net.caesarlegion.drugimpact.R;

/**
 * Created by 1555220 on 2017-11-27.
 */

public class ExperienceActivityAdapter extends ArrayAdapter<ExperienceActivity> {
    public ExperienceActivityAdapter(@NonNull Context context) {
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
            root = inflater.inflate(R.layout.partial_experience_activity, parent, false);
        }
        TextView textViewName = root.findViewById(R.id.experience_name);
        TextView textViewDrug = root.findViewById(R.id.experience_drug);
        TextView textViewDesc = root.findViewById(R.id.experience_descriction);
        TextView textViewTime = root.findViewById(R.id.experience_drug);

        ExperienceActivity item = getItem(position);

        textViewName.setText(item.Username.toString());
        textViewDrug.setText(item.Drug.toString());
        textViewDesc.setText( item.Description.toString());
        textViewTime.setText(item.TimeWritten.toString());

        return root;
    }
}
