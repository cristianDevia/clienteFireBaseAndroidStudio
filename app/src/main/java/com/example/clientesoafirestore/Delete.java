package com.example.clientesoafirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Registry;
import com.example.clientesoafirestore.services.Services;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class Delete extends AppCompatActivity {

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
        setContentView(R.layout.activity_delete);

        txtRegistryNumber = (EditText) findViewById(R.id.txtRegistryNumber3);
        txtRegistryDate = (EditText) findViewById(R.id.txtRegistryDate3);
        txtStudentID = (EditText) findViewById(R.id.txtStudentID3);
        txtStudentCode = (EditText) findViewById(R.id.txtStudentCode3);
        txtProgram = (EditText) findViewById(R.id.txtProgram3);
        txtCredits = (EditText) findViewById(R.id.txtCreditsNumber3);
        txtPPA = (EditText) findViewById(R.id.txtPPA3);
        txtRegistryPrice = (EditText) findViewById(R.id.txtPrice3);

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

    public void deleteRegistry(View view)
    {
        try {
            final int searchNumber = Integer.parseInt(txtRegistryNumber.getText().toString());

            db.collection("Registry").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                System.out.println("task.isSuccessful()");
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    System.out.println("entre");
                                    Registry registry = document.toObject(Registry.class);
                                    if (searchNumber == registry.getRegistryNumber()) {

                                        db.collection("Registry").document(String.valueOf(searchNumber)).delete();
                                    }
                                }
                            }
                        }
                    });
            Toast.makeText(this, "Eliminado...", Toast.LENGTH_SHORT).show();
            clean();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();
        }
    }

    public void clean()
    {
        txtRegistryNumber.setText("");
        txtRegistryDate.setText("");
        txtStudentID.setText("");
        txtStudentCode.setText("");
        txtProgram.setText("");
        txtCredits.setText("");
        txtPPA.setText("");
        txtRegistryPrice.setText("");
    }
}