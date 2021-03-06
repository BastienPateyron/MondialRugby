package uca.mondialrugby;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

//import uca.mondialrugby.fragments.Classement.Classement_fragment;
import uca.mondialrugby.fragments.Classement.Classement_fragment;
import uca.mondialrugby.fragments.Equipe.Equipe_Fragment_Add;
import uca.mondialrugby.fragments.Equipe.Equipe_Fragment_Home;

import uca.mondialrugby.fragments.Match.Match_fragment;
import uca.mondialrugby.fragments.Match.Match_fragment_ajout;
import uca.mondialrugby.fragments.Personne.Personne_Fragment_home;
import uca.mondialrugby.fragments.Stade.Stade_fragment_home;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	
	private static Context sContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		sContext = getApplicationContext();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		
		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		
	}
	
	@Override
	public void onBackPressed() {
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();
		
		if (id == R.id.nav_home_layout) {
			//changeFragment(new Home_fragment());
		}
		if (id == R.id.nav_matchs) {
			changeFragment(new Match_fragment());
		}
		if (id == R.id.nav_equipes) {
			changeFragment(new Equipe_Fragment_Home());
		}
		if (id == R.id.nav_personnes) {
			changeFragment(new Personne_Fragment_home());
		}
		if (id == R.id.nav_stade) {
			changeFragment(new Stade_fragment_home());
		}
		if (id == R.id.nav_classement) {
			changeFragment(new Classement_fragment());
		}
		
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
	
	public void changeFragment(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
		getSupportFragmentManager().executePendingTransactions();
		
	}
	
	public static void closekeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		
	}
	
	// permet d'accéder au context de n'importe ou dans l'application
	public static Context getsContext(){return sContext;}


}
