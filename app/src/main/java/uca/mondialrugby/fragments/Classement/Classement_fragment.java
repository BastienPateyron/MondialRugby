package uca.mondialrugby.fragments.Classement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.JouerDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Jouer;
import uca.mondialrugby.fragments.Equipe.Equipe_Adapter;
import uca.mondialrugby.fragments.Equipe.Equipe_Fragment_Add;

/**
 * Created by basti on 3/14/2018.
 */

public class Classement_fragment extends Fragment {

	View myView;
	private ArrayList<Equipe> listClassement = new ArrayList<>();


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
		Equipe_Adapter adapter = new Equipe_Adapter(getActivity(), listClassement);
		listView.setAdapter(adapter);
	}
}
