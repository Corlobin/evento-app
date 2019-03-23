package uvv.com.br.uvvapp.integration;

import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uvv.com.br.uvvapp.model.Palestra;

public class EventIntegration implements Response.Listener<JSONObject> {

    @Override
    public void onResponse(JSONObject response){
        if (response != null) {
            Gson gson = new Gson();
            try {
                JSONArray jsonArray = response.getJSONArray("palestras");
                if (jsonArray != null) {
                    Palestra[] palestras = gson.fromJson(jsonArray.toString(), Palestra[].class);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
