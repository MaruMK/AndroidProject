package net.caesarlegion.drugimpact.Control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.R;

/**
 * Created by Maru on 2017-11-27.
 */

public class DrugListAdapter extends ArrayAdapter<Drug> {
    public DrugListAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflate or reuse previously inflated UI
        View root;
        if (convertView != null)
            root = convertView;
        else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(R.layout.list_drug_template,parent,false);
        }

        TextView drugName = root.findViewById(R.id.drugName);
        //sample image
        ImageView drugImage = root.findViewById(R.id.drugImage);

        Drug drug = getItem(position);

        drugName.setText(drug.getName());


        return root;
    }
}
