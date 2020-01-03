package com.riyan.pposyandu.crete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.riyan.pposyandu.MainActivity;
import com.riyan.pposyandu.R;
import com.riyan.pposyandu.util.database.DaoHandler;
import com.riyan.pposyandu.util.database.DaoSession;
import com.riyan.pposyandu.util.database.TblAnak;
import com.riyan.pposyandu.util.database.TblAnakDao;
import com.riyan.pposyandu.util.database.TblAnakPeriksa;
import com.riyan.pposyandu.util.database.TblAnakPeriksaDao;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CreateActivity extends AppCompatActivity {
    @BindView(R.id.edtnama)
    EditText ednama;

    @BindView(R.id.edtumur)
    EditText edumur;



    @BindView(R.id.btnSimpan)
    Button btsimpan;

    private Unbinder unbinder;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Data Anak");

        unbinder = ButterKnife.bind(this);
        daoSession = DaoHandler.getInstance(this);

        btsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = ednama.getText().toString();
                String umur = edumur.getText().toString();


                if (nama.isEmpty() || umur.isEmpty() ) {
                    Toast.makeText(CreateActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else {
                    TblAnak tblAnak = new TblAnak();
                    tblAnak.setNama(nama);
                    tblAnak.setUmur(umur);
                    daoSession.getTblAnakDao().insert(tblAnak);

//                    TblAnakPeriksa tblAnakPeriksa = new TblAnakPeriksa();
//                    tblAnakPeriksa.setIdAnak(daoSession.getTblAnakDao().queryBuilder().limit(1).orderDesc(TblAnakDao.Properties.Id).list().get(0).getId());
//                    daoSession.getTblAnakPeriksaDao().insert(tblAnakPeriksa);

                    Toast.makeText(CreateActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreateActivity.this, "ID = "+daoSession.getTblAnakDao().queryBuilder().limit(1).orderDesc(TblAnakDao.Properties.Id).list().get(0).getNama(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
