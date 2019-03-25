package uvv.com.br.uvvapp.integration;

import android.app.ProgressDialog;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

import uvv.com.br.uvvapp.MainActivity;

public class GoogleIntegration implements Response.Listener<String> {
    private final ProgressDialog loading;
    private MainActivity mainActivity;

    public GoogleIntegration(MainActivity mainActivity, ProgressDialog loading) {
        this.mainActivity = mainActivity;
        this.loading = loading;
    }

    @Override
    public void onResponse(String response) {
        loading.dismiss();
        Toast.makeText(mainActivity, response, Toast.LENGTH_LONG).show();
    }
}