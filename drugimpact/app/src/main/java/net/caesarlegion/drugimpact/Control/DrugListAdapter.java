package net.caesarlegion.drugimpact.Control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Maru on 2017-11-27.
 */

public class DrugListAdapter extends ArrayAdapter<Drug> implements Filterable{
    /*=========== Private fields =======================================================================*/
    public List<Drug> fullDrugList;
    public List<Drug> filteredDrugList;
    private LayoutInflater inflater;
    public DrugFilter filter = new DrugFilter();
    /*==================================================================================================*/

    /*=========== Constructors =========================================================================*/
    public DrugListAdapter(@NonNull Context context) {
        super(context, -1);
    }

    public DrugListAdapter(Context context, List<Drug> data) {
        super(context, -1, data);
        this.filteredDrugList = data;
        this.fullDrugList = data;
        inflater = LayoutInflater.from(context);
    }
    /*==================================================================================================*/
    /*=========== Private Classes ======================================================================*/
    private class DrugFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String constraintStr = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Drug> list = fullDrugList;
            int count = list.size();

            ArrayList<Drug> newList = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                String filterableString = list.get(i).getName().toLowerCase();
                if (filterableString.contains(constraintStr)) {
                    newList.add(list.get(i));
                }
            }
            results.values = newList;
            results.count = newList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(results.count > 0) {
                filteredDrugList = (List<Drug>) results.values;
                for (Iterator<Drug> it = ((List<Drug>) results.values).iterator(); it.hasNext(); ) {
                    Drug d = it.next();
                    Log.d("DEBUG",d.getName());
                }
                Log.d("DEBUG_COUNT", String.valueOf(results.count));

                notifyDataSetChanged();
            }
            notifyDataSetInvalidated();
        }
    }
    /*==================================================================================================*/

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflate or reuse previously inflated UI
        View root;
        if (convertView != null)
            root = convertView;
        else {
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(R.layout.list_drug_template,parent,false);
        }

        TextView drugName = root.findViewById(R.id.drugName);
        //sample image
        ImageView drugImage = root.findViewById(R.id.drugImage);

        Drug drug = getItem(position);

        drugName.setText(drug.getName());

        return root;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }
}