package uca.mondialrugby.fragments.Classement;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;

import uca.mondialrugby.classes.Equipe;

/**
 * Created by basti on 3/14/2018.
 */

/* Affiche le classement */
public class Classement_fragment extends Fragment {

	private View myView;
	private ArrayList<Equipe> listClassement = new ArrayList<>();
	//Button button =(Button) myView.findViewById(R.id.add_part);
	//button.setVisibility(myView.INVISIBLE);

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		getActivity().setTitle("Classement");
		myView = inflater.inflate(R.layout.general_layout_list, container, false);


		initListClassement();

		return myView;
	}

	public void initListClassement () {
		EquipeDAO equipeDAO = new EquipeDAO(getContext());
		listClassement = equipeDAO.getClassement();

		ListView listView = (ListView) myView.findViewById(R.id.generalListe);
		Classement_Adapter adapter = new Classement_Adapter(getActivity(), listClassement);
		listView.setAdapter(adapter);
	}
}
