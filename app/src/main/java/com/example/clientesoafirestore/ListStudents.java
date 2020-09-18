package com.example.clientesoafirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Program;
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
    private  ArrayList<Student> goStudent = new ArrayList<Student>();



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
                                goStudent.add(student);

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
                                goStudent.add(student);
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
                for(int j = 0; j< goStudent.size(); j++)
                {
                    Student temp = goStudent.get(j);
                    if(j == i)
                    {
                        goToAddRegistry(String.valueOf(temp.getId()));
                        break;
                    }
                }
            }
        });
        Toast.makeText(this, "refreshing...", Toast.LENGTH_SHORT).show();
    }

    public void goToAddRegistry(String student){
        Intent intent = new Intent(this, Add.class);
        intent.putExtra("MSJSTUDENT",student);
        startActivity(intent);
    }
}