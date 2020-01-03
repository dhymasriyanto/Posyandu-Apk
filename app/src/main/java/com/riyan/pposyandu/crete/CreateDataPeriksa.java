package com.riyan.pposyandu.crete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.riyan.pposyandu.R;
import com.riyan.pposyandu.util.database.DaoHandler;
import com.riyan.pposyandu.util.database.DaoSession;
import com.riyan.pposyandu.util.database.TblAnak;
import com.riyan.pposyandu.util.database.TblAnakDao;
import com.riyan.pposyandu.util.database.TblAnakPeriksa;
import com.riyan.pposyandu.view.DataPeriksaActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CreateDataPeriksa extends AppCompatActivity {



    @BindView(R.id.edtbb)
    EditText edBb;

    @BindView(R.id.edttb)
    EditText edTb;

    @BindView(R.id.edtbulan)
    EditText edBulan;

    @BindView(R.id.btnSimpan)
    Button btnSimpan;



    private Unbinder unbinder;
    private Long Id;
    private DaoSession daoSession;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data_periksa);

        unbinder = ButterKnife.bind(this);
        daoSession = DaoHandler.getInstance(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data Periksa");
        bundle = getIntent().getExtras();
        Id = bundle.getLong("id");

        Toast.makeText(this, "id= ok "+Id, Toast.LENGTH_SHORT).show();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bb = edBb.getText().toString();
                String tb = edTb.getText().toString();
                String bulan = edBulan.getText().toString();



                if ( bb.isEmpty() || tb.isEmpty() ||bulan.isEmpty()) {
                    Toast.makeText(CreateDataPeriksa.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else {
                    TblAnakPeriksa tblAnakPeriksa = new TblAnakPeriksa();
                    tblAnakPeriksa.setIdAnak(Id);
                    tblAnakPeriksa.setBb(bb);
                    tblAnakPeriksa.setTb(tb);
                    tblAnakPeriksa.setBulan(bulan);
                    daoSession.getTblAnakPeriksaDao().insert(tblAnakPeriksa);

                    DataPeriksaActivity dataPeriksaActivity = new DataPeriksaActivity();
                    dataPeriksaActivity.onRefresh();

                    Toast.makeText(CreateDataPeriksa.this, "Berhasil Menambahkan Data Periksa dan Berdasarkan ID = "+ Id, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
