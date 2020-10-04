package br.edu.mariaregina.agenda_dmos5.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.constants.Constantes;
import br.edu.mariaregina.agenda_dmos5.data.ContatoDAO;
import br.edu.mariaregina.agenda_dmos5.model.Contato;



    public class MainActivity extends AppCompatActivity {

        private static final int REQUESTCODE_NOVO_CONTATO = 75;

        private RecyclerView mRecyclerView;
        private ImageView mImageView;

        private List<Contato> mContatoList;
        private ContatoDAO contatoDAO;
        private ItemContatoAdapter mItemContatoAdapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            mRecyclerView = findViewById(R.id.recycler_lista_contato);
            mImageView = findViewById(R.id.imageview_lista_vazia);


            contatoDAO = new ContatoDAO(this);
            mContatoList = ContatoDAO.buscaTodos();


            mItemContatoAdapter = new ItemContatoAdapter(mContatoList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mItemContatoAdapter);


        }

        @Override
        protected void onResume() {
            super.onResume();
            updateUserInterface();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_contato,menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_adicionar:
                    Intent intent = new Intent(this, NovoContatoActivity.class);
                    startActivityForResult(intent, REQUESTCODE_NOVO_CONTATO);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);


            if (requestCode == REQUESTCODE_NOVO_CONTATO){
                if(resultCode == Activity.RESULT_OK){
                    String nome = data.getStringExtra(Constantes.ATTR_NOME);
                    String sobrenome = data.getStringExtra(Constantes.ATTR_SOBRENOME);
                   //int telefonefixo = data.getIntExtra(Constantes.ATTR_TELEFONEFIXO);
                    //int telefonecelular = data.getIntExtra(Constantes.ATTR_TELEFONECELULAR);
                   Contato contato = new Contato(nome,sobrenome);//, sobrenome, telefonefixo, telefonecelular);


                    contatoDAO.adiciona(contato);

                    mContatoList.add(contato);
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        }

        private void updateUserInterface() {
            if (mContatoList.size() == 0) {
                mImageView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            } else {
                mImageView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }


        }
    }