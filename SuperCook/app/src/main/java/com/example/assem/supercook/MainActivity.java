package com.example.assem.supercook;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public static FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final int RC_SIGN_IN = 1;
    String SEARCH_FRAG = "searchFragment";
    String RECIPE_FRAG = "recipeFragment";
    String PROFILE_FRAG = "profileFragment";
    public static ArrayList<Recipe> recipes;
    public static String curUsername;
    public static String userID;
    public static int [] categoryIms = new int [] {R.drawable.dessert, R.drawable.vegetables, R.drawable.meat,
            R.drawable.seafood, R.drawable.chicken, R.drawable.soup};
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivityForResult(AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.supercooklogo)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                } else {
                    curUsername = user.getDisplayName();
                    userID = user.getUid();
                }
            }
        };
        init();
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottombaritem_home:
                                RecipeFragment RF = (RecipeFragment)getSupportFragmentManager().findFragmentByTag(RECIPE_FRAG);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_holder, RF, RECIPE_FRAG)
                                        .commit();
                                return true;
                            case R.id.bottombaritem_search:
                                SearchFragment SF = (SearchFragment)getSupportFragmentManager().findFragmentByTag(SEARCH_FRAG);
                                if (SF == null) {SF = new SearchFragment();}
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_holder, SF, SEARCH_FRAG)
                                        .commit();
                                return true;
                            case R.id.bottombaritem_lists:
                                ProfileFragment PF = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(PROFILE_FRAG);
                                if (PF == null) {PF = new ProfileFragment();}
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_holder, PF, PROFILE_FRAG)
                                        .commit();
                                return true;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                RecipeFragment RF = (RecipeFragment)getSupportFragmentManager().findFragmentByTag(RECIPE_FRAG);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_holder, RF, RECIPE_FRAG)
                        .commit();
                curUsername = mFirebaseAuth.getCurrentUser().getDisplayName();
                userID = mFirebaseAuth.getCurrentUser().getUid();
                Toast.makeText(this, "Welcome, "+ curUsername, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
    }

    public void init () {
        RecipeFragment RF = new RecipeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_holder, RF, RECIPE_FRAG)
                .addToBackStack(RECIPE_FRAG)
                .commit();
    }

    @Override
    protected void onResume () {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause () {
        super.onPause();
        if (mFirebaseAuth != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
