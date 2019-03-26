package uvv.com.br.uvvapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;

import uvv.com.br.uvvapp.handle.ListViewClickHandler;
import uvv.com.br.uvvapp.integration.ErrorHandler;
import uvv.com.br.uvvapp.integration.EventIntegration;
import uvv.com.br.uvvapp.model.Palestra;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private String mJSONURLString = "https://raw.githubusercontent.com/Corlobin/evento-app/master/dados.json";

    public ExpandableListView listView;
    public FloatingActionButton floatingActionButton;

    public List<String> datas;
    public HashMap<String, List<Palestra>> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        listView = findViewById(R.id.expandableListView);

        // Acrescentando ações
        ListViewClickHandler listViewClickHandler = new ListViewClickHandler(this);
        listView.setOnChildClickListener(listViewClickHandler);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        EventIntegration eventIntegration = new EventIntegration(this);
        final ErrorHandler errorHandler = new ErrorHandler();
        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                eventIntegration,
                errorHandler
        );

        requestQueue.add(jsonObjectRequest);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
            }
        }

        // Ações para o botão
        floatingActionButton = findViewById(R.id.confirmar_inscricao);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmação de presença no evento")
                        .setMessage("Você deseja marcar a sua presença no evento?")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent = new Intent(MainActivity.this, QRCodeActivity.class);
                                MainActivity.this.startActivity(intent);
                                //addItemSheet(errorHandler);
                            }
                        })
                        .setNegativeButton("Não", null).show();
            }
        });
    }
}
