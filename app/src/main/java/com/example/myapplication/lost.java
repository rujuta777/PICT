package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class lost extends AppCompatActivity {
    private EditText object,desc,color,no;
    Button back;
    private FirebaseAuth auth;
    private int notificationId=1;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object=(EditText)findViewById(R.id.object);
                desc=(EditText)findViewById(R.id.description);
                color=(EditText)findViewById(R.id.color);
                no=(EditText)findViewById(R.id.phone);

                String Objectt=object.getText().toString().trim();
                String desct= desc.getText().toString().trim();
                String colort =color.getText().toString().trim();
                String not =no.getText().toString().trim();
                DocumentReference Ref=db.collection("lost").document();
                if(Objectt.isEmpty())
                { object.requestFocus();
                    Toast.makeText(lost.this,"object is empty",Toast.LENGTH_SHORT).show();
                }
                else if(colort.isEmpty())
                {color.requestFocus();
                    Toast.makeText(lost.this,"color of object is empty",Toast.LENGTH_SHORT).show();

                }
                else if(desct.isEmpty())
                {desc.requestFocus();
                    Toast.makeText(lost.this,"Description of object is empty",Toast.LENGTH_SHORT).show();
                }

                else if(not.isEmpty())
                {no.requestFocus();
                    Toast.makeText(lost.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();

                }
                else
                {//Log.d("123","xyz");

                    record2 record=new record2( Objectt,colort,not,desct);
                   // int abc = Log.d("abc", "123jkl");
                    Ref.set(record);
                   // Log.d("123","xyz");

                    Ref.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Log.d("123","zyx");
                            Toast.makeText(lost.this, " Information is store Successfully", Toast.LENGTH_SHORT).show();
                            Intent in =new Intent(lost.this,landf.class);
                            startActivity(in);


                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(lost.this, "Failed to store  Information", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });

    }
}