package br.edu.mariaregina.agenda_dmos5.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.constants.Constantes;
import br.edu.mariaregina.agenda_dmos5.data.ContatoDAO;
import br.edu.mariaregina.agenda_dmos5.model.Contato;
import br.edu.mariaregina.agenda_dmos5.view.ItemContatoAdapter;

public class Novo_Contato extends AppCompatActivity implements View.OnClickListener {


    private ListView contatosListView;
    private TextView semContatosTextView;
    private List<Contato> contatoList;
    private ArrayAdapter<Novo_Contato> arrayAdapter;
    private FloatingActionButton adicionarActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo__contato);

        contatosListView = findViewById(R.id.listview_contato);
        semContatosTextView = findViewById(R.id.textview_sem_contatos);
        adicionarActionButton = findViewById(R.id.fab_add_contato);


        contatoList = ContatoDAO.getInstante().all();



        contatosListView.setAdapter(arrayAdapter);
        adicionarActionButton.setOnClickListener(this);

        atualizaTela();
    }

    @Override
    public void onClick(View v) {
        if(v == adicionarActionButton){
            Intent novoContato = new Intent(this, adicionar_Novo_Contato.class);
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
            semContatosTextView.setVisibility(View.VISIBLE);
            contatosListView.setVisibility(View.GONE);
        }else{
            semContatosTextView.setVisibility(View.GONE);
            contatosListView.setVisibility(View.VISIBLE);
        }
    }


}

''