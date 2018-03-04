package uca.mondialrugby.fragments.Stade;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import uca.mondialrugby.Classe.Stade;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.fragments.Stade.Update_stade_fragment;

/**
 * Created by watson on 03/03/2018.
 */

public abstract class FragmentManager  extends Fragment {

    public final void initList (View myView, ArrayList list) {
        ListView listView = (ListView) myView.findViewById(R.id.list_generique);
        final ArrayAdapter <Stade> adapter = new ArrayAdapter<>(myView.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ID", String.valueOf(adapter.getItem(position).getId()));

                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(adapter.getItem(position).getId()));
                Update_stade_fragment usf = new Update_stade_fragment();
                usf.setArguments(bundle);
                ((MainActivity) getContext()).changeFragment(usf);
            }
        });
    }




}
