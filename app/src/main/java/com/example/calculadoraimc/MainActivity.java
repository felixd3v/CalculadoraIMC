package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtEdad,txtAltura,txtPeso;
    private Button  btnMasculino,btnFemenino,btnCalcularIMC;

    private TextView textViewResultado;

    private String selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        btnMasculino = findViewById(R.id.btnMasculino);
        btnFemenino = findViewById(R.id.btnFemenino);
        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        btnCalcularIMC= findViewById(R.id.btnCalcularIMC);
        textViewResultado = findViewById(R.id.textViewResultado);

        // Agregar listeners a los botones de género
        btnMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "masculino";
                btnMasculino.setSelected(true);
                btnFemenino.setSelected(false);
            }
        });

        btnFemenino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "femenino";
                btnFemenino.setSelected(true);
                btnMasculino.setSelected(false);
            }
        });

        // Agregar un listener al botón de calcular
        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC() {

       if(selectedGender=="femenino"){
           double peso = Double.parseDouble(txtPeso.getText().toString());
           double altura = Double.parseDouble(txtAltura.getText().toString());


           double imc = peso / (altura * altura);


           String resultado = String.format("Tu IMC es", imc, selectedGender);
           Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
           textViewResultado.setText(resultado);


       }
       if (selectedGender=="masculino"){



           double peso = Double.parseDouble(txtPeso.getText().toString());
           double altura = Double.parseDouble(txtAltura.getText().toString());


           double imc = peso / (altura * altura);


           String resultado = String.format("Tu IMC es", imc, selectedGender);
           Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
           textViewResultado.setText(resultado);
       }



    }
}
