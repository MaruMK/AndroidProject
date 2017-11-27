package net.caesarlegion.drugimpact.Control;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Model.onDrugClickedListener;
import net.caesarlegion.drugimpact.R;


/**
 * Created by Main on 2017-11-26.
 */

public class CustomDrugReminderAdapter extends ArrayAdapter<String> {

    public onDrugClickedListener adapterListener;

    public CustomDrugReminderAdapter(Context context, onDrugClickedListener listener) {
        super(context, -1);
        adapterListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inflate ui
        View root;
        if(convertView != null){
            root = convertView;
        }
        else{
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(R.layout.drug_reminder_listitem, parent, false);
        }

        TextView substanceTxt = root.findViewById(R.id.substanceTxt);

        substanceTxt.setText(getItem(position));

        return root;
    }
}
