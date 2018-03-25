package uca.mondialrugby.fragments.Equipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.fragments.Personne.Personne_Adapter;

/**
 * Created by watson on 18/03/2018.
 */

public class Equipe_Frament_Update extends Fragment {
    private View myView;
    private String idEquipe;
    private ArrayList<Equipe> listValidate;


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
         final String pays = value_pays.getText().toString();
            final String surnom = value_surnom.getText().toString();
        Button button_update_stade = (Button) myView.findViewById(R.id.button_update_personne);

        button_update_stade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipe equipe_modify = new Equipe();


                EquipeDAO equipeDAO = new EquipeDAO(myView.getContext());
                listValidate = equipeDAO.getAllEquipe();
                boolean validate = true;
                if ( TextUtils.isEmpty(pays)) {
                    Toast.makeText(getContext(), "Pays manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(surnom)) {
                    Toast.makeText(getContext(), "surnom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(surnom)) {
                    Toast.makeText(getContext(), "surnom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                }
                if (validate) {
                    equipe_modify.setCopyPays(equipe.getPays());
                    equipe_modify.setPays(value_pays.getText().toString());
                    System.out.println(equipe_modify.getPays());
                    equipe_modify.setSurnom(value_surnom.getText().toString());

                    equipeDAO.updateEquipe(equipe_modify);
                    MainActivity.closekeyboard(getContext(), myView);
                    ((MainActivity) getActivity()).changeFragment(new Equipe_Fragment_Home());
                }
            }
        });

        Button button_annule_equipe = (Button) myView.findViewById(R.id.button_noupdate_personne);

        button_annule_equipe.setOnClickListener(new View.OnClickListener()

            {
                @Override public void onClick (View v){
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Equipe_Fragment_Home());

            }

        });

        initListJoueur();

        return myView;

    }
    private void initListJoueur () {
        PersonneDAO personneDAO = new PersonneDAO(getContext());


        ArrayList<Personne> listPersonne;
        listPersonne = personneDAO.getAllPersonneOfEquipe(getContext(),idEquipe);
        ListView listView = (ListView) myView.findViewById(R.id.list_joueur);
        Personne_Adapter adapter = new Personne_Adapter(getActivity(), listPersonne);
        listView.setAdapter(adapter);
    }




}
