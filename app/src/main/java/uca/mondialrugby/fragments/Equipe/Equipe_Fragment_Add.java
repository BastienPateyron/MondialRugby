package uca.mondialrugby.fragments.Equipe;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.classes.Equipe;


/**
 * Created by watson on 18/03/2018.
 */

public class Equipe_Fragment_Add extends android.support.v4.app.Fragment {
    private View myView;
    private boolean validate = true;
    private static ArrayList<Equipe> listValidate = new ArrayList<Equipe>();
    private String pays;
    private String surnom;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.equipe_layout_add, container, false);

        getActivity().setTitle("Nouvelle Equipe");

        Button button_add_equipe = (Button) myView.findViewById(R.id.button_add_equipe);

        button_add_equipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value_pays = (EditText) myView.findViewById(R.id.value_pays);
                EditText value_surnom = (EditText) myView.findViewById(R.id.value_surnom);

                 pays = value_pays.getText().toString();
                 surnom = value_surnom.getText().toString();


                 //Verification des champs return false si id déja existant ou champs vide
                EquipeDAO equipeDAO = new EquipeDAO(myView.getContext());
                listValidate = equipeDAO.getAllEquipe();
                if ( TextUtils.isEmpty(pays)) {
                    Toast.makeText(getContext(), "Pays manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(surnom)) {
                    Toast.makeText(getContext(), "surnom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                }


                if (validate)
                {
                    System.out.println("Insert");
                    ((MainActivity) getActivity()).closekeyboard(getContext(), myView);

                    Equipe equipe = new Equipe
                            (
                                    pays.toUpperCase(),
                                    surnom

                            );

                    equipeDAO.insertEquipe(equipe);


                    ((MainActivity) getActivity()).changeFragment(new Equipe_Fragment_Home());
                }
            }
        });

        return myView;

    }




}
