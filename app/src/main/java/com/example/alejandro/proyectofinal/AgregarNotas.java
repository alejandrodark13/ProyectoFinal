package com.example.alejandro.proyectofinal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alejandro.proyectofinal.BasesDeDatos.Conexion;
import com.example.alejandro.proyectofinal.BasesDeDatos.DAOusuario;
import com.example.alejandro.proyectofinal.BasesDeDatos.Usuario;

import java.util.Calendar;

public class AgregarNotas extends AppCompatActivity implements View.OnClickListener {



    private Button btnVerNotas, btnFecha,btnHora;
    private Spinner opciones;
    private EditText txtFecha,txtHora;
    private  int dia,mes,ano,hora,minutos;

    EditText txtTititulo, txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);

        //Es el spinner para dar a elegir entre las dos opciones Si es nota o tarea en la interfaz
        opciones = (Spinner)findViewById(R.id.spNotaoTarea);
        // String [] llenar = {"Tares", "Notas" };
        String datos []=new String[2];
        datos[0]="Nota";
        datos[1]="Tarea";
        ArrayAdapter <String> adapter = new  ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
        opciones.setAdapter(adapter);


        txtTititulo = (EditText) findViewById(R.id.txtTitulo);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        opciones = (Spinner) findViewById(R.id.spNotaoTarea);
    btnFecha = (Button)findViewById(R.id.btnFecha);
    btnHora = (Button)findViewById(R.id.btnHora);
    txtFecha = (EditText) findViewById(R.id.txtFecha);
    txtHora = (EditText) findViewById(R.id.txtHora);
        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);



    //Esto solo es para cambiar de interfaz a la de ver notas
    btnVerNotas =(Button)findViewById(R.id.btnVerNotas);
        btnVerNotas.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View v) {


            Intent siguiente1 = new Intent(AgregarNotas.this, VerNotas.class);
            startActivity(siguiente1);
        }
    });
}

    //Este metodo da la fecha con orden de dia, mes y año al igual que la hora con orden de hora y minutos
    @Override
    public void onClick(View v) {
        if(v==btnFecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==btnHora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txtHora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }


    /*public void btnAgregar(View v){
        DAOusuario ado = new DAOusuario(getApplicationContext());
        if( !txtTititulo.getText().toString().isEmpty() && !txtDescripcion.getText().toString().isEmpty() &&
                !opciones.getSelectedItem().toString().isEmpty() && !txtFecha.getText().toString().isEmpty()
                && txtHora.getText().toString().isEmpty()){

            long result = ado.add(
                    new Usuario( 0,txtTititulo.getText().toString(), txtDescripcion.getText().toString(),
                            opciones.getSelectedItem().toString(),txtFecha.getText().toString() , txtHora.getText().toString())
            );

            if (result>0){
                Toast.makeText(this, "Adición exitosa",
                        Toast.LENGTH_LONG).show();

                txtTititulo.setText("");
                txtDescripcion.setText("");
                txtFecha.setText("");
                txtHora.setText("");
            }
        }else{
            Toast.makeText(this, "Los datos no deben de estar vacios",Toast.LENGTH_LONG).show();
        }
    }*/
    public void Agregar(View view){
        Conexion admin = new Conexion(this,"usuario",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = txtTititulo.getText().toString();
        String descri = txtDescripcion.getText().toString();
        String Fe = txtFecha.getText().toString();
        String Ho = txtHora.getText().toString();
        String spn = opciones.getSelectedItem().toString();

        if (!nombre.isEmpty() && !descri.isEmpty() && !Fe.isEmpty() && !Ho.isEmpty()){
            ContentValues registrar = new ContentValues();
            registrar.put("Titulo",nombre);
            registrar.put("Descripcion", descri);
            registrar.put("TareaoNota", spn);
            registrar.put("Fecha",Fe);
            registrar.put("Hora",Ho);

            BaseDeDatos.insert("usuario", null, registrar);
            BaseDeDatos.close();
            txtTititulo.setText("");
            txtDescripcion.setText("");
            txtFecha.setText("");
            txtHora.setText("");
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Todos los campos tienen que estar llenos",Toast.LENGTH_SHORT).show();
        }
    }
}

