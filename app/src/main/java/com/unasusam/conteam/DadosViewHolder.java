package com.unasusam.conteam;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DadosViewHolder extends RecyclerView.ViewHolder {

    TextView nome_apresentador;
    TextView titulo_apresentacao;
    ImageView foto_apresentador;
    RelativeLayout relative_layout;

    public DadosViewHolder(@NonNull View itemView) {
        super(itemView);
        nome_apresentador = itemView.findViewById(R.id.nome_apresentador);
        titulo_apresentacao = itemView.findViewById(R.id.tema_apresentacao);
        foto_apresentador = itemView.findViewById(R.id.photo);
        relative_layout = itemView.findViewById(R.id.rltv_layout);
    }
}
