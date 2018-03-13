package uca.mondialrugby.fragments.Stade;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import uca.mondialrugby.classes.Stade;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.StadeDAO;


/**
 * Created by watson on 28/02/2018.
 */

public class Fragment_Home_Stade extends Fragment {
    View myView;
    private ArrayList<Stade> listStade = new ArrayList<Stade>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.general_layout_list, container, false);

        getActivity().setTitle("Stade");

        TextView add_part_textview = (TextView) myView.findViewById(R.id.add_part_textview);
        add_part_textview.setText("Ajouter un stade");

        LinearLayout add_part = (LinearLayout) myView.findViewById(R.id.add_part);
        add_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Stade_fragment_ajout afs = new Stade_fragment_ajout();
                ((MainActivity)getActivity()).changeFragment(afs);
            }
        });



    initListStade();

        return myView;
    }

    public void initListStade () {
        StadeDAO stadeDAO = new StadeDAO(getContext());
        listStade = stadeDAO.getAllStade();

        ListView listView = (ListView) myView.findViewById(R.id.list_generique);
        final ArrayAdapter<Stade> adapter = new ArrayAdapter<Stade>(myView.getContext(), android.R.layout.simple_list_item_1, listStade);
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