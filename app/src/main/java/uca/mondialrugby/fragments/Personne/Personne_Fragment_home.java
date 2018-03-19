package uca.mondialrugby.fragments.Personne;

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

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.classes.Personne;


/**
 * Created by watson on 12/03/2018.
 */

public class Personne_Fragment_home extends Fragment {


    View myView;
    private ArrayList<Personne> listPersonne = new ArrayList<Personne>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.general_layout_list, container, false);

        getActivity().setTitle("Personne");

        TextView add_part_textview = (TextView) myView.findViewById(R.id.add_part_textview);
        add_part_textview.setText("Ajouter une personne");

        LinearLayout add_part = (LinearLayout) myView.findViewById(R.id.add_part);
        add_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personne_Fragment_Ajout pfs = new Personne_Fragment_Ajout();
               ((MainActivity)getActivity()).changeFragment(pfs);
            }
        });



        initListPersonne();

        return myView;
    }

    public void initListPersonne () {
        PersonneDAO personneDAO = new PersonneDAO(getContext());
        listPersonne = personneDAO.getAllPersonne(getContext());
        ListView listView = (ListView) myView.findViewById(R.id.generalListe);
        Personne_Adapter adapter = new Personne_Adapter(getActivity(), listPersonne);
        listView.setAdapter(adapter);
    }

      /*  ListView listView = (ListView) myView.findViewById(R.id.generalListe);
        final ArrayAdapter<Personne> adapter = new ArrayAdapter<Personne>(myView.getContext(), android.R.layout.simple_list_item_1, listPersonne);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ID", String.valueOf(adapter.getItem(position).getId()));

                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(adapter.getItem(position).getId()));
                Personne_Fragment_Update pfu = new Personne_Fragment_Update();
                pfu.setArguments(bundle);
                ((MainActivity) getContext()).changeFragment(pfu);
            }
        });*/



}//fin
