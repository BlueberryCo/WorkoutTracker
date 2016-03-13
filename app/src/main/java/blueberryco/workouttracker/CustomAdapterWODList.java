package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Greta on 13.3.2016 г..
 */
public class CustomAdapterWODList extends BaseAdapter {

    Context context;
    //List<WorkoutOfTheDay> lWODline;
    List<String> lWODline;
    private int[] colors = new int[] {  0x30808080, 0x30ffffff };

    CustomAdapterWODList(Context context, List<String> lWODs) {
        this.context = context;
        this.lWODline = lWODs;
        Log.i("CustomAdapterClients`", " --------1------");
    }

    @Override
    public int getCount() {
        return lWODline.size();
    }

    @Override
    public Object getItem(int position) {
        return lWODline.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lWODline.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        TextView tvExercise;
        TextView tvReps;
        TextView tvSets;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder item = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        item = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_wod, null);

            item.tvExercise = (TextView) convertView
                    .findViewById(R.id.laptop);
            item.tvReps = (TextView) convertView
                    .findViewById(R.id.laptop2);
            item.tvSets = (TextView) convertView.findViewById(R.id.laptop3);


            convertView.setTag(item);
        } else {
            item = (ViewHolder) convertView.getTag();
        }
        String wod = lWODline.get(position);
        // ako ima i snimka
        //item.ivProfilePic.setImageResource();
        item.tvExercise.setText(wod.toString() );
       //item.tvSets.setText(wod.getPhone());
        //item.tvReps.setText(wod.getEmail());

        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);

        return convertView;
    }

}
