package com.example.clientesoafirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Program;
import com.example.clientesoafirestore.model.Registry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ListPrograms extends AppCompatActivity {

    private ListView toList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String cadProgram = "";
    private ArrayList<String> arrayProgram = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_programs);

        toList = (ListView) findViewById(R.id.lv_listProgram);

        db.collection("Program").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            System.out.println("task.isSuccessful()");
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                            {
                                System.out.println("entre");
                                Program program = document.toObject(Program.class);
                                cadProgram = program.getName() + " - " + program.getProgramCode() + " - " + program.getVerification() +
                                            " - " + program.getDuration() + " - " + program.getModality();

                                arrayProgram.add(cadProgram);

                            }
                        }
                        else
                        {
                            System.out.println("else");
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                            {

                                Program program = document.toObject(Program.class);
                                cadProgram = program.getName() + " - " + program.getProgramCode() + " - " + program.getVerification() +
                                        " - " + program.getDuration() + " - " + program.getModality();

                                arrayProgram.add(cadProgram);
                            }
                        }
                    }
                });


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayProgram);
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
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayProgram);
        toList.setAdapter(adaptador);
        toList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        Toast.makeText(this, "refreshing...", Toast.LENGTH_SHORT).show();
    }
}