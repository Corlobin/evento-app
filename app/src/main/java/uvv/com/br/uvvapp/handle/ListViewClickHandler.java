package uvv.com.br.uvvapp.handle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import uvv.com.br.uvvapp.DetailActivity;
import uvv.com.br.uvvapp.MainActivity;
import uvv.com.br.uvvapp.model.Palestra;

import static android.support.v4.content.ContextCompat.startActivity;

public class ListViewClickHandler implements AdapterView.OnItemClickListener {

    private MainActivity mainActivity;

    public ListViewClickHandler (MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Palestra palestra = (Palestra) mainActivity.listView.getItemAtPosition(position);
        System.out.println(palestra);

        Intent intent = new Intent(mainActivity, DetailActivity.class);
        intent.putExtra("ListViewClickedValue", palestra);
        mainActivity.startActivity(intent);
    }
}