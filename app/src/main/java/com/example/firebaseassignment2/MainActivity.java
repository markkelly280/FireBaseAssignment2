package com.example.firebaseassignment2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth myAuth;
    Button signOut;
    Button exit;
    Button login;

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentuser = myAuth.getCurrentUser();
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    OnSignInResult(result);
                }
            }
    );

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();
        //createSignInIntent();

        signOut = (Button) findViewById(R.id.signout_btn);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        Button solarPower = (Button) findViewById(R.id.solar_btn);

        solarPower.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, solar_panel_info.class);
                startActivity(i);
            }
        });

        //Code to open the wind activity
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button windfarms = (Button) findViewById(R.id.wind_btn);
        windfarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w = new Intent(MainActivity.this, wind_turbine_info.class);
                startActivity(w);
            }
        });

        //Code to open the charge points(?) Activity
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button chargePoint = (Button) findViewById(R.id.charge_btn);
        chargePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cP = new Intent(MainActivity.this, chargep.class);
                startActivity(cP);
            }
        });

        Button electricCars = (Button) findViewById(R.id.car_btn);

        electricCars.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, electric_cars_info.class);
                startActivity(i);
            }
        });

        //Function to close the app when exit button is tapped
        exit = (Button) findViewById(R.id.exit_btn);
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSignInIntent();
            }
        });
    }

    public void createSignInIntent(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.AnonymousBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void OnSignInResult(FirebaseAuthUIAuthenticationResult result){
        IdpResponse response = result.getIdpResponse();
        if(result.getResultCode()== RESULT_OK){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
            //Disables login button and enables signout button when login was successful
            login.setEnabled(false);
            signOut.setEnabled(true);
        }else {
            Toast.makeText(this, "login Failed",Toast.LENGTH_SHORT).show();
        }
    }

    //Firebase SignOut
    private void signOut(){
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        //Enables the login button and disables the signout button when signout is successful
                        login.setEnabled(true);
                        signOut.setEnabled(false);
                        Toast.makeText(MainActivity.this, "Signed out Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

        //Old methods to view solar, and car charging? Can remove if not used anymore.
        /*@SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button solar = (Button) findViewById(R.id.solar_btn);
        solar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent s = new Intent(MainActivity.this,);
               startActivity(s);
            }
        });*/

        /*@SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button elecCar = (Button) findViewById(R.id.car_btn);
        elecCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(MainActivity.this,);
                startActivity(e);
            }
        });*/

        //UNCOMMENT THIS WHEN READY, Firebase database setup I think
        /*FirebaseDatabase database = FirebaseDatabase.getInstance("");
        DatabaseReference myRef = database.getReference("");
        myRef.setValue("");*/