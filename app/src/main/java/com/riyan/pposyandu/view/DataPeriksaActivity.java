package com.riyan.pposyandu.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.riyan.pposyandu.ColumnChartActivity;
import com.riyan.pposyandu.ColumnChartActivityTinggi;
import com.riyan.pposyandu.R;
import com.riyan.pposyandu.adapter.DataAnakAdapter;
import com.riyan.pposyandu.adapter.DataAnakAdapterPeriksa;
import com.riyan.pposyandu.crete.CreateDataPeriksa;
import com.riyan.pposyandu.login.LoginActivity;
import com.riyan.pposyandu.util.database.DaoHandler;
import com.riyan.pposyandu.util.database.DaoSession;
import com.riyan.pposyandu.util.database.TblAnak;
import com.riyan.pposyandu.util.database.TblAnakPeriksa;
import com.riyan.pposyandu.util.database.TblAnakPeriksaDao;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DataPeriksaActivity extends AppCompatActivity implements DataAnakAdapterPeriksa.DataAnakaAdapterPeriksaCallback {
    @BindView(R.id.tvNama)
    TextView tvNama;

    @BindView(R.id.tvUmur)
    TextView tvUmur;

    @BindView(R.id.empty_view)
    TextView emptyView;





    @BindView(R.id.rvPeriksa)
    RecyclerView rvPeriksa;

    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    private Bundle bundle;
    private DaoSession daoSession;
    private Long Id;
    private String nama;
    private String umur;
    private String bb;
    private String tb;
    private String bulan;

    private List<TblAnak> tblAnakList;
    private List<TblAnakPeriksa> tblAnakPeriksaList;
    private DataAnakAdapterPeriksa dataAnakAdapterPeriksa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_periksa);

        ButterKnife.bind(this);

        daoSession = DaoHandler.getInstance(this);

        bundle = getIntent().getExtras();
        String title;
        if (bundle != null) {
            Id = bundle.getLong("id");
            nama = bundle.getString("nama");
            umur = bundle.getString("umur");
        }else {
            title = "No data";
        }

        tvNama.setText(nama);
        tvUmur.setText(umur +" Tahun");

//        Toast.makeText(this, "id ="+Id, Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Periksa");

        tblAnakPeriksaList = daoSession.getTblAnakPeriksaDao().queryBuilder().where(TblAnakPeriksaDao.Properties.IdAnak.eq(Id)).list();
        if (tblAnakPeriksaList.isEmpty()) {
            rvPeriksa.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            rvPeriksa.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

        dataAnakAdapterPeriksa = new DataAnakAdapterPeriksa(tblAnakPeriksaList, this);
        rvPeriksa.setLayoutManager(new LinearLayoutManager(this));
        rvPeriksa.setItemAnimator(new DefaultItemAnimator());
        rvPeriksa.setAdapter(dataAnakAdapterPeriksa);
        dataAnakAdapterPeriksa.notifyDataSetChanged();
//        onRefresh();


        // Funsi Untuk Membaca Data Dari Database


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DataPeriksaActivity.this, CreateDataPeriksa.class);
                i.putExtra("id", Id);
                startActivity(i);
//                Toast.makeText(DataPeriksaActivity.this, "id= ok "+Id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tblAnakPeriksaList.clear();
        tblAnakPeriksaList.addAll(tblAnakPeriksaList = daoSession.getTblAnakPeriksaDao().queryBuilder().where(TblAnakPeriksaDao.Properties.IdAnak.eq(Id)).list());
        dataAnakAdapterPeriksa.notifyDataSetChanged();
    }

    public void onRefresh() {
        rvPeriksa.setAdapter(dataAnakAdapterPeriksa);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_grafik,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_berat_badan) {
            startActivity(new Intent(this, ColumnChartActivity.class));
        }else if (item.getItemId() == R.id.action_tinggi_badan){
            startActivity(new Intent(this, ColumnChartActivityTinggi.class));
        }
        return true;
    }
}
