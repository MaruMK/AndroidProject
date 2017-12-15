package net.caesarlegion.drugimpact.ListAdapters.HistoryAdapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Fragments.RemindersFragment;
import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.Model.History;
import net.caesarlegion.drugimpact.Model.onDrugClickedListener;
import net.caesarlegion.drugimpact.R;

import net.caesarlegion.drugimpact.Model.DatabaseException;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * Created by Main on 2017-11-26.
 */

public class HistoryAdapter extends ArrayAdapter<History> {

    public onDrugClickedListener adapterListener;


    //This will be used to format the amount of susbtance consumed displayed
    //in the history listview
    NumberFormat historyAmountFormat = new DecimalFormat("##.##");

    public HistoryAdapter(Context context, onDrugClickedListener listener) {
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

        final TextView substanceTxt = root.findViewById(R.id.substance_text);
        final TextView amountTxt = root.findViewById(R.id.quantity_text);
        Button removeButton = root.findViewById(R.id.deleteSubstanceBtn);

        History h = getItem(position);

        substanceTxt.setText(DrugSafetyData.GetDrugById(h.getDrugId()).getName());
        amountTxt.setText(historyAmountFormat.format(h.getAmount()) + " " + DrugSafetyData.GetDrugById(h.getDrugId()).getUnit());

        //When the removebutton is clicked, remove that particular element
        View.OnClickListener removePressedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemindersFragment.elementToDelete = getItem(position);
                try{
                    RemindersFragment.currentTimer.cancel();
                    adapterListener.onDrugClicked();
                }
                catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        };
        removeButton.setOnClickListener(removePressedListener);

        return root;
    }
}
