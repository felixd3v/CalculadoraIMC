package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtEdad, txtAltura, txtPeso;
    private ImageButton btnMasculino, btnFemenino;
    private Button btnCalcularIMC;
    private TextView textViewResultado, textViewSituacion;
    private Spinner altura, peso;
    private ImageView imgSituacion;

    private String selectedGender;
    private int edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        btnMasculino = findViewById(R.id.btnMasculino);
        btnFemenino = findViewById(R.id.btnFemenino);
        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        txtEdad = findViewById(R.id.txtEdad);
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC);
        textViewResultado = findViewById(R.id.textViewResultado);
        textViewSituacion = findViewById(R.id.textViewSituacion);
        altura = findViewById(R.id.spinnerAltura);
        peso = findViewById(R.id.spinnerPeso);
        imgSituacion = findViewById(R.id.imgpeso);

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

        // Configurar el ArrayAdapter para los Spinners de altura y peso
        ArrayAdapter<CharSequence> alturaAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.altura_options,
                android.R.layout.simple_spinner_item
        );
        alturaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        altura.setAdapter(alturaAdapter);

        ArrayAdapter<CharSequence> pesoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.peso_options,
                android.R.layout.simple_spinner_item
        );
        pesoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        peso.setAdapter(pesoAdapter);

        // Agregar listener al Spinner de altura
        altura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAltura = parent.getItemAtPosition(position).toString();
                // Realizar la conversión de la altura según la opción seleccionada (cm o pulgadas)
                // y actualizar el EditText de altura
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se realiza ninguna acción
            }
        });

        // Agregar listener al Spinner de peso
        peso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPeso = parent.getItemAtPosition(position).toString();
                // Realizar la conversión del peso según la opción seleccionada (kg o libras)
                // y actualizar el EditText de peso
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se realiza ninguna acción
            }
        });
    }

    private void calcularIMC() {
        double peso = Double.parseDouble(txtPeso.getText().toString());
        double altura = Double.parseDouble(txtAltura.getText().toString());
        edad = Integer.parseInt(txtEdad.getText().toString());

        // Cálculo del IMC
        double imc = peso / (altura * altura);

        // Determinar la situación según el IMC, el género y la edad
        String situacion = "";
        int imageResource = 0; // Variable para almacenar el ID de la imagen
        if (selectedGender.equals("femenino")) {
            if (edad < 16) {
                if (imc < 19) {
                    situacion = "Delgadez severa";
                    imageResource = R.drawable.obesamujer1;
                } else if (imc >= 19 && imc < 24) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.imagen_mujer_inicio;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.femenino6;
                }
            } else if (edad >= 16 && edad <= 18) {
                if (imc < 19) {
                    situacion = "Delgadez severa";
                    imageResource = R.drawable.obeso3;
                } else if (imc >= 19 && imc < 24) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.masculino3;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.masculino4;
                }
            } else if (edad >= 19 && edad <= 24) {
                if (imc < 19) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.delgado1;
                } else if (imc >= 19 && imc < 24) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.obeso3;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso1;
                }
            } else if (edad >= 25 && edad <= 29) {
                if (imc < 20) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.obesamujer1;
                } else if (imc >= 20 && imc < 25) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.delgado1;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.femenino6;
                }
            } else if (edad >= 30 && edad <= 60) {
                if (imc < 21) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.imagen_mujer_inicio;
                } else if (imc >= 21 && imc < 26) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.imagen_hombre_inicio;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso1;
                }
            } else if (edad > 60) {
                if (imc < 22) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.obesamujer1;
                } else if (imc >= 22 && imc < 27) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.obeso3;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso1;
                }
            }
        } else if (selectedGender.equals("masculino")) {
            if (edad < 16) {
                if (imc < 20) {
                    situacion = "Delgadez severa";
                    imageResource = R.drawable.obesamujer1;
                } else if (imc >= 20 && imc < 25) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.obeso3;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso1;
                }
            } else if (edad >= 16 && edad <= 18) {
                if (imc < 20) {
                    situacion = "Delgadez severa";
                    imageResource = R.drawable.obesamujer1;
                } else if (imc >= 20 && imc < 25) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.obeso3;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso1;
                }
            } else if (edad >= 19 && edad <= 24) {
                if (imc < 20) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.obesamujer1;
                } else if (imc >= 20 && imc < 25) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.obeso3;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso1;
                }
            } else if (edad >= 25 && edad <= 29) {
                if (imc < 21) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.imagen_hombre_inicio;
                } else if (imc >= 21 && imc < 26) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.imagen_mujer_inicio;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.imagen_hombre_inicio;
                }
            } else if (edad >= 30 && edad <= 60) {
                if (imc < 22) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.imagen_hombre_inicio;
                } else if (imc >= 22 && imc < 27) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.imagen_mujer_inicio;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.imagen_hombre_inicio;
                }
            } else if (edad > 60) {
                if (imc < 23) {
                    situacion = "Delgadez";
                    imageResource = R.drawable.imagen_hombre_inicio;
                } else if (imc >= 23 && imc < 28) {
                    situacion = "Peso normal";
                    imageResource = R.drawable.obeso1;
                } else {
                    situacion = "Sobrepeso";
                    imageResource = R.drawable.obeso3;
                }
            }
        }

        // Mostrar el resultado y la situación
        textViewResultado.setText(String.format("%.2f", imc));
        textViewSituacion.setText(situacion);

        // Mostrar la imagen correspondiente a la situación
        Drawable drawable = getResources().getDrawable(imageResource);
        imgSituacion.setImageDrawable(drawable);
    }
}
