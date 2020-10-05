package br.edu.mariaregina.agenda_dmos5.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.data.ContatoDAO;
import br.edu.mariaregina.agenda_dmos5.model.Contato;

public class AdicionarContatoActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText nomeEditText;
    private EditText sobrenomeEditText;
    private EditText telefonefixoEditText;
    private EditText telefonecelularEditText;
    private Button salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_contato);

        nomeEditText = findViewById(R.id.edittext_nome);
        sobrenomeEditText = findViewById(R.id.edittext_sobrenome);
        telefonefixoEditText = findViewById(R.id.edittext_telefonefixo);
        telefonecelularEditText = findViewById(R.id.edittext_telefonecelular);
        salvarButton = findViewById(R.id.button_salvar);

        salvarButton.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if(v == salvarButton){
            salvaContato();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvaContato(){
        String nome;
        String sobrenome = "";
        int telefonefixo;
        int telefonecelular;

        nome = nomeEditText.getText().toString();
    try{
           telefonefixo = Integer.valueOf(telefonefixoEditText.getText().toString());
        telefonecelular = Integer.valueOf(telefonecelularEditText.getText().toString());
           if(telefonefixo < 0 && telefonecelular < 0){
            telefonefixo *= -1;
            telefonecelular *= -1;
           }
        }catch (NumberFormatException ex){
            telefonefixo = -1;
            telefonecelular = -1;
        }

        if(nome.isEmpty() || sobrenome.isEmpty()){
            Toast.makeText(this, "Dados inválidos.", Toast.LENGTH_SHORT).show();
        }else{
            ContatoDAO.getInstante().add(new Contato(nome,sobrenome));
            Toast.makeText(this, "Dados cadastrados.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }
}
