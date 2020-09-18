package com.example.clientesoafirestore.services;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.clientesoafirestore.model.Registry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Services {

    private  FirebaseFirestore  db = FirebaseFirestore.getInstance();;


    public Boolean addRegistry(Registry registry)
    {
        db.collection("Registry").document(String.valueOf(registry.getRegistryNumber())).set(registry)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Error: " + e);
                    }
                });

        return true;
    }

    public Registry search(int searchRegistryNumber)
    {
        final int pRegistryNumber = searchRegistryNumber;
        final Registry searchRegistry = null;

        try
        {
            db.collection("Registry").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {
                                System.out.println("task.isSuccessful()");
                                for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                                {
                                    System.out.println("entre");
                                    Registry registry = document.toObject(Registry.class);
                                    if(pRegistryNumber == registry.getRegistryNumber() )
                                    {
                                        //searchRegistry.setRegistryNumber(registry.getRegistryNumber());
                                        searchRegistry.setRegistryDate(registry.getRegistryDate());
                                        searchRegistry.setStudentID(registry.getStudentID());
                                        searchRegistry.setStudentCode(registry.getStudentCode());
                                        searchRegistry.setProgram(registry.getProgram());
                                        searchRegistry.setCreditsNumber(registry.getCreditsNumber());
                                        searchRegistry.setPpa(registry.getPpa());
                                        searchRegistry.setPrice(registry.getPrice());
                                    }
                                }
                            }
                        }
                    });

        }
        catch (Exception e)
        {

        }

        return searchRegistry;
    }
}
