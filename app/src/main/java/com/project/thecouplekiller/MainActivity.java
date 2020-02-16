package com.project.thecouplekiller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.log4j.Logger;

public class MainActivity extends AppCompatActivity {

    Logger logger = Logger.getLogger(MainActivity.class);

    EditText editTextName;
    Button buttonLogin;
    String playerName;
    SharedPreferences preferences;

    FirebaseDatabase database;


    DatabaseReference databaseRef;


    // GameService gameService;

    DatabaseReference playerRef;


    Player player = new Player("");
    Room room = new Room();


    public void initComponents() {
        editTextName = findViewById(R.id.gEditTextName);
        buttonLogin = findViewById(R.id.gButtonLogin);
        buttonLogin.setEnabled(false);

        DBService.initDatabase(database);

        databaseRef = FirebaseDatabase.getInstance().getReference();

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initilaze components
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editTextName.getText().toString().isEmpty()) {
                buttonLogin.setEnabled(true);
                } else {
                    buttonLogin.setEnabled(false);
                }
            }
        });


        //initComponents();

        //enable Button if text present
        //if (!TextUtils.isEmpty(editTextName.getText().toString())) {


        //Check if player exists and get reference

/*        if (!playerName.equals("")) {
            playerRef = database.getReference("players/" + playerName);
            //database.
            addPlayerEventListener();
            playerRef.setValue("");
        }*/


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logging the player in

                player.setName(editTextName.getText().toString());
                DBService.removePlayer(databaseRef, player);
                DBService.addPlayer(databaseRef, player);

                startActivity(new Intent(getApplicationContext(), Main2Activity.class));

                logger.info("Added successfully Player :" + player.getName());
                finish();

//                playerName = editTextName.getText().toString();
//                editTextName.setText("");

/*                if (!playerName.equals("")) {
                    buttonLogin.setText("LOGGING IN");
                    buttonLogin.setEnabled(false);
                    playerRef = database.getReference("players/" + playerName);
                    addPlayerEventListener();
                    playerRef.setValue("");
                    databaseRef.child("players").child(play)

                }*/

                //addPlayerAndLoginEventListener();


            }

        });

    }


    public void addPlayerAndLoginEventListener() {

        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AlgoUtils.savePlayerIntoPreference(preferences, player);
                Toast.makeText(MainActivity.this, "Welcome " + player.getName(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));

                logger.info("Added successfully Player :" + player.getName());
                finish();

                //Success - continue to the next screen after saving the player name
/*                if (!playerName.equals("")) {
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("playerName", playerName);
                    editor.apply();

                }*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

/*                buttonLogin.setText("LOGIN");
                buttonLogin.setEnabled(true);*/

                Toast.makeText(MainActivity.this, "Failed to read value onCanceledError !", Toast.LENGTH_LONG).show();
                logger.error("Database Error : " + getClass().getName());

            }
        });
    }


    ValueEventListener playerListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            Player player = dataSnapshot.getValue(Player.class);
            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            //            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            //            // ...

            logger.error("Error during playerListner");
        }
    };

}
