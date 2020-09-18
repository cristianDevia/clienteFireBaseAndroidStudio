package com.example.clientesoafirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Program;
import com.example.clientesoafirestore.model.Registry;
import com.example.clientesoafirestore.services.Services;

public class Add extends AppCompatActivity {

    private EditText txtRegistryNumber;
    private EditText txtRegistryDate;
    private EditText txtStudentID;
    private EditText txtStudentCode;
    private EditText txtProgram;
    private EditText txtCredits;
    private EditText txtPPA;
    private EditText txtRegistryPrice;

    private Services services;
    private String cadProgram;
    private String cadStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        cadProgram = intent.getStringExtra("MSJPROGRAM");

        Intent intent1 = getIntent();
        cadStudent = intent1.getStringExtra("MSJSTUDENT");


        txtRegistryNumber = (EditText) findViewById(R.id.txtRegistryNumber);
        txtRegistryDate = (EditText) findViewById(R.id.txtRegistryDate);
        txtStudentID = (EditText) findViewById(R.id.txtStudentID);
        if(cadStudent != "")
        {
            txtStudentID.setText(cadStudent );
        }

        txtStudentCode = (EditText) findViewById(R.id.txtStudentCode);
        txtProgram = (EditText) findViewById(R.id.txtProgram);
        txtProgram.setText(cadProgram);
        txtCredits = (EditText) findViewById(R.id.txtCreditsNumber);
        txtPPA = (EditText) findViewById(R.id.txtPPA);
        txtRegistryPrice = (EditText) findViewById(R.id.txtPrice);

        services = new Services();
    }

    public void btnAdd(View view)
    {
        boolean response = false;
        int registryNumber, studentID, creditsNumber;
        double ppa, price;
        String studentCode;
        String program;
        String registryDate;
        Registry registry = null;

        try{

            registryNumber = Integer.parseInt(txtRegistryNumber.getText().toString());
            studentID = Integer.parseInt(txtStudentID.getText().toString());
            creditsNumber = Integer.parseInt(txtCredits.getText().toString());
            ppa = Double.parseDouble(txtPPA.getText().toString());
            price = Double.parseDouble(txtRegistryPrice.getText().toString());

            //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            registryDate = txtRegistryDate.getText().toString();

            studentCode = txtStudentCode.getText().toString();
            program = txtProgram.getText().toString();

            registry = new Registry(registryNumber, registryDate, studentID, studentCode, program, creditsNumber, ppa, price);
            response = services.addRegistry(registry);
            System.out.println("Registro agregado: " + registry);

            if(response == true)
            {
                Toast.makeText(this, "Registro creado", Toast.LENGTH_SHORT).show();
                clean();
            }
            else
            {
                Toast.makeText(this, "Error, No se pudo agregar el registro", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            System.out.println("Error al agregar: " + ex);
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

    public void goToProgram(View view)
    {
        Intent goProgram = new Intent(this, ListPrograms.class);
        startActivity(goProgram);
    }

    public void goToStudent(View view)
    {
        Intent goStudent = new Intent(this, ListStudents.class);
        startActivity(goStudent);
    }
}