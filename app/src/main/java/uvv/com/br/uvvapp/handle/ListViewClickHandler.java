package uvv.com.br.uvvapp.handle;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import uvv.com.br.uvvapp.DetailActivity;
import uvv.com.br.uvvapp.MainActivity;
import uvv.com.br.uvvapp.model.Palestra;

public class ListViewClickHandler implements ExpandableListView.OnChildClickListener {

    private MainActivity mainActivity;

    public ListViewClickHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {

        Palestra palestra = mainActivity.dados.get(
                mainActivity.datas.get(groupPosition)).get(
                childPosition);
        System.out.println(palestra);

        Intent intent = new Intent(mainActivity, DetailActivity.class);
        intent.putExtra("ListViewClickedValue", palestra);
        mainActivity.startActivity(intent);
        return false;
    }
}