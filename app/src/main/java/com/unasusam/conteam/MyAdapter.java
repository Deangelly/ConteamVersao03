package com.unasusam.conteam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<DadosViewHolder> {

    List<Apresentacao> listDados = new ArrayList<>();
    Context context;
    Activity activity;
    private com.unasusam.conteam.MainActivity feedActivity;
    FirebaseStorage st;
    MyAdapter(Context context, Activity activity, FirebaseStorage st, MainActivity mainActivity){
        this.context = context;
        this.activity = activity;
        this.st = st;
        this.feedActivity = mainActivity;
    }



    @NonNull
    @Override
    public DadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apresentador,parent,false);
        DadosViewHolder viewHolder = new DadosViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DadosViewHolder holder, final int position) {

        holder.nome_apresentador.setText(listDados.get(position).getNome_apresentador());
        holder.titulo_apresentacao.setText(listDados.get(position).getTitulo_apresentacao());

        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,QuestionActivity.class);
                intent.putExtra("apresentacao",(Serializable) listDados.get(position));
                activity.startActivity(intent);

            }
        });
        Glide.with(feedActivity)
                .load(listDados.get(position).getFoto_apresentador()).into(holder.foto_apresentador);

    }

    @Override
    public int getItemCount() {
        return listDados.size();
    }


    public void setDados(List<Apresentacao> listDados){
        this.listDados.clear();
        this.listDados.addAll(listDados);
    }

    public void setDado(Apresentacao apresentacao){
        this.listDados.add(apresentacao);
    }

    public void clearList(){
        this.listDados.clear();
    }

   /* public Bitmap getBitMapFromUri(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap mBitmap = BitmapFactory.decodeStream(input);
            return mBitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }*/
}
