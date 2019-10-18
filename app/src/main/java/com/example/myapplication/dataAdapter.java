package com.example.myapplication;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Text;

import java.util.List;

import javax.annotation.Nullable;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder>
{
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    public List<Record>recordList;
    public List<String>keylists;
    Dialog mydialog;
    String myemailid;
    TextView name, contactnumber;

    private OnNoteListner onNoteListner;
    public dataAdapter(List<Record>trecord, List<String> keylists,OnNoteListner onNoteListner, String emailid){
        this.recordList=trecord;
        this.onNoteListner=onNoteListner;
        this.keylists=keylists;
        myemailid=emailid;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements DialogInterface.OnClickListener {


        View mview;
        TextView object,place,color,desc,time,date,no;
        Button contact,deletetext;

        OnNoteListner onNoteListner;
        public ViewHolder(@NonNull View itemView/*,OnNoteListner onNoteListnerthis*/) {
            super(itemView);
            mview=itemView;

            object=mview.findViewById(R.id.object);
            place=mview.findViewById(R.id.place);
            color=mview.findViewById(R.id.color);
            desc=mview.findViewById(R.id.description);
            no=mview.findViewById(R.id.phone);
            time=mview.findViewById(R.id.time);
            date=mview.findViewById(R.id.date);
            contact=mview.findViewById(R.id.contact_but);
            deletetext=mview.findViewById(R.id.delete_but);

            mydialog=new Dialog(contact.getContext());
            mydialog.setContentView(R.layout.found_dialog);
            name=mydialog.findViewById(R.id.name_pop1);
            contactnumber=mydialog.findViewById(R.id.contact_pop1);
//            this.onNoteListner=onNoteListnerthis;


            //itemView.setOnClickListener((View.OnClickListener) this);

        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            onNoteListner.onNoteClick(getAdapterPosition());

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.object.setText(recordList.get(position).getObject());
        holder.place.setText(recordList.get(position).getPlacet());
        holder.color.setText(recordList.get(position).getColort());
        holder.desc.setText(recordList.get(position).getDesct());
        holder.time.setText(recordList.get(position).getTimet());
        holder.date.setText(recordList.get(position).getDatet());
        /// holder.no.setText(recordList.get(position).getnot());

        if(!myemailid.equals(recordList.get(position).getEmailt())){
            holder.deletetext.setVisibility(View.GONE);
        }

        holder.deletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("found").document(keylists.get(position))
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(holder.mview.getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.mview.getContext(), "Not deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseFirestore db=FirebaseFirestore.getInstance();
                db.collection("Student").document(recordList.get(position).getEmailt()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        String namet=documentSnapshot.getString("namet");
                        String contactt=documentSnapshot.getString("mobilet");
                        name.setText(namet);
                        contactnumber.setText(contactt);
                        mydialog.show();
                    }
                });
            }
        });
    }



    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public interface OnNoteListner{
        void onNoteClick(int position);

    }


}