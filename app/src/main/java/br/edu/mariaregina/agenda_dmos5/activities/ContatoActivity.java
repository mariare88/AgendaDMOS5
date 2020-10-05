package br.edu.mariaregina.agenda_dmos5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.constants.Constantes;
import br.edu.mariaregina.agenda_dmos5.data.ContatoDAO;
import br.edu.mariaregina.agenda_dmos5.model.Contato;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener {



        private ListView contatoListView;
        private TextView semDadosTextView;
        private List<Contato> contatoList;
        private ArrayAdapter<Contato> arrayAdapter;
        private FloatingActionButton adicionarActionButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_novo__contato);

            contatoListView = findViewById(R.id.listview_contato);
            semDadosTextView = findViewById(R.id.textview_sem_dados);
            adicionarActionButton = findViewById(R.id.fab_add_contato);


            contatoList = ContatoDAO.getInstante().all();
            arrayAdapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatoList);


            contatoListView.setAdapter(arrayAdapter);
            adicionarActionButton.setOnClickListener(this);

            atualizaTela();
        }

        @Override
        public void onClick(View v) {
            if(v == adicionarActionButton){
                Intent novoContato = new Intent(this, AdicionarContatoActivity.class);
                startActivityForResult(novoContato, Constantes.NEW_CONTATO_REQUEST_CODE);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode){
                case Constantes.NEW_CONTATO_REQUEST_CODE:
                    if(resultCode == RESULT_OK){
                        contatoList = ContatoDAO.getInstante().all();
                        arrayAdapter.notifyDataSetChanged();
                    }else{
                        if(resultCode == RESULT_CANCELED){
                            Toast.makeText(this, "Nenhum contato adicionado.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
            }
            atualizaTela();
        }

        private void atualizaTela(){
            if(contatoList.size() == 0){
                semDadosTextView.setVisibility(View.VISIBLE);
                contatoListView.setVisibility(View.GONE);
            }else{
                semDadosTextView.setVisibility(View.GONE);
               contatoListView.setVisibility(View.VISIBLE);
            }
        }
    }
