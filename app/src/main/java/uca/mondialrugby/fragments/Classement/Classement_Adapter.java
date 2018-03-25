package uca.mondialrugby.fragments.Classement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.fragments.Equipe.Equipe_Frament_Update;

/**
 * Created by watson on 24/03/2018.
 */
/* Classe qui permet de personnalisé les item de la liste */
public class Classement_Adapter extends ArrayAdapter<Equipe> {
    private ArrayList<Equipe> equipes = new ArrayList<>();
    private static class EquipeHolder
    {
        TextView secteur_nom;
        TextView secteur_number;

    }

    public Classement_Adapter(Context context, ArrayList<Equipe> equipes)
    {
       super(context,0, equipes);
        this.equipes = equipes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        final Equipe equipe = getItem(position);
        EquipeHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new EquipeHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_classement,parent,false);
            viewHolder.secteur_number = (TextView) convertView.findViewById(R.id.classement_item_number);
            viewHolder.secteur_nom = (TextView) convertView.findViewById(R.id.classement_item_nom);

            
            convertView.setTag(viewHolder);
        }
        else {

            viewHolder = (EquipeHolder) convertView.getTag();
        }
        // setText avec notre object
        viewHolder.secteur_number.setText(String.valueOf(position + 1));
        viewHolder.secteur_nom.setText(equipe.getPays()+ "   Victoire : " + equipe.getSurnom());




        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ROW: ", "ROW PRESSED");
                System.out.println(equipe.getPays());
                Bundle bundle = new Bundle();
                bundle.putString("id", (getItem(position).getPays()));
                System.out.printf(equipe.getPays());
                Equipe_Frament_Update efu = new Equipe_Frament_Update();
                efu.setArguments(bundle);
                ((MainActivity)getContext()).changeFragment(efu);
            }
        });
        return convertView;
    }
}
