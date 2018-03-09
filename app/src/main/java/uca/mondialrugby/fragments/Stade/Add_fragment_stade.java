package uca.mondialrugby.fragments.Stade;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uca.mondialrugby.classes.Stade;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.StadeDAO;

/**
 * Created by watson on 28/02/2018.
 */

public class Add_fragment_stade extends Fragment {

    View myView;
    boolean validate = true;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.stade_layout_add, container, false);

        getActivity().setTitle("Nouveau Client");

        Button button_add_stade = (Button) myView.findViewById(R.id.button_add_stade);

        button_add_stade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value_nom = (EditText) myView.findViewById(R.id.value_nom_stade);
                EditText value_place = (EditText) myView.findViewById(R.id.value_nb_place);
                EditText value_num = (EditText) myView.findViewById(R.id.value_numero_rue);
                EditText value_rue = (EditText) myView.findViewById(R.id.value_nom_rue);
                EditText value_ville = (EditText) myView.findViewById(R.id.value_ville);
                EditText value_cp = (EditText) myView.findViewById(R.id.value_cp);




                String nom = value_nom.getText().toString();
                Integer place = Integer.parseInt(value_place.getText().toString());
                String num_rue = value_num.getText().toString();
                String nom_rue = value_rue.getText().toString();
                String ville = value_ville.getText().toString();
                String cp = value_cp.getText().toString();



                if ( TextUtils.isEmpty(nom)) {
                    Toast.makeText(getContext(), "Nom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(num_rue)) {
                    Toast.makeText(getContext(), "Prénom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(nom_rue)) {
                    Toast.makeText(getContext(), "Numero de téléphone manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(ville)) {
                    Toast.makeText(getContext(), "Numero de rue manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(nom_rue)) {
                    Toast.makeText(getContext(), "Nom de rue manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(ville)) {
                    Toast.makeText(getContext(), "Ville manquante", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(String.valueOf(place))) {
                    Toast.makeText(getContext(), "Code postal manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else validate = true;

                if (validate)
                {
                    System.out.println("Insert");
                    ((MainActivity) getActivity()).closekeyboard(getContext(), myView);
                    StadeDAO stadeDAO = new StadeDAO(myView.getContext());
                    Stade stade = new Stade
                            (
                                    0,
                                    nom,
                                    num_rue,
                                    nom_rue,
                                    ville,
                                    cp,
                                     place

                            );

                    stadeDAO.insertStade(stade);


                    ((MainActivity) getActivity()).changeFragment(new Fragment_Home_Stade());
                }
            }
        });

        return myView;

    }
}
