package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class landf extends AppCompatActivity implements dataAdapter.OnNoteListner {
    private Button lost,found,att,event;
    private static final String TAG ="Something" ;
    //private FirebaseAuth auth;
    DocumentReference documentReference;
    private FirebaseFirestore db;

    String emailt;
    dataAdapter mdataAdapter;
    RecyclerView rvlist;
    List<Record> userlist;
    List<String> keylist;
    TextView name_pop1,contact_pop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landf);

        found =(Button)findViewById(R.id.button);
        lost=(Button)findViewById(R.id.button2);
       att=(Button)findViewById(R.id.attendance_but);
       event=(Button)findViewById(R.id.event_but);



        userlist=new ArrayList<>();
        keylist=new ArrayList<>();

        db=FirebaseFirestore.getInstance();
        documentReference= db.collection("found").document();
        // auth= FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("data",0);
        emailt = sharedPreferences.getString("email"," ");
        mdataAdapter= new dataAdapter(userlist, keylist, this, emailt);
        rvlist=findViewById(R.id.rvuserist);

        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(mdataAdapter);







        db.collection("found").orderBy("datet").addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                {
                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {


                       /* String time;
                        time=dc.getDocument().getString("timet");*/
                        String key=dc.getDocument().getId();
                        keylist.add(key);
                        Record record=dc.getDocument().toObject(Record.class);
                        userlist.add(record);

                        mdataAdapter.notifyDataSetChanged();

                    }
                }
            }
        });

        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(landf.this,lost.class);
                startActivity(i);
            }
        });
        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(landf.this, found.class);
                SharedPreferences sharedPreferences1 = getSharedPreferences("data",0);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("email",emailt);
                editor.commit();
                startActivity(intent);


                overridePendingTransition(0, 0);


            }


        });
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(landf.this, Attendance.class);
                startActivity(intent);

            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(landf.this, EventActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onNoteClick(int position) {
       userlist.get(position);

          }
}