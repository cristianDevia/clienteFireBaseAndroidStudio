package com.example.clientesoafirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Registry;
import com.example.clientesoafirestore.services.Services;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Dictionary;
import java.util.Objects;

public class Search extends AppCompatActivity {

    private Services services;

    private EditText txtRegistryNumber;
    private EditText txtRegistryDate;
    private EditText txtStudentID;
    private EditText txtStudentCode;
    private EditText txtProgram;
    private EditText txtCredits;
    private EditText txtPPA;
    private EditText txtRegistryPrice;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        txtRegistryNumber = (EditText) findViewById(R.id.txtRegistryNumber2);
        txtRegistryDate = (EditText) findViewById(R.id.txtRegistryDate2);
        txtStudentID = (EditText) findViewById(R.id.txtStudentID2);
        txtStudentCode = (EditText) findViewById(R.id.txtStudentCode2);
        txtProgram = (EditText) findViewById(R.id.txtProgram2);
        txtCredits = (EditText) findViewById(R.id.txtCreditsNumber2);
        txtPPA = (EditText) findViewById(R.id.txtPPA2);
        txtRegistryPrice = (EditText) findViewById(R.id.txtPrice2);

        services = new Services();
    }

    public void search(View view)
    {
       final int searchNumber = Integer.parseInt(txtRegistryNumber.getText().toString());

        try
        {
            db.collection("Registry").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Registry registry = document.toObject(Registry.class);
                            if( searchNumber == registry.getRegistryNumber())
                            {

                                txtRegistryDate.setText(registry.getRegistryDate());
                                txtStudentID.setText(String.valueOf(registry.getStudentID()));
                                txtStudentCode.setText(registry.getStudentCode());
                                txtProgram.setText(registry.getProgram());
                                txtCredits.setText(String.valueOf(registry.getCreditsNumber()));
                                txtPPA.setText(String.valueOf(registry.getPpa()));
                                txtRegistryPrice.setText(String.valueOf(registry.getPrice()));
                            }
                        }
                    } else {
                        //Log.w(TAG, "Error getting documents.", task.getException());
                    }
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();
        }
    }
}