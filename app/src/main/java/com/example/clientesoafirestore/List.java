package com.example.clientesoafirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Registry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class List extends AppCompatActivity {

    private ListView toList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String cadRegistry = "";
    private ArrayList<String> arrayRegistry = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        toList = (ListView) findViewById(R.id.lv_listRegistry);


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
                                cadRegistry = registry.getRegistryNumber() + " - " + registry.getRegistryDate() + " - " + registry.getStudentID() +
                                        " - " + registry.getStudentCode() + " - " + registry.getProgram() + " - " + registry.getCreditsNumber() +
                                        " - "+ registry.getPpa()+ " - " + registry.getPrice();

                                arrayRegistry.add(cadRegistry);

                            }
                        }
                        else
                        {
                            System.out.println("else");
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                            {

                                Registry registry = document.toObject(Registry.class);
                                cadRegistry = registry.getRegistryNumber() + " - " + registry.getRegistryDate() + " - " + registry.getStudentID() +
                                        " - " + registry.getStudentCode() + " - " + registry.getProgram() + " - " + registry.getCreditsNumber() +
                                        " - "+ registry.getPpa()+ " - " + registry.getPrice();

                                arrayRegistry.add(cadRegistry);
                            }
                        }
                    }
                });


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayRegistry);
        toList.setAdapter(adaptador);
        toList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "Pos : " + i + "Valor : " + adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
                //irAEmpleado(adapterView.getItemAtPosition(i).toString());
            }
        });
    }

    public void list(View view)
    {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayRegistry);
        toList.setAdapter(adaptador);
        toList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        Toast.makeText(this, "refreshing...", Toast.LENGTH_SHORT).show();
    }
}