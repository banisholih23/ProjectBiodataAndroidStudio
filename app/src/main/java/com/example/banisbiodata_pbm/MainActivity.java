package com.example.banisbiodata_pbm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener {


    EditText namapanjang,namapanggilan,tempatlahir,alamat,hobi,pekerjaan;
    Spinner jkelamin;
    Button tgllahir,proses;
    TextView pengenalandiri,outputhobi,outputpk;
    CheckBox cekboxjava, cekboxsa, cekboxnet, cekboxs1;
    RadioGroup grouppk;
    RadioButton radiosma, radios1, radios2;
    String pk;
    private int mTahun,mBulan,mHari;
    private Button Internal, External;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);

        SharedPreferences customSharedPreference = getSharedPreferences("baniSharedPrefs", Activity.MODE_PRIVATE);

        Internal = findViewById(R.id.save_internal);
        External = findViewById(R.id.save_external);
        namapanjang = (EditText)findViewById(R.id.edt_namapanjang);
        namapanjang.setText(customSharedPreference.getString("NamaPanjang", null));
        namapanggilan = (EditText)findViewById(R.id.edt_namapanggilan);
        namapanggilan.setText(customSharedPreference.getString("NamaPanggilan", null));
        tempatlahir = (EditText)findViewById(R.id.edt_tempatlahir);
        alamat = (EditText)findViewById(R.id.edt_alamat);
        hobi = (EditText)findViewById(R.id.edt_hobi);
        pekerjaan = (EditText)findViewById(R.id.edt_pekerjaan);

        jkelamin = (Spinner)findViewById(R.id.spn_jnklmn);

        tgllahir = (Button)findViewById(R.id.btn_tglahir);
        proses = (Button)findViewById(R.id.btn_proses);

        pengenalandiri = (TextView)findViewById(R.id.txt_pengenalan);
        pengenalandiri.setText(customSharedPreference.getString("PengenalanDiri", null));
        cekboxjava = (CheckBox)findViewById(R.id.cekboxjava);
        grouppk = (RadioGroup)findViewById(R.id.grouppk);
        cekboxsa = (CheckBox)findViewById(R.id.cekboxsa);
        cekboxnet = (CheckBox)findViewById(R.id.cekboxnet);
        cekboxs1 = (CheckBox)findViewById(R.id.cekboxs1);
        outputhobi = (TextView)findViewById(R.id.outputhobi);
        outputpk = (TextView)findViewById(R.id.outputpk);

        radiosma = (RadioButton)findViewById(R.id.radiosma);
        radios1 = (RadioButton)findViewById(R.id.radios1);
        radios2 = (RadioButton)findViewById(R.id.radios2);

        grouppk.setOnCheckedChangeListener(this);

        List<String> listkelamin = new ArrayList<String>();
        listkelamin.add("Pria");
        listkelamin.add("Wanita");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listkelamin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jkelamin.setAdapter(adapter);

        tgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                mTahun = cal.get(Calendar.YEAR);
                mBulan = cal.get(Calendar.MONTH);
                mHari = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDateDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tgllahir.setText(String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year));
                    }
                },mTahun,mBulan,mHari);
                mDateDialog.setTitle("Pilih Tanggal Lahir");
                mDateDialog.show();

            }
        });
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cekboxjava.isChecked() && cekboxnet.isChecked() && cekboxsa.isChecked() && cekboxs1.isChecked()){
                    outputhobi.setText("Bahasa Favorit: C/C++, JAVA, HTML & Phyton");
                }else if(cekboxjava.isChecked() && cekboxnet.isChecked() && cekboxsa.isChecked()){
                    outputhobi.setText("Bahasa Favorit:  C/C++, JAVA & HTML");
                }else if(cekboxjava.isChecked() && cekboxnet.isChecked() && cekboxs1.isChecked()){
                    outputhobi.setText("Bahasa Favorit: C/C++, JAVA & Phyton");
                }else if(cekboxjava.isChecked() && cekboxnet.isChecked()){
                    outputhobi.setText("Bahasa Favorit : C/C++ & JAVA");
                }else if(cekboxnet.isChecked() && cekboxsa.isChecked()) {
                    outputhobi.setText("Bahasa Favorit : JAVA & HTML");
                }else if(cekboxsa.isChecked() && cekboxs1.isChecked()) {
                    outputhobi.setText("Bahasa Favorit: HTML & Phyton");
                }else if(cekboxjava.isChecked() && cekboxsa.isChecked()) {
                    outputhobi.setText("Bahasa Favorit : C/C++ & HTML");
                }else if(cekboxnet.isChecked() && cekboxs1.isChecked()) {
                    outputhobi.setText("Bahasa Favorit : JAVA & Phyton");
                }else if(cekboxjava.isChecked() && cekboxs1.isChecked()) {
                    outputhobi.setText("Bahasa Favorit : C/C++ & Phyton");
                }else if(cekboxjava.isChecked()){
                    outputhobi.setText("Bahasa Favorit : C/C++");
                }else if(cekboxnet.isChecked()){
                    outputhobi.setText("Bahasa Favorit : JAVA");
                }else if(cekboxsa.isChecked()){
                    outputhobi.setText("Bahasa Favorit : HTML");
                }else if(cekboxs1.isChecked()) {
                    outputhobi.setText("Bahasa Favorit : Phyton");
                }else{
                    outputhobi.setText("Bahasa Favorit     : ");

                }
                outputpk.setText(pk);
                cekboxjava.setChecked(false);
                cekboxsa.setChecked(false);
                cekboxnet.setChecked(false);
                cekboxs1.setChecked(false);
                grouppk.clearCheck();

                pengenalandiri.setText("Nama Lengkap    : "+namapanjang.getText().toString()+"\nNama Panggilan  : "+namapanggilan.getText().toString()+"\nJenis Kelamin     : "+jkelamin.getSelectedItem().toString()+"\nTempat Tgl Lahir   : "+
                        tempatlahir.getText().toString()+" "+tgllahir.getText().toString()+ "\nAlamat   : "+alamat.getText().toString()+"\nHobi     : "+hobi.getText().toString()+
                        "\nPekerjaan    : "+pekerjaan.getText().toString()+
                        "");
            }

        });
        Internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setData = pengenalandiri.getText().toString();
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = openFileOutput("DataSaya", Context.MODE_PRIVATE);
                    fileOutputStream.write(setData.getBytes());
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(), "Data Berhasil disimpan di Internal", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        External.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)){
                    File dirExternal = Environment.getExternalStorageDirectory();
                    File createDir = new File(dirExternal.getAbsolutePath()+"/DirektoriBani");

                    if (createDir.exists()){
                        createDir.mkdir();
                        File file = new File(createDir, "FileBani.txt");
                        String setData = pengenalandiri.getText().toString();

                        FileOutputStream fileOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(file);

                            fileOutputStream.write(setData.getBytes());

                            fileOutputStream.close();
                            Toast.makeText(getApplicationContext(), "Data Disimpan di External", Toast.LENGTH_SHORT).show();
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Penyimpanan External Tidak Tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });
    //public void saveClicked(View v) {
            //try {
                //OutputStreamWriter out =
                //new OutputStreamWriter(openFileOutput(STORAGE_SERVICE, 0));
                //out.write(txtEditor.getText().toString());
                //out.close();
                //Toast.makeText(this, "The content are saved in the file ", Toast.LENGTH_LONG). show();
            //}
           //catch (Throwable t) {
           //Toast.makeText(this, "Exception: " +t.toString(), Toast.LENGTH_LONG).show();
           //}
        //}
    }
    private void savePreferences() {
        SharedPreferences customSharedPreference =
                getSharedPreferences("baniSharedPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = customSharedPreference.edit();
        editor.putString("PengenalanDiri", pengenalandiri.getText().toString());
        editor.putString("NamaPanjang", namapanjang.getText().toString());
        editor.putString("NamaPanggilan", namapanggilan.getText().toString());
        editor.commit();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int check) {
        // TODO Auto-generated method stub
        if(check==R.id.radiosma){
            pk="Pendidikan Terakhir : SMA";

        }else if(check==R.id.radios1){
            pk="Pendidikan Terakhir : Strata 1 (S1)";

        }else if (check==R.id.radios2) {
            pk="Pendidikan Terakhir : Strata 2 (S2)";
        }else{
            pk="Pendidikan Terakhir : ";
        }

    }
}