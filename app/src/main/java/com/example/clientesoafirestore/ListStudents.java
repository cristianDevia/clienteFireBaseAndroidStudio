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
import com.example.clientesoafirestore.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ListStudents extends AppCompatActivity {

    private ListView toList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String cadStudents = "";
    private ArrayList<String> arrayStudents = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        toList = (ListView) findViewById(R.id.lv_listStudents);

        db.collection("Student").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            System.out.println("task.isSuccessful()");
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                            {
                                System.out.println("entre");
                                Student student = document.toObject(Student.class);
                                cadStudents = student.getName() + " - " + student.getId() + " - " + student.getCode() +
                                              " - " + student.getEmail() + " - " + student.getCelphone() + " - " +
                                                student.getDateOfBirth() + " - " + student.getGender();

                                arrayStudents.add(cadStudents);

                            }
                        }
                        else
                        {
                            System.out.println("else");
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                            {

                                Student student = document.toObject(Student.class);
                                cadStudents = student.getName() + " - " + student.getId() + " - " + student.getCode() +
                                        " - " + student.getEmail() + " - " + student.getCelphone() + " - " +
                                        student.getDateOfBirth() + " - " + student.getGender();

                                arrayStudents.add(cadStudents);
                            }
                        }
                    }
                });


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayStudents);
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
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayStudents);
        toList.setAdapter(adaptador);
        toList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        Toast.makeText(this, "refreshing...", Toast.LENGTH_SHORT).show();
    }
}