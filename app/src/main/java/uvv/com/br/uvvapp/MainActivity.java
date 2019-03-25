package uvv.com.br.uvvapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uvv.com.br.uvvapp.handle.ListViewClickHandler;
import uvv.com.br.uvvapp.integration.ErrorHandler;
import uvv.com.br.uvvapp.integration.EventIntegration;
import uvv.com.br.uvvapp.integration.GoogleIntegration;
import uvv.com.br.uvvapp.model.Palestra;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private String mJSONURLString = "https://raw.githubusercontent.com/Corlobin/evento-app/master/dados.json";
    private String mGoogleString = "https://script.google.com/macros/s/AKfycbxJJGECmBQfpFn6767mPhebOpG8Ke0ZEUDuc2O996R156IM72E/exec";

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
                            addItemSheet(errorHandler);
                        }})
                    .setNegativeButton("Não", null).show();
            }
        });
    }

    private void addItemSheet(ErrorHandler errorHandler) {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Marcando presença! :)", "Por favor, aguarde!");
        final String nome = "Ricardo Brasil";
        final String afiliacao = "IFES";
        GoogleIntegration googleIntegration = new GoogleIntegration(this, progressDialog);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mGoogleString, googleIntegration, errorHandler) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("action", "addItem");
                params.put("nome", nome);
                params.put("afiliacao", afiliacao);
                return params;
            }
        };
        int timeout = 5000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }




}
