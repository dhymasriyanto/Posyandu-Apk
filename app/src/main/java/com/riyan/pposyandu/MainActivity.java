package com.riyan.pposyandu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.riyan.pposyandu.adapter.DataAnakAdapter;
import com.riyan.pposyandu.crete.CreateActivity;
import com.riyan.pposyandu.login.LoginActivity;
import com.riyan.pposyandu.util.database.DaoHandler;
import com.riyan.pposyandu.util.database.DaoSession;
import com.riyan.pposyandu.util.database.TblAnak;
import com.riyan.pposyandu.util.database.TblAnakDao;
import com.riyan.pposyandu.view.DataPeriksaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DataAnakAdapter.DataAnakaAdapterCallback {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.rvNote)
    RecyclerView rvNote;

    @BindView(R.id.fabAdd)
    FloatingActionButton faAdd;

    private DaoSession daoSession;
    private DataAnakAdapter dataAnakAdapter;

    private List<TblAnak> tblAnakList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        daoSession = DaoHandler.getInstance(this);


        // fungsi untuk membaca data dari database


        tblAnakList = daoSession.getTblAnakDao().queryBuilder().list();
        dataAnakAdapter = new DataAnakAdapter(tblAnakList, this);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setItemAnimator(new DefaultItemAnimator());
        rvNote.setAdapter(dataAnakAdapter);
        dataAnakAdapter.notifyDataSetChanged();

        faAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });

//        initRecyclerviews();
    }


    private void showPeriksa() {

    }


//
//    private void initRecyclerviews() {
//        tblAnakList = daoSession.getTblAnakDao().queryBuilder().list();
//        dataAnakAdapter = new DataAnakAdapter(tblAnakList);
//        rvNote.setLayoutManager(new LinearLayoutManager(this));
//        rvNote.setItemAnimator(new DefaultItemAnimator());
//        rvNote.setAdapter(dataAnakAdapter);
//
//        dataAnakAdapter.setOnItemClickCallback(new DataAnakAdapter.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(TblAnak data) {
//                showSelectDetail(data);
//            }
//        });
//    }

//    private void showSelectDetail(TblAnak data) {
//        Intent i = new Intent(MainActivity.this, DataPeriksaActivity.class);
//        i.putExtra("nama", data.getNama());
//        i.putExtra("umur", data.getUmur());
//        i.putExtra("bb", data.getBb());
//        i.putExtra("tb", data.getTb());
//        i.putExtra("bulan", data.getBulan());
//        startActivity(i);
//
//    }


    @Override
    public void onLongClick(int position) {
        long id = tblAnakList.get(position).getId();
        String nama = tblAnakList.get(position).getNama();
        String umur = tblAnakList.get(position).getUmur();
        String bb = tblAnakList.get(position).getBb();
        String tb = tblAnakList.get(position).getTb();
        String bulan = tblAnakList.get(position).getBulan();

        Intent i = new Intent(MainActivity.this, DataPeriksaActivity.class);
        i.putExtra("id", id);
        i.putExtra("nama", nama);
        i.putExtra("umur", umur);
        i.putExtra("bb", bb);
        i.putExtra("tb", tb);
        i.putExtra("bulan", bulan);

        Toast.makeText(this, "ID = " + id, Toast.LENGTH_SHORT).show();

        startActivity(i);
    }

    @Override
    public void onDelete(int position) {
        String nama = tblAnakList.get(position).getNama();
        showDialogDelete(position, nama);
    }

    private void showDialogDelete(final int position, String nama) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah Anda Yakin Akan Menghapus Data  " + nama + "??");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "YA",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // fungsi delete berdasarkan id
                        long idtbl = tblAnakList.get(position).getId();
                        daoSession.getTblAnakDao().deleteByKey(idtbl);

                        tblAnakList.remove(position);
                        dataAnakAdapter.notifyItemRemoved(position);
                        dataAnakAdapter.notifyItemRangeChanged(position, tblAnakList.size());

                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            startActivity(new Intent(this, LoginActivity.class));

        }
        return true;
    }
}
