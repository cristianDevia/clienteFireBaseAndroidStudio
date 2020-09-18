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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class Update extends AppCompatActivity {

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
        setContentView(R.layout.activity_update);

        txtRegistryNumber = (EditText) findViewById(R.id.txtRegistryNumber4);
        txtRegistryDate = (EditText) findViewById(R.id.txtRegistryDate4);
        txtStudentID = (EditText) findViewById(R.id.txtStudentID4);
        txtStudentCode = (EditText) findViewById(R.id.txtStudentCode4);
        txtProgram = (EditText) findViewById(R.id.txtProgram4);
        txtCredits = (EditText) findViewById(R.id.txtCreditsNumber4);
        txtPPA = (EditText) findViewById(R.id.txtPPA4);
        txtRegistryPrice = (EditText) findViewById(R.id.txtPrice4);

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

    public void update(View view)
    {
        boolean response = false;
        final int registryNumber, studentID, creditsNumber;
        final double ppa, price;
        final String studentCode, program;
        final String registryDate;
        Registry registry = null;

        final int searchNumber = Integer.parseInt(txtRegistryNumber.getText().toString());

        {

            try {
                registryNumber = Integer.parseInt(txtRegistryNumber.getText().toString());
                studentID = Integer.parseInt(txtStudentID.getText().toString());
                creditsNumber = Integer.parseInt(txtCredits.getText().toString());
                ppa = Double.parseDouble(txtPPA.getText().toString());
                price = Double.parseDouble(txtRegistryPrice.getText().toString());

                //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                registryDate = txtRegistryDate.getText().toString();

                studentCode = txtStudentCode.getText().toString();
                program = txtProgram.getText().toString();

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

                                            DocumentReference documentReference = db.collection("Registry").document(String.valueOf(searchNumber));

                                            documentReference.update("registryNumber",registryNumber, "registryDate",registryDate,
                                                                    "studentID",studentID, "studentCode", studentCode, "program", program, "creditsNumber", creditsNumber,
                                                                    "ppa", ppa, "price", price);
                                        }
                                    }
                                }
                            }
                        });
                Toast.makeText(this, "Updating...", Toast.LENGTH_SHORT).show();
                clean();
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();
            }
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