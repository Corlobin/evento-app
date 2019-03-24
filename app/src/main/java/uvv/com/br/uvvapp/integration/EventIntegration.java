package uvv.com.br.uvvapp.integration;

import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uvv.com.br.uvvapp.MainActivity;
import uvv.com.br.uvvapp.handle.CustomExpandableListAdapter;
import uvv.com.br.uvvapp.model.Palestra;

public class EventIntegration implements Response.Listener<JSONObject> {

    private MainActivity mainActivity;

    public EventIntegration(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onResponse(JSONObject response) {
        if (response != null) {
            Gson gson = new Gson();
            try {
                JSONArray jsonArray = response.getJSONArray("palestras");
                Palestra[] palestras;
                if (jsonArray != null) {
                    palestras = gson.fromJson(jsonArray.toString(), Palestra[].class);
                    List<Palestra> palestraList = Arrays.asList(palestras);

                    // Convertendo em hashmap
                    final HashMap<String, List<Palestra>> hashMap = new HashMap<>();
                    // Inicializando as datas (poderia ter feito por stream, mas não é suportada na API 19)
                    final Set<String> datas = new HashSet<>();
                    for (Palestra palestra : palestraList) {
                        datas.add(palestra.getData());
                    }
                    // Obtendo os itens pelas datas
                    for (String data : datas) {
                        List<Palestra> palestras1 = new ArrayList<>();
                        for (Palestra palestra : palestraList) {
                            if (palestra.getData().equals(data)) {
                                palestras1.add(palestra);
                            }
                        }
                        hashMap.put(data, palestras1);
                    }

                    mainActivity.dados = new HashMap<>(hashMap);
                    mainActivity.datas = new ArrayList<>(datas);

                    CustomExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(mainActivity, mainActivity.datas, hashMap);
                    mainActivity.listView.setAdapter(expandableListAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}