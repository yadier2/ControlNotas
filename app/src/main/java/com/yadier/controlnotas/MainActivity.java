package com.yadier.controlnotas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;



public class MainActivity extends AppCompatActivity  {
    private EditText etNestudiantes;

    public Button btnIngresar;

    private int contador= 0;
    public int numEstudiantes;
    RadioGroup radioId30;
    double porcentajes[] = new double[4];
    private TextView tvPorcentajes [] = new TextView[4];
    HashSet<Integer> idButtons = new HashSet<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNestudiantes=(EditText) findViewById(R.id.etNestudiantes);
        btnIngresar=(Button) findViewById(R.id. btnIngresar);
        radioId30 = (RadioGroup) findViewById(R.id.radioId30);
    }
    public void porcentaje(View view){
        boolean checked = ((RadioButton) view).isChecked();

        int id = view.getId();
        if(!idButtons.contains(id)){
            idButtons.add(id);
            if (id == R.id.Id20) {
                if (checked) {
                    tvPorcentajes[contador].setText("20%");
                    porcentajes[contador] = 0.20;
                    radioId30.setEnabled(false);
                }
            } else if (id == R.id.Id30) {
                if (checked) {
                    tvPorcentajes[contador].setText("30%");
                    porcentajes[contador] = 0.30;
                }
            } else if (id == R.id.Id15) {
                if (checked) {
                    tvPorcentajes[contador].setText("15%");
                    porcentajes[contador] =0.15;
                }
            } else if (id == R.id.Id35) {
                if (checked) {
                    tvPorcentajes[contador].setText("35%");
                    porcentajes[contador] = 0.35;
                }
            }
            if (contador < 3){
                contador++;
            }
        }
    }

    public  void CantidadEstudiantes(View view){
        try {
            numEstudiantes= Integer.parseInt(etNestudiantes.getText().toString());
            etNestudiantes.setEnabled(false);
        }catch (Exception e){
            Toast.makeText(this, "Ingresar la cantidad de estudiantes", Toast.LENGTH_LONG).show();
        }
    }


}