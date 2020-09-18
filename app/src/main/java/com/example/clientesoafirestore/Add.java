package com.example.clientesoafirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientesoafirestore.model.Registry;
import com.example.clientesoafirestore.services.Services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtRegistryNumber = (EditText) findViewById(R.id.txtRegistryNumber);
        txtRegistryDate = (EditText) findViewById(R.id.txtRegistryDate);
        txtStudentID = (EditText) findViewById(R.id.txtStudentID);
        txtStudentCode = (EditText) findViewById(R.id.txtStudentCode);
        txtProgram = (EditText) findViewById(R.id.txtProgram);
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
        String studentCode, program;
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

}