package com.example.clasemagistral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterNombre extends RecyclerView.Adapter<adapterNombre.holder>{

    Context context;
    ArrayList<item> list;
    adapterNombre(Context c){
        context=c;
        list=new ArrayList<>();
    }

    public void add(item i) {
        list.add(i);
        notifyItemInserted(list.indexOf(i));
    }

    public void eliminar() {
        int tam=list.size();
        list.clear();
        notifyItemRangeRemoved(0,tam);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        holder vh = new holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final item it = list.get(position);
        holder.t.setText(it.getTipo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView t;
        public holder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.textos);
        }
    }
}
