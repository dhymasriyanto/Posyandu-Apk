package com.riyan.pposyandu.adapter;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.riyan.pposyandu.R;
import com.riyan.pposyandu.util.database.TblAnak;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAnakAdapter extends RecyclerView.Adapter<DataAnakAdapter.ViewHolder> {

    private List<TblAnak> list;
    private DataAnakaAdapterCallback dataAnakaAdapterCallback;
//    private OnItemClickCallback onItemClickCallback;


    public DataAnakAdapter(List<TblAnak> tblAnakList, DataAnakaAdapterCallback adapterCallback) {
        this.list = tblAnakList;
        this.dataAnakaAdapterCallback = adapterCallback;
    }

//    public DataAnakAdapter(List<TblAnak> tblAnakList) {
//        this.list= tblAnakList;
//    }
//
//    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
//        this.onItemClickCallback = onItemClickCallback;
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anak, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TblAnak item = list.get(position);

        String nama = item.getNama();
        String umur = item.getUmur();
//        String bb = item.getBb();
//        String tb = item.getTb();
//        String bulan = item.getBulan();

        holder.tvNama.setText(nama);
        holder.tvUmur.setText(umur +" Tahun ");
//        holder.tvBb.setText(bb);
//        holder.tvTb.setText(tb);
//        holder.tvBulan.setText(bulan);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size =this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0,size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNama)
        TextView tvNama;

        @BindView(R.id.tvUmur)
        TextView tvUmur;

//        @BindView(R.id.tvBb)
//        TextView tvBb;
//
//        @BindView(R.id.tvTb)
//        TextView tvTb;
//
//        @BindView(R.id.tvBulan)
//        TextView tvBulan;

        @BindView(R.id.ivDelete)
        ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataAnakaAdapterCallback.onDelete(getAdapterPosition());
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dataAnakaAdapterCallback.onClick(getAdapterPosition());
//                    return;r
//                }
//            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    dataAnakaAdapterCallback.onLongClick(getAdapterPosition());
                    return true;
                }
            });

        }
    }

    public interface DataAnakaAdapterCallback{
        void onLongClick(int position);

        void onDelete(int position);
    }

//
//    public interface OnItemClickCallback {
//        void onItemClicked(TblAnak data);
//    }
}
