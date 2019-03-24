package uvv.com.br.uvvapp.integration;

import android.widget.ArrayAdapter;

import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import uvv.com.br.uvvapp.MainActivity;
import uvv.com.br.uvvapp.model.Palestra;

public class EventIntegration implements Response.Listener<JSONObject> {

    private MainActivity mainActivity;

    public EventIntegration(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onResponse(JSONObject response){
        if (response != null) {
            Gson gson = new Gson();
            try {
                JSONArray jsonArray = response.getJSONArray("palestras");
                Palestra[] palestras;
                if (jsonArray != null) {
                    palestras = gson.fromJson(jsonArray.toString(), Palestra[].class);
                    List<Palestra> palestraList = Arrays.asList(palestras);
                    ArrayAdapter<Palestra> arrayAdapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_list_item_1, palestraList);
                    mainActivity.listView.setAdapter(arrayAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}