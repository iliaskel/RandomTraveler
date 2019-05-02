package com.example.randomtraveler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.randomtraveler.Repositories.FlightsRepository;
import com.example.randomtraveler.UIFragments.FlightsFragment;
import com.example.randomtraveler.UIFragments.HotelsFragment;
import com.example.randomtraveler.UIFragments.SearchFragment;
import com.example.randomtraveler.UIFragments.SignInFragment;
import com.example.randomtraveler.UIFragments.SignUpFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements SignInFragment.SignInFragmentListener, SignUpFragment.SignUpFragmentListener, SearchFragment.SearchFragmentListener {

    private final String TAG = getClass().getSimpleName();

    private final String SIGN_IN_FRAGMENT_TAG = "Sign in fragment";
    private final String SIGN_UP_FRAGMENT_TAG = "Sign up fragment";
    private final String SEARCH_FRAGMENT_TAG = "Search fragment";
    private final String HOTELS_FRAGMENT_TAG = "Hotels fragment";
    private final String FLIGHTS_FRAGMENT_TAG = "Flights fragment";

    SearchFragment searchFragment = new SearchFragment();
    SignInFragment signInFragment = new SignInFragment();
    SignUpFragment signUpFragment = new SignUpFragment();
    HotelsFragment hotelsFragment = new HotelsFragment();
    FlightsFragment flightsFragment = new FlightsFragment();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //First fragment appears in onStart();

    }

    /**
     * Sign up Fragment interface implemented methods
     **/
    @Override
    public void OnSignUpClicked(final String email,String password, int age) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmailAndPassword :: User created :: " + email);
                            showToast("User successfully created...");
                            showSignInFragment();
                        }
                        else{
                            showToast("Something went wrong. Please try again...");
                            Log.d(TAG, "createUserWithEmailAndPassword :: Failed to create user " + task.getException());
                        }
                    }
                });

    }

    /**
     * Search Fragment interface implemented methods
     */
    @Override
    public void searchButtonClicked(String startingPoint, String days, String budget) {
        //showHotelsFragment();
        showFlightsFragment(startingPoint,days,budget);
    }

    /**
     * Sign in Fragment interface implemented methods
     **/
    @Override
    public void onSignInClicked(String email, String password) {
        if(email.isEmpty() || password.isEmpty()){
            Log.d(TAG, "onSignInClicked :: email or password null");
            showToast("Please fill all required fields...");
        }else{
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                showToast("User signed in!");
                                showSearchFragment();
                                Log.d(TAG, "signInWithEmail :: Signed in");
                            }
                            else{
                                Log.d(TAG, "signInWithEmail :: Couldn't sign in");
                                showToast("Wrong credentials");
                            }
                        }
                    });
        }

    }

    @Override
    public void onCreateAccountClicked() {
        showSignUpFragment();
    }

    @Override
    public void onForgotPasswordClicked() {

    }


    /**
     * Fragment transaction methods
     */
    private void showSignInFragment() {
        showToast("mUser == null. Sign in fragment show up");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_frame_layout,signInFragment,SIGN_IN_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void showSignUpFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_frame_layout,signUpFragment,SIGN_UP_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void showSearchFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_frame_layout,searchFragment,SEARCH_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void showHotelsFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(searchFragment);
        fragmentTransaction.replace(R.id.activity_main_top_half_layout,hotelsFragment,HOTELS_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void showFlightsFragment(String startingPoint, String days, String budget) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        flightsFragment.setSearchParams(startingPoint,days,budget);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(searchFragment);
        fragmentTransaction.replace(R.id.activity_main_frame_layout,flightsFragment,FLIGHTS_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    /**
     * Lifecycle methods
     */
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
            showSearchFragment();
        else
            showSignInFragment();
    }

    /**
     * Other methods
     **/
    private void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

}
