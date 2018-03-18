package uca.mondialrugby.fragments.Equipe;

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

import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;


/**
 * Created by watson on 18/03/2018.
 */

public class Equipe_Fragment_Home extends Fragment{
    View myView;
    private ArrayList<Equipe> listEquipe = new ArrayList<Equipe>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.general_layout_list, container, false);

        getActivity().setTitle("Equipe");

        TextView add_part_textview = (TextView) myView.findViewById(R.id.add_part_textview);
        add_part_textview.setText("Ajouter une equipe");

        LinearLayout add_part = (LinearLayout) myView.findViewById(R.id.add_part);
        add_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipe_Fragment_Add efa = new Equipe_Fragment_Add();
                ((MainActivity)getActivity()).changeFragment(efa);
            }
        });



        initListEquipe();

        return myView;
    }

    public void initListEquipe () {
        EquipeDAO equipeDAO = new EquipeDAO(getContext());
        listEquipe = equipeDAO.getAllEquipe();

        ListView listView = (ListView) myView.findViewById(R.id.generalListe);
        final ArrayAdapter<Equipe> adapter = new ArrayAdapter<Equipe>(myView.getContext(), android.R.layout.simple_list_item_1, listEquipe);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ID", (adapter.getItem(position).getPays()));

                Bundle bundle = new Bundle();
                bundle.putString("id", (adapter.getItem(position).getPays()));
                Equipe_Frament_Update efu = new Equipe_Frament_Update();
                efu.setArguments(bundle);

                ((MainActivity) getContext()).changeFragment(efu);
            }

        });
    }
}
