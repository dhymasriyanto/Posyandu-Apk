package com.riyan.pposyandu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riyan.pposyandu.R;
import com.riyan.pposyandu.util.database.TblAnak;
import com.riyan.pposyandu.util.database.TblAnakPeriksa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAnakAdapterPeriksa extends RecyclerView.Adapter<DataAnakAdapterPeriksa.ViewHolder> {

    TextView tvBb,tvTb,tvBulan;


    private List<TblAnakPeriksa>tblAnakPeriksaList;
    private DataAnakaAdapterPeriksaCallback dataAnakaAdapterPeriksaCallback;

    public DataAnakAdapterPeriksa(List<TblAnakPeriksa> tblAnakPeriksaList, DataAnakaAdapterPeriksaCallback dataAnakaAdapterCallback){
        this.tblAnakPeriksaList = tblAnakPeriksaList;
        this.dataAnakaAdapterPeriksaCallback = dataAnakaAdapterCallback;
    }

//    private DataAnakAdapterPeriksa.DataAnakaAdapterCallback dataAnakaAdapterCallback;

//    public DataAnakAdapterPeriksa(List<TblAnakPeriksa> tblAnakPeriksaList, DataAnakAdapterPeriksa.DataAnakaAdapterCallback adapterCallback) {
//        this.list = tblAnakList;
//        this.dataAnakaAdapterCallback = adapterCallback;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_periksa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TblAnakPeriksa tblAnakPeriksa = tblAnakPeriksaList.get(position);

        String bb = tblAnakPeriksa.getBb();
        String tb = tblAnakPeriksa.getTb();
        String bulan = tblAnakPeriksa.getBulan();


        holder.tvBb.setText(bb +" Kg");
        holder.tvTb.setText(tb + " Cm");
        holder.tvBulan.setText(bulan);


    }

    @Override
    public int getItemCount() {
        return tblAnakPeriksaList.size();
    }

    public void clear() {
        int size =this.tblAnakPeriksaList.size();
        this.tblAnakPeriksaList.clear();
        notifyItemRangeRemoved(0,size);
    }

    public interface DataAnakaAdapterPeriksaCallback {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBeratBadan)
        TextView tvBb;

        @BindView(R.id.tvTinggiBadan)
        TextView tvTb;

        @BindView(R.id.tvBulan)
        TextView tvBulan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
