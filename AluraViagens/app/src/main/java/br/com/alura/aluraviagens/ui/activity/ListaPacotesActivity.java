package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDAO;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.ui.adapter.ListaPacotesAdapter;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);

        setTitle(TITULO_APPBAR);

        configuraLista();

    }

    private void configuraLista() {
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);

        final List<Pacote> pacotes = new PacoteDAO().lista();

        listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));

        listaDePacotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Pacote pacote = (Pacote) pacotes.get(posicao);
                vaiParaResumoPacotes(pacote);
            }
        });
    }

    private void vaiParaResumoPacotes(Pacote pacote) {
        Intent intent = new Intent(ListaPacotesActivity.this, ResumoPacoteActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }
}
