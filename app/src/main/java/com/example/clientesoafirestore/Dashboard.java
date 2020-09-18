package com.example.clientesoafirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void btnAdd(View view)
    {
        Intent add = new Intent(this, Add.class);
        startActivity(add);
    }
    public void btnSearch(View view)
    {
        Intent search = new Intent(this, Search.class);
        startActivity(search);
    }
    public void btnDelete(View view)
    {
        Intent delete = new Intent(this, Delete.class);
        startActivity(delete);
    }

    public void btnUpdate(View view)
    {
        Intent update = new Intent(this, Update.class);
        startActivity(update);
    }
}