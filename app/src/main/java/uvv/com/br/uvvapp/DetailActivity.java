package uvv.com.br.uvvapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import uvv.com.br.uvvapp.model.Palestra;
import uvv.com.br.uvvapp.model.Professor;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Palestra palestra = (Palestra) getIntent().getSerializableExtra("ListViewClickedValue");
        System.out.println("Passed: " + palestra);

        TextView data = findViewById(R.id.dataPalestra);
        TextView detalhe = findViewById(R.id.detalhe);
        TextView horario = findViewById(R.id.horario);

        data.setText(palestra.getData());
        detalhe.setText(palestra.getInformacao());
        horario.setText(palestra.getHora());

        TextView nome = findViewById(R.id.nome);
        TextView afiliacao = findViewById(R.id.afiliacao);
        TextView tema = findViewById(R.id.tema);
        if( palestra.getProfessores() != null && palestra.getProfessores().size() > 0){

            Professor professor = palestra.getProfessores().get(0);
            nome.setText(professor.getNome());
            afiliacao.setText(professor.getTitulacao());
            tema.setText(professor.getTema());
        } else {
            nome.setVisibility(View.INVISIBLE);
            afiliacao.setVisibility(View.INVISIBLE);
            tema.setVisibility(View.INVISIBLE);
            TextView dadosApresentador = findViewById(R.id.editText7);
            dadosApresentador.setVisibility(View.INVISIBLE);
            TextView editText8 = findViewById(R.id.editText8);
            TextView editText9 = findViewById(R.id.editText9);
            TextView temaPalestrante= findViewById(R.id.temaPalestrante);
            editText8.setVisibility(View.INVISIBLE);
            editText9.setVisibility(View.INVISIBLE);
            temaPalestrante.setVisibility(View.INVISIBLE);
        }
    }
}