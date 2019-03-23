package uvv.com.br.uvvapp.integration;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ErrorHandler implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error);
    }
}
