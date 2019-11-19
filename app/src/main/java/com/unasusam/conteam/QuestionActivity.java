package com.unasusam.conteam;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    Spinner spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8,spinner9;
    SeekBar seek1;
    TextView seekText1;
    TextView nome_apresentador;
    TextView titulo_apresentacao;
    Apresentacao apresentacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle intent = getIntent().getExtras();
        if(intent != null) {
            apresentacao = (Apresentacao) intent.get("apresentacao");
        }
        nome_apresentador = findViewById(R.id.nome_apresentador);
        nome_apresentador.setText(apresentacao.getNome_apresentador());
        titulo_apresentacao = findViewById(R.id.titulo);
        titulo_apresentacao.setText(apresentacao.getTitulo_apresentacao());


        seekText1 = findViewById(R.id.seek_text_1);
        seek1 = findViewById(R.id.seek_bar_1);
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekText1.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}
