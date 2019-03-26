package uvv.com.br.uvvapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import uvv.com.br.uvvapp.handle.BarcodeProcessor;
import uvv.com.br.uvvapp.integration.ErrorHandler;
import uvv.com.br.uvvapp.integration.GoogleIntegration;

public class ConfirmarActivity extends AppCompatActivity {

    private Button confirmar;
    private Button cancelar;
    private TextView etNome;
    private TextView etAfiliacao;
    private String mGoogleString = "https://script.google.com/macros/s/AKfycbxJJGECmBQfpFn6767mPhebOpG8Ke0ZEUDuc2O996R156IM72E/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        confirmar = findViewById(R.id.btConfirmar);
        cancelar = findViewById(R.id.btCancelar);
        etNome = findViewById(R.id.etNome);
        etAfiliacao = findViewById(R.id.afiliacao);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence nome = etNome.getText();
                CharSequence afiliacao = etAfiliacao.getText();
                if ((nome == null || nome != null && nome.toString().isEmpty())) {
                    Toast.makeText(ConfirmarActivity.this, "O nome é obrigatório.", Toast.LENGTH_LONG).show();
                } else if (afiliacao == null || afiliacao != null && afiliacao.toString().isEmpty()) {
                    Toast.makeText(ConfirmarActivity.this, "A afiliação é obrigatório.", Toast.LENGTH_LONG).show();
                } else {
                    addItemSheet(new ErrorHandler());
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmarActivity.super.onBackPressed();
                BarcodeProcessor.calledOne = false;
            }
        });
    }


    private void addItemSheet(ErrorHandler errorHandler) {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Marcando presença! :)", "Por favor, aguarde!");
        GoogleIntegration googleIntegration = new GoogleIntegration(this, progressDialog);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mGoogleString, googleIntegration, errorHandler) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("action", "addItem");
                params.put("nome", etNome.getText().toString());
                params.put("afiliacao", etAfiliacao.getText().toString());
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
