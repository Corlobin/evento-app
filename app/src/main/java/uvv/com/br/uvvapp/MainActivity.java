package uvv.com.br.uvvapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import uvv.com.br.uvvapp.integration.ErrorHandler;
import uvv.com.br.uvvapp.integration.EventIntegration;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mCLayout;
    private Button mButtonDo;
    private String mJSONURLString = "https://gist.githubusercontent.com/Corlobin/a859796fe711c82eb088cd29e27f0420/raw/dd73b50f012e7e639b721eae22c507114f97da42/dados.json";

    private ListView listView;
    private MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mCLayout = (RelativeLayout) findViewById(R.id.coordinator_layout);
        mButtonDo = (Button) findViewById(R.id.btn_do);
        listView = (ListView) findViewById(R.id.listView);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Empty the TextView

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                EventIntegration eventIntegration = new EventIntegration();
                ErrorHandler errorHandler = new ErrorHandler();
                // Initialize a new JsonObjectRequest instance
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        mJSONURLString,
                        null,
                        eventIntegration,
                        errorHandler
                );

                // Add JsonObjectRequest to the RequestQueue
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
