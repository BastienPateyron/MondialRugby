package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import uca.mondialrugby.classes.Jouer;
import uca.mondialrugby.classes.Match;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Stade;

import static android.content.ContentValues.TAG;

/**
 * Created by watson on 04/03/2018.
 */

public class MatchDAO extends SQLiteDBHelper {
	private static final String TABLE_MATCHS = "MATCHS";
	private static final String COL_ID = "ID_MATCH";
	private static final String COL_STADE = "ID_STADE";
	private static final String COL_PERSONNE = "ID_PERSONNE";
	private static final String COL_DATE = "DATE_MATCH ";
	
	// Table jouer
	private static final String TABLE_JOUER = "JOUER";
	private static final String COL_SCORE = "SCORE ";
	
	
	private Personne personne;
	private Stade stade;
	
	/******** Match
	 * @param context***********/
	public MatchDAO(Context context) {
		super(context);
	}
	
	/*** Méthodes alternatives ***/
	
	/* getAllMatchFini */
	public ArrayList<Match> getAllMatchFini(Context context){
		SQLiteDatabase db = this.getReadableDatabase();
		PersonneDAO personneDAO = new PersonneDAO (context);
		StadeDAO stadeDAO = new StadeDAO(context);
		
		ArrayList<Match> listMatch = new ArrayList<>();
		
		/*db.query(TABLE_MATCHS, // Nom de table
				new String[]{COL_ID, COL_STADE, COL_PERSONNE, COL_DATE},
				COL_ID + "=?",
				new String[]{String.valueOf(idMatch)}, // j'ai supprimé ici, pourquoi on avait besoin de String.valueOf(idPays) en plus ???
				null, null, null, null); // Options*/
		
		// On récupère les matchs dont le score est renseigné
		String query = "SELECT DISTINCT ID_MATCH, ID_STADE, ID_PERSONNE, DATE_MATCH" +
						" FROM " + TABLE_MATCHS + " JOIN " + TABLE_JOUER + " USING(" + COL_ID + ")" +
						" WHERE " + COL_SCORE + " NOT LIKE 'NULL';";
		Cursor cursor = db.rawQuery(query, null);
		
		if (cursor.moveToFirst()){
			do {
				stade = stadeDAO.retrieveStade(cursor.getInt(1));
				personne = personneDAO.retrievePersonne(cursor.getInt(2),context);
				Match jouer = new Match (
						cursor.getInt(0),
						stade,
						personne,
						cursor.getString(3)
				);
				
				Log.d(TAG, "getAllMatchFini: new match: " + jouer);
				listMatch.add(jouer);
			} while(cursor.moveToNext());
		} else Log.d(TAG, "getAllMatchFini: Liste vide");
		db.close();
		return listMatch;
	}
	
	/* getAllMatchPrevu */
	public ArrayList<Match> getAllMatchPrevu(Context context){
		SQLiteDatabase db = this.getReadableDatabase();
		PersonneDAO personneDAO = new PersonneDAO (context);
		StadeDAO stadeDAO = new StadeDAO(context);
		
		ArrayList<Match> listMatch = new ArrayList<>();
		
		// On récupère les matchs dont le score est 'NULL'
		String query = "SELECT DISTINCT ID_MATCH, ID_STADE, ID_PERSONNE, DATE_MATCH " +
				" FROM " + TABLE_MATCHS + " JOIN " + TABLE_JOUER + " USING(" + COL_ID + ") " +
				" WHERE " + COL_SCORE + " = 'NULL';";
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()){
			do {
				stade = stadeDAO.retrieveStade(cursor.getInt(1));
				personne = personneDAO.retrievePersonne(cursor.getInt(2),context);
				Match jouer = new Match (
						cursor.getInt(0),
						stade,
						personne,
						cursor.getString(3)
				);
				
				listMatch.add(jouer);
				
				Log.d(TAG, "getAllMatchPrevu: new match: " + jouer);
			} while(cursor.moveToNext());
		} else Log.d(TAG, "getAllMatchPrevu: Liste vide");
		db.close();
		return listMatch;
	}
	
	/* getAllMatch */
	public ArrayList<Match> getAllMatch(Context context){
		SQLiteDatabase db = this.getReadableDatabase();
		PersonneDAO personneDAO = new PersonneDAO (context);
		StadeDAO stadeDAO = new StadeDAO(context);
		
		ArrayList<Match> listMatch = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_MATCHS + ";";
		Cursor cursor = db.rawQuery(query, null);
		
		if (cursor.moveToFirst()){
			do {
				stade = stadeDAO.retrieveStade(cursor.getInt(1));
				personne = personneDAO.retrievePersonne(cursor.getInt(2),context);
				Match jouer = new Match (
						cursor.getInt(0),
						stade,
						personne,
						cursor.getString(3)
				);
				
				listMatch.add(jouer);
			} while(cursor.moveToNext());
		} else Log.d(TAG, "getAllMatch: Liste vide");
		db.close();
		return listMatch;
	}
	
	/* Méthodes CRUD */
	/*createMatch*/
	public boolean createMatch(Match match) {
		ContentValues values = new ContentValues();
		
		values.put(COL_ID, match.getIdMatch());
		values.put(COL_STADE, match.getStade().getId());
		values.put(COL_PERSONNE, match.getPersonne().getId());
		values.put(COL_DATE, match.getDateMatch());
		
		SQLiteDatabase db = this.getWritableDatabase();
		boolean insertSuccessful = db.insert(TABLE_MATCHS, null, values) > 0;
		
		return insertSuccessful;
	}
	
	public Match retrieveMatch(Context context, int idMatch) {
		SQLiteDatabase db = this.getReadableDatabase();
		PersonneDAO personneDAO = new PersonneDAO(context);
		StadeDAO stadeDAO = new StadeDAO(context);

        /* Requete */
		Cursor cursor = db.query(TABLE_MATCHS, // Nom de table
				new String[]{COL_ID, COL_STADE, COL_PERSONNE, COL_DATE},
				COL_ID + "=?",
				new String[]{String.valueOf(idMatch)}, // j'ai supprimé ici, pourquoi on avait besoin de String.valueOf(idPays) en plus ???
				null, null, null, null); // Options
		if (cursor != null)
			cursor.moveToFirst();
		
		personne = personneDAO.retrievePersonne(cursor.getInt(0), context);
		stade = stadeDAO.retrieveStade(cursor.getInt(1));
		Match match = new Match(
				cursor.getInt(0),
				stade,
				personne,
				cursor.getString(3));
		;
		db.close();
		return match;
	}
	
	public void updateMatch(Match match) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_ID, match.getIdMatch());
		values.put(COL_STADE, match.getStade().getId());
		values.put(COL_PERSONNE, match.getPersonne().getId());
		values.put(COL_DATE, match.getDateMatch());
		db.update(TABLE_MATCHS, values, COL_ID + "=" + match.getIdMatch(), null);
		db.close();
	}
	
	// deleteMatch
	public void deleteMatch(Context context, int idMatch) {
		
		//TODO : supprimer avec double clé
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		db.delete(TABLE_MATCHS, COL_ID + "=" + idMatch, null);
		
		db.close();
	}
}
