package uca.mondialrugby.fragments.Stade;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import uca.mondialrugby.Classe.Stade;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.StadeDAO;

/**
 * Created by watson on 28/02/2018.
 */
// TODO : problème de modification ( cause : surement  layout)

public class Update_stade_fragment extends Fragment {
    View myView;
    private int idStade;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.stade_layout_update, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            idStade = Integer.valueOf(bundle.get("id").toString());
        }
        System.out.println("Id Client: " + idStade);

        final EditText value_nom = (EditText) myView.findViewById(R.id.value_nom_stade);
        final EditText value_place = (EditText) myView.findViewById(R.id.value_nb_place);
        final EditText value_num = (EditText) myView.findViewById(R.id.value_numero_rue);
        final EditText value_rue = (EditText) myView.findViewById(R.id.value_nom_rue);
        final EditText value_ville = (EditText) myView.findViewById(R.id.value_ville);
        final EditText value_cp = (EditText) myView.findViewById(R.id.value_cp);


        final StadeDAO stadeDAO = new StadeDAO(getContext());
        final Stade stade = stadeDAO.retrieveStade(idStade);

        value_nom.setText(stade.getNom());
        value_place.setText("" + stade.getNombre_place());
        value_num.setText("" + stade.getNum_rue());
        value_rue.setText(stade.getNom_rue());
        value_cp.setText("" + stade.getCp());
        value_ville.setText(stade.getVille());

        /*
        Button button_remove_client = (Button) myView.findViewById(R.id.remove_client);

        button_remove_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stadeDAO.deleteClient(getContext(), stade.getId());
                ((MainActivity) getActivity()).changeFragment(new Client_fragment());
            }
        });*/
        Button button_modify_client = (Button) myView.findViewById(R.id.button_update_stade);

        button_modify_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stade stade_modify = new Stade();

                stade_modify.setNom(value_nom.getText().toString());
                stade_modify.setNombre_place(Integer.parseInt(value_place.getText().toString()));
                stade_modify.setNum_rue(value_num.getText().toString());
                stade_modify.setNom_rue(value_rue.getText().toString());
                stade_modify.setCp(value_cp.getText().toString());
                stade_modify.setVille(value_ville.getText().toString());

                stadeDAO.updateStade(stade_modify);
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Fragment_Home_Stade());
            }
        });

        Button button_annule_stade = (Button) myView.findViewById(R.id.button_noupdate_stade);

        button_annule_stade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Fragment_Home_Stade());
            }
        });

        return myView;

    }


    private void showEditDialog() {

    }



    public void updateList() {
        ArrayList<Stade> listStade = new ArrayList<Stade>();
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
