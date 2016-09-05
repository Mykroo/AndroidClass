package com.uaa.controlesbasicos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MainActivity extends AppCompatActivity {


    private TextView salida;
    private EditText entrada;
    private Button btnAceptar;

    private CheckBox[] opciones;
    private Button cb_btnAceptar, rb_btnAceptar;

    private RadioGroup rg;
    private int radioSeleccionado=-1;
    private String mensaje;

    private Spinner spinnerList1;
    private Spinner spinnerList2;
    private Context context;

    private TextView calc_txt;
    private String btn_click,buffer="",tmp="0";
    private Button[] calc_btns;
    private double res;
    private int op,calcu_ids[] = {
            R.id.id_0, R.id.id_1, R.id.id_1, R.id.id_2, R.id.id_3, R.id.id_4, R.id.id_5, R.id.id_6,
            R.id.id_7, R.id.id_8, R.id.id_9, R.id.id_c,R.id.id_del,R.id.id_mul,R.id.id_eq, R.id.id_empt,
            R.id.id_div,R.id.id_dot,R.id.id_less,R.id.id_plus
    };

    private ArrayAdapter<String> getAdapter(String[] array) {
        return new ArrayAdapter<>(this.context, android.R.layout.simple_spinner_item, array);
    }

    private ArrayAdapter<CharSequence> getApater(int recurso) {
        return ArrayAdapter.createFromResource(this.context,recurso,android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //*this.initMainActivity();
        //this.initCheckBoxActivity();
        //this.initRadioActivity();
        this.initCalcuActivity();
        /*
        this.context=MainActivity.this;


        this.spinnerList1=(Spinner) findViewById(R.id.spinner_list1);
        this.spinnerList2=(Spinner) findViewById(R.id.spinner_list2);

        final String[] LENGUAJES ={
                "JAVA","PHP","C","C++"};

        ArrayAdapter<String> adaptador1= this.getAdapter(LENGUAJES);

        //ArrayAdapter<CharSequence> adaptador2 = this.getAdapter(R.array.sa_lenguajes);

        this.spinnerList1.setAdapter(adaptador1);

        //this.spinnerList2.setAdapter(adaptador2);

        this.spinnerList1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Item seleccionsds:" + LENGUAJES[position], Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
    private void initCalcuActivity(){
        this.calc_btns=new Button[20];

        setContentView(R.layout.calcu);
        //calc_txt.setText("oli" , TextView.BufferType.EDITABLE);
        for (int i=0; i<calcu_ids.length;i++){
            this.calc_btns[i]=(Button) findViewById(calcu_ids[i]);
        }
        for (int i=0;i<calc_btns.length;i++){
            this.calc_btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    btn_click=((Button)v).getText().toString();

                    //calc_txt.setText(calc_txt.getText()+btn_click);
                    //Toast.makeText(MainActivity.this,btn_click, Toast.LENGTH_SHORT).show();
                    calc_function(v.getId());

                }
            });
        }
    }

    private void calc_function(int id){
        // Create a Pattern object

        this.calc_txt=(TextView) findViewById(R.id.txt_cal);
        Pattern r = Pattern.compile("^[0-9.]$*");

        // Now create matcher object.
        Matcher m = r.matcher(btn_click);
        if (m.find()){
            switch (id){
                case R.id.id_dot:
                    if (buffer.contains(".")){
                        Toast.makeText(MainActivity.this, "Ya hay un punto puto", Toast.LENGTH_SHORT).show();
                    }else {
                        buffer+=btn_click;
                        calc_txt.setText(buffer);
                    }
                    break;
                default:
                    buffer+=btn_click;
                    calc_txt.setText(buffer);
                    break;

            }
        }else{
            switch (id){
                case R.id.id_del:
                    if(buffer.length()>0) {
                        buffer = buffer.substring(0, buffer.length() - 1);
                        calc_txt.setText(buffer);
                    }
                    break;
                case R.id.id_plus:

                    res = (Float.parseFloat(tmp) + Float.parseFloat(buffer));
                    op = R.id.id_plus;
                    tmp = buffer;
                    buffer = "";
                    calc_txt.setText(buffer);
                    Toast.makeText(MainActivity.this, "Suma " + Double.toString(res), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.id_less:
                    op=R.id.id_less;
                    res= (Float.parseFloat(tmp) - Float.parseFloat(buffer));
                    buffer="";
                    calc_txt.setText(buffer);
                    Toast.makeText(MainActivity.this, "Minuuus " + Double.toString(res), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.id_mul:
                    op=R.id.id_mul;
                    res= (Float.parseFloat(tmp) * Float.parseFloat(buffer));
                    buffer="";
                    calc_txt.setText(buffer);
                    Toast.makeText(MainActivity.this, "multiplication" + Double.toString(res), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.id_div:
                    op=R.id.id_div;
                    res= (Float.parseFloat(tmp) / Float.parseFloat(buffer));
                    buffer="";
                    calc_txt.setText(buffer);
                    Toast.makeText(MainActivity.this, "Divide y venceras " + Double.toString(res), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.id_c:
                    op=0;
                    buffer="0";
                    res=0;
                    tmp="0";
                    calc_txt.setText(buffer);
                    Toast.makeText(MainActivity.this, "Borradooooooo!!!! " + Double.toString(res), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.id_eq:
                    switch (op) {
                        case R.id.id_plus:
                            res= (Float.parseFloat(tmp) + Float.parseFloat(buffer));
                            Toast.makeText(MainActivity.this, "Suma " + Double.toString(res), Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.id_less:
                            res= (Float.parseFloat(tmp) - Float.parseFloat(buffer));
                            Toast.makeText(MainActivity.this, "Suma " + Double.toString(res), Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.id_mul:
                            res= (Float.parseFloat(tmp) * Float.parseFloat(buffer));
                            Toast.makeText(MainActivity.this, "Suma " + Double.toString(res), Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.id_div:
                            res= (Float.parseFloat(tmp) / Float.parseFloat(buffer));
                            Toast.makeText(MainActivity.this, "Suma " + Double.toString(res), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            //tmp=buffer="";
                            //res=0;
                            tmp = "0";
                            //Toast.makeText(MainActivity.this, "Iguakasdfjaskd,fnjkasd", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    buffer=Double.toString(res);
                    calc_txt.setText(buffer);
                    op=0;
                    break;
                default:
                    tmp=buffer;
                    buffer="";
                    calc_txt.setText(buffer);
                    Toast.makeText(MainActivity.this, "Not Match", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void initRadioActivity() {
        setContentView(R.layout.radio);

        this.rg  = (RadioGroup) findViewById(R.id.radio_buttons);
        this.rb_btnAceptar = (Button) findViewById(R.id.btn_radioAceptar);

        this.rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged( RadioGroup group, int checkedId) {
                        radioSeleccionado = checkedId;
                    }

                }
        );
        this.rb_btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioSeleccionado) {
                    case R.id.rb_cpp:
                        mensaje = "C++";
                        break;
                    case R.id.rb_rb:
                        mensaje = "Ruby";
                        break;
                    case R.id.rb_java:
                        mensaje = "Java";
                        break;
                }

                Toast.makeText(MainActivity.this, "Tu lenguaje favorito " + mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initMainActivity() {
        setContentView(R.layout.activity_main);
        this.entrada = (EditText) findViewById(R.id.edEntrada);
        this.salida = (TextView) findViewById(R.id.txSalida);
        this.btnAceptar = (Button) findViewById(R.id.btnAceptar);

        this.btnAceptar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String texto = entrada.getText().toString();
                        salida.setText(texto);
                    }
                }
        );
    }

        private void initCheckBoxActivity() {
            setContentView(R.layout.check_boxes);
            this.cb_btnAceptar = (Button) findViewById(R.id.cb_btn_aceptar);

            this.opciones = new CheckBox[]{
                    (CheckBox) findViewById(R.id.cb_ios),
                    (CheckBox) findViewById(R.id.cb_android),
                    (CheckBox) findViewById(R.id.cb_win_phone)
            };

            this.cb_btnAceptar.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            int opcSeleccionada = 0;
                            for (int i=0; i < opciones.length; i++){
                                if (opciones[i].isChecked()){
                                    opcSeleccionada++;
                                }
                            }
                            Toast.makeText(MainActivity.this,"Opciones seleccionadas: " + opcSeleccionada, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

}


