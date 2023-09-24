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
    private EditText etNestudiantes,etNota1,etNota2,etNota3,etNota4;

    public Button btnIngresar,btnCalcular;

    private int contador= 0;
    public int numEstudiantes;
    private int nEstudiantesPerdieron=0;
    RadioGroup radioId30;
    double porcentajes[] = new double[4];
    private TextView tvPorcentajes [] = new TextView[4];
    private double nota1,nota2,nota3,nota4,notafinal;
    private TextView tvNotaFinal,tvMensaje, tvMensajeP;
    HashSet<Integer> idButtons = new HashSet<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNestudiantes=(EditText) findViewById(R.id.etNestudiantes);
        btnIngresar=(Button) findViewById(R.id. btnIngresar);
        radioId30 = (RadioGroup) findViewById(R.id.radioId30);
        etNota1=(EditText) findViewById(R.id.etNota1);
        etNota2=(EditText) findViewById(R.id.etNota2);
        etNota3=(EditText) findViewById(R.id.etNota3);
        etNota4=(EditText) findViewById(R.id.etNota4);
        tvNotaFinal=(TextView) findViewById(R.id.tvNotaFinal);

        tvMensaje=(TextView) findViewById(R.id.tvMensaje);
        tvMensajeP=(TextView) findViewById(R.id.tvMensajeP);
        tvPorcentajes[0]=(TextView) findViewById(R.id.tvPorcentaje1);
        tvPorcentajes[1]=(TextView) findViewById(R.id.tvPorcentaje2);
        tvPorcentajes[2]=(TextView) findViewById(R.id.tvPorcentaje3);
        tvPorcentajes[3]=(TextView) findViewById(R.id.tvPorcentaje4);
        btnCalcular=(Button) findViewById(R.id.btnCalcular);
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
    private void ConvertirNotas(){
            nota1 = Double.parseDouble(etNota1.getText().toString());
            nota2 = Double.parseDouble(etNota2.getText().toString());
            nota3 = Double.parseDouble(etNota3.getText().toString());
            nota4 = Double.parseDouble(etNota4.getText().toString());

    }

    private boolean esNotaValida(double nota){
        if(nota>0 && nota <=5){
            return true;
        }else{
            return false;
        }
    }
    private void limpiarNotas (){
       etNota1.setText("");
       etNota2.setText("");
       etNota3.setText("");
       etNota4.setText("");
    }
    public void CalcularNota(View view) {
            boolean porcentajeValido = true;
        try {
            for (int i = 0; i < porcentajes.length; i++) {
                if (porcentajes[i] == 0) {
                    porcentajeValido = false;
                }
            }
            if (porcentajeValido  &&  numEstudiantes > 0 ) {
                ConvertirNotas();
            if (esNotaValida(nota1) && esNotaValida(nota2) && esNotaValida(nota3) && esNotaValida(nota4)) {
                    nota1 = (nota1 * porcentajes[0]);
                    nota2 = (nota2 * porcentajes[1]);
                    nota3 = (nota3 * porcentajes[2]);
                    nota4 = (nota4 * porcentajes[3]);

                    notafinal = nota1 + nota2 + nota3 + nota4;

                    tvNotaFinal.setText("Nota final: " + String.valueOf(notafinal));
                if (notafinal < 3) {
                    nEstudiantesPerdieron++;

                }
                limpiarNotas();
                numEstudiantes--;
                if(numEstudiantes == 0){
                    tvMensajeP.setText("Cantidad de estudiantes que perdieron la materia: ");
                    tvMensaje.setText(String.valueOf(nEstudiantesPerdieron));
                    etNestudiantes.setText("0");
                }else{
                    tvMensajeP.setText("Aún falta ingresar las notas de "+numEstudiantes+ " estudiantes");
                }
            } else {
                Toast.makeText(this, "Las notas deben estar comprendidas en el rango que va desde 1 hasta 5", Toast.LENGTH_LONG).show();
            }
            } else {
                if(!porcentajeValido){
                    Toast.makeText(this, " Es necesario elegir el porcentaje asignado a cada nota", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(this, "La cantidad de estudiantes es cero", Toast.LENGTH_LONG).show();

                }

            }
    } catch (Exception e) {
        Toast.makeText(this, "Error valores invalidos", Toast.LENGTH_LONG).show();

    }
    }

}