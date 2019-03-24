package uvv.com.br.uvvapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uvv.com.br.uvvapp.model.Palestra;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Palestra palestra = (Palestra) getIntent().getSerializableExtra("ListViewClickedValue");
        System.out.println("Passed: " + palestra);
    }
}
