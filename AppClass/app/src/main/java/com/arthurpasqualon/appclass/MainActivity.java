package com.arthurpasqualon.appclass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etValue;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // tela ainda não está visível para usuario
        // Classe R referencia todos os elementos do codigo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValue = findViewById(R.id.etValue);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    private void calculate() {
        String value = etValue.getText().toString();
        if(!value.isEmpty()){
            double num = Double.valueOf(value);
            double result = num * 2;
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Resultado do Cálculo:");
            alert.setMessage(value + " x 2 = " + result);
            alert.setPositiveButton("Ok", null);
            alert.show();
        }
    }


}