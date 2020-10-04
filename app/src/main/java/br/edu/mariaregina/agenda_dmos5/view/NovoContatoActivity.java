package br.edu.mariaregina.agenda_dmos5.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.constants.Constantes;

public class NovoContatoActivity extends AppCompatActivity implements View.OnClickListener {


        private EditText nomeEditText;
        private EditText sobrenomeEditText;
        private EditText telefonefixoEditText;
        private EditText telefonecelularEditText;
        private Button salvarButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_novo__contato);

            //Habilita o botão voltar na activity.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            nomeEditText = findViewById(R.id.edittext_nome);
            sobrenomeEditText = findViewById(R.id.edittext_sobrenome);
            telefonefixoEditText = findViewById(R.id.edittext_telefonefixo);
            telefonecelularEditText = findViewById(R.id.edittext_telefonecelular);
            salvarButton = findViewById(R.id.button_salvar);

            salvarButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view == salvarButton){
                //Ao clicar no botão verifica-se se os dois campos possuem dados, caso afirmativo é
                // é realizado o processamento.
                if(!nomeEditText.getText().toString().isEmpty() && !sobrenomeEditText.getText().toString().isEmpty()
                && !telefonefixoEditText.getText().toString().isEmpty() && !telefonecelularEditText.getText().toString().isEmpty()){

                    // Salvar um site significa coletar os dados e devolver os valores para a SiteActivity.java,
                    // que realizou a chamada da activity.
                    //
                    // Iremos criar uma nova Intent denominada result e vamos inserir os valores preenchidos
                    // pelo usuário no Bundle (putExtra) dessa Intent, depois disso é definido qual o resultado
                    // de NovoSiteActivity.java pelo método serResult().
                    Intent result = new Intent();
                    result.putExtra(Constantes.ATTR_NOME, nomeEditText.getText().toString());
                  //  result.putExtra(Constantes.ATTR_SOBRENOME, sobrenomeEditText.getText().toString());
                 //   result.putExtra(Constantes.ATTR_TELEFONEFIXO, telefonefixoEditText.getText().toString());
                 //   result.putExtra(Constantes.ATTR_TELEFONECELULAR, telefonecelularEditText.getText().toString());
                    setResult(Activity.RESULT_OK, result);

                    Toast.makeText(this, R.string.actnovo_msg_save_sucess, Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, getString(R.string.actnovo_msg_erro_data_missing), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == android.R.id.home){
            /*
            Essa activity é chamada com a espera de um resultado, caso o usuário não cadastre um
            novo site o resultado será RESULT_CANCELED, ou seja, a activity não produziu nenhum
            resultado para ser devolvido para a activity que a chamou.
             */
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
            return super.onOptionsItemSelected(item);
        }
    }

