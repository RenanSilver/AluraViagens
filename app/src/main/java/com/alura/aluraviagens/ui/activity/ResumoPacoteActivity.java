package com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alura.aluraviagens.R;
import com.alura.aluraviagens.model.Pacote;
import com.alura.aluraviagens.util.DataUtil;
import com.alura.aluraviagens.util.DiasUtil;
import com.alura.aluraviagens.util.MoedaUtil;
import com.alura.aluraviagens.util.ResourceUtil;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do pacote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(TITULO_APPBAR);

        Intent intent = getIntent();
        if(intent.hasExtra("pacote")){
            final Pacote pacote = (Pacote) intent.getSerializableExtra("pacote");

            mostraLocal(pacote);
            mostraImagem(pacote);
            mostraDias(pacote);
            mostraPreco(pacote);
            mostraData(pacote);

            Button botaoRealizaPagamento = findViewById(R.id.resumo_pacote_botao_realiza_pagamento);
            botaoRealizaPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ResumoPacoteActivity.this,
                            PagamentoActivity.class);
                    intent.putExtra("pacote",pacote);
                    startActivity(intent);
                }
            });
        }

    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaDaViagem = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaDaViagem);
    }


    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_pacote_preco);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.resumo_pacote_dias);
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_pacote_imagem);
        Drawable drawableDoPacote = ResourceUtil
                .devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.resumo_pacote_local);
        local.setText(pacote.getLocal());
    }
}