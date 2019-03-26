package uvv.com.br.uvvapp.integration;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.Response;

import uvv.com.br.uvvapp.ConfirmarActivity;

public class GoogleIntegration implements Response.Listener<String> {
    private final ProgressDialog loading;
    private ConfirmarActivity mainActivity;

    public GoogleIntegration(ConfirmarActivity mainActivity, ProgressDialog loading) {
        this.mainActivity = mainActivity;
        this.loading = loading;
    }

    @Override
    public void onResponse(String response) {
        loading.dismiss();
        Toast.makeText(mainActivity, response, Toast.LENGTH_LONG).show();
        mainActivity.onBackPressed();
    }
}