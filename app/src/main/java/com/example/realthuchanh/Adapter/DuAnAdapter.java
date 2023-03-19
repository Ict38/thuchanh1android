package com.example.realthuchanh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realthuchanh.R;
import com.example.realthuchanh.model.DuAn;

import java.util.List;

public class DuAnAdapter extends RecyclerView.Adapter<DuAnAdapter.DuAnViewHolder> {

    private List<DuAn> mList, bList;
    private Context context;
    private DuAnItemListener duAnItemListener;

    public DuAnAdapter(List<DuAn> mList, Context context) {
        this.mList = mList;
        this.bList = mList;
        this.context = context;
    }

    public List<DuAn> getbList() {
        return bList;
    }

    public void setDuAnItemListener(DuAnItemListener duAnItemListener) {
        this.duAnItemListener = duAnItemListener;
    }

    @NonNull
    @Override
    public DuAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new DuAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuAnViewHolder holder, int position) {
        DuAn duAn = mList.get(position);
        if(duAn == null) return;
        holder.name.setText(duAn.getName());
        holder.st.setText(duAn.getSt());
        holder.dl.setText(duAn.getDl());
        if(duAn.isFinish()) holder.finish.setChecked(true);
        else holder.finish.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public DuAn getItemAt(int position) {
        return mList.get(position);
    }

    public void add(DuAn duAn) {
        mList.add(duAn);
        notifyDataSetChanged();
    }

    public void update(int rListPosition, DuAn duAn) {
        mList.set(rListPosition,duAn);
        notifyDataSetChanged();
    }

    public void filterList(List<DuAn> filterList) {
        mList = filterList;
        notifyDataSetChanged();
    }

    public class DuAnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name,st,dl;
        private CheckBox finish;
        private Button remove;
        public DuAnViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rname);
            st = itemView.findViewById(R.id.rstart);
            dl = itemView.findViewById(R.id.rdl);
            finish = itemView.findViewById(R.id.rfinish);
            remove = itemView.findViewById(R.id.remove);
            itemView.setOnClickListener(this);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList.remove(getAdapterPosition());
                    Toast.makeText(context.getApplicationContext(), "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(View view) {
            if(duAnItemListener == null) return;
            duAnItemListener.onItemClick(view, getAdapterPosition());
        }
    }
    public interface DuAnItemListener{
        void onItemClick(View view, int position);
    }
}
