package uca.mondialrugby.fragments.Equipe;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.fragments.Stade.Stade_fragment_home;

/**
 * Created by watson on 18/03/2018.
 */

public class Equipe_Fragment_Add extends android.support.v4.app.Fragment {
    View myView;
    boolean validate = true;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.equipe_layout_add, container, false);

        getActivity().setTitle("Nouvelle Equipe");

        Button button_add_equipe = (Button) myView.findViewById(R.id.button_add_equipe);

        button_add_equipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value_pays = (EditText) myView.findViewById(R.id.value_pays);
                EditText value_surnom = (EditText) myView.findViewById(R.id.value_surnom);




                String pays = value_pays.getText().toString();

                String surnom = value_surnom.getText().toString();



                if ( TextUtils.isEmpty(pays)) {
                    Toast.makeText(getContext(), "Nom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(surnom)) {
                    Toast.makeText(getContext(), "Prénom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                }

                if (validate)
                {
                    System.out.println("Insert");
                    ((MainActivity) getActivity()).closekeyboard(getContext(), myView);
                    EquipeDAO equipeDAO = new EquipeDAO(myView.getContext());
                    Equipe equipe = new Equipe
                            (
                                    pays,
                                    surnom

                            );

                    equipeDAO.insertEquipe(equipe);


                    ((MainActivity) getActivity()).changeFragment(new Stade_fragment_home());
                }
            }
        });

        return myView;

    }
}
