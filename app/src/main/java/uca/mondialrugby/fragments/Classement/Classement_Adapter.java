package uca.mondialrugby.fragments.Classement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.classes.Jouer;
import uca.mondialrugby.fragments.Equipe.Equipe_Frament_Update;

import static java.security.AccessController.getContext;

/**
 * Created by watson on 24/03/2018.
 */

public class Classement_Adapter extends ArrayAdapter<Jouer> {
    private ArrayList<Jouer> jouers = new ArrayList<>();
    private static class JouerHolder /* Objet qui contient les éléments affichés à l'écran */
    {
        TextView secteur_nom;
        TextView secteur_number;

    }

    public Classement_Adapter(Context context, ArrayList<Jouer> jouers)
    {
       super(context,0, jouers);
        this.jouers = jouers;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //On recupere l'objet Secteur
        final Jouer jouer = getItem(position);
        JouerHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new JouerHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext()); /* On désérialise les données du context */
            convertView = inflater.inflate(R.layout.item_classement,parent,false); /* On désérialise le layout */
            viewHolder.secteur_number = (TextView) convertView.findViewById(R.id.classement_item_number); /* On valorise le nombre */
            viewHolder.secteur_nom = (TextView) convertView.findViewById(R.id.classement_item_nom); /* On valorise le nom */

            
            convertView.setTag(viewHolder);
        }
        else {

            viewHolder = (JouerHolder) convertView.getTag();
        }
        // setText avec notre object
        viewHolder.secteur_number.setText(String.valueOf(position));
        viewHolder.secteur_nom.setText(jouer.getIdPays().getPays());




        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ROW: ", "ROW PRESSED");
                Bundle bundle = new Bundle();
                //bundle.putString("id_jouer",String.valueOf(.getId()));
                Equipe_Frament_Update efu = new Equipe_Frament_Update();
                efu.setArguments(bundle);
                ((MainActivity)getContext()).changeFragment(efu);
            }
        });
        return convertView;
    }
}
