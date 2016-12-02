package com.example.career.career.m_Firebase;

import com.example.career.career.m_Country.Country;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by cmmuk_000 on 12/2/2016.
 */

public class FirebaseHelper {

    private DatabaseReference mydb;

    public FirebaseHelper(DatabaseReference mydb) {
        this.mydb = mydb;
    }

    //reading
    public ArrayList<String> retrieve()
    {
        final ArrayList<String> countries = new ArrayList<>();

        mydb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot, countries);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot, countries);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return countries;
    }

    private void fetchData(DataSnapshot snapshot, ArrayList<String> countries)
    {
        countries.clear();
        for(DataSnapshot ds:snapshot.getChildren())
        {
            String name = ds.getValue(Country.class).getName();
            countries.add(name);
        }
    }
}
