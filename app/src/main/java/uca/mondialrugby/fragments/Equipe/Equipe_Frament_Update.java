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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Personne;

/**
 * Created by watson on 18/03/2018.
 */

public class Equipe_Frament_Update extends Fragment {
    View myView;
    private String idEquipe;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.equipe_layout_update, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            idEquipe = (bundle.get("id").toString());
        }
        System.out.println("Id equipe : " + idEquipe);

        final EditText value_pays = (EditText) myView.findViewById(R.id.value_pays);
        final EditText value_surnom = (EditText) myView.findViewById(R.id.value_surnom);


        final EquipeDAO equipeDAO = new EquipeDAO(getContext());
        final Equipe equipe = equipeDAO.retrieveEquipe(idEquipe);

        value_pays.setText(equipe.getPays());
        value_surnom.setText(equipe.getSurnom());


        /*
        Button button_remove_client = (Button) myView.findViewById(R.id.remove_client);

        button_remove_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipeDAO.deleteClient(getContext(), equipe.getId());
                ((MainActivity) getActivity()).changeFragment(new Client_fragment());
            }
        });*/
        Button button_update_stade = (Button) myView.findViewById(R.id.button_update_personne);

        button_update_stade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipe equipe_modify = new Equipe();
                System.out.print(idEquipe);
                System.out.println("fin");
                equipe_modify.setCopyPays(equipe.getPays());
                equipe_modify.setPays(value_pays.getText().toString());
                System.out.println(equipe_modify.getPays());
                equipe_modify.setSurnom(value_surnom.getText().toString());

                equipeDAO.updateEquipe(equipe_modify);
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Equipe_Fragment_Home());
            }
        });

        Button button_annule_equipe = (Button) myView.findViewById(R.id.button_noupdate_personne);

        button_annule_equipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Equipe_Fragment_Home());
            }
        });
        // Get all contrats
        PersonneDAO personneDAO = new PersonneDAO(getContext());


        ArrayList<Personne> listPersonne = personneDAO.getAllPersonneOfEquipe(getContext(),idEquipe);

        final ArrayAdapter<Personne> adapter = new ArrayAdapter<Personne>(myView.getContext(),android.R.layout.simple_list_item_1, listPersonne);

        ListView listView = myView.findViewById(R.id.list_joueur);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ID Equipe :", (idEquipe));
                Log.i("ID Personne :", String.valueOf(adapter.getItem(position).getId()));

                Bundle bundle = new Bundle();
                bundle.putString("id_equipe",(idEquipe));
                bundle.putString("id_personne",String.valueOf(adapter.getItem(position).getId()));







            }
        });
        return myView;

    }
}
