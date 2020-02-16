package com.project.thecouplekiller;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBService {


    public static void initDatabase(FirebaseDatabase lInDatabase) {
        lInDatabase = FirebaseDatabase.getInstance();

    }

    public static DatabaseReference getDbRef() {
        return FirebaseDatabase.getInstance().getReference();
    }


    public static void addPlayer(DatabaseReference aInDbRef, Player aInPlayer){

        aInDbRef.child("players").child(aInPlayer.getName()).setValue(aInPlayer);
    }

    public static void removePlayer(DatabaseReference aInDbRef, Player aInPlayer){
        aInDbRef.child("players").child(aInPlayer.getName()).removeValue();
    }




}
