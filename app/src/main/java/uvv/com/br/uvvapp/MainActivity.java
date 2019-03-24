package uvv.com.br.uvvapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import uvv.com.br.uvvapp.integration.ErrorHandler;
import uvv.com.br.uvvapp.integration.EventIntegration;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private String mJSONURLString = "https://gist.githubusercontent.com/Corlobin/a859796fe711c82eb088cd29e27f0420/raw/dd73b50f012e7e639b721eae22c507114f97da42/dados.json";

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        listView = findViewById(R.id.listView);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        EventIntegration eventIntegration = new EventIntegration(this);
        ErrorHandler errorHandler = new ErrorHandler();
        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                eventIntegration,
                errorHandler
        );

        requestQueue.add(jsonObjectRequest);
    }

}
