package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import blueberryco.entities.Client;

/**
 * Created by Greta on 20.2.2016 г..
 */
public class CustomAdapterClients extends BaseAdapter {

    Context context;
    List<Client> lClients;
    private int[] colors = new int[] {  0x30808080, 0x30ffffff };

    CustomAdapterClients(Context context, List<Client> lClients) {
        this.context = context;
        this.lClients = lClients;
        Log.i("CustomAdapterClients`", " --------1------");
    }

    @Override
    public int getCount() {
        return lClients.size();
    }

    @Override
    public Object getItem(int position) {
        return lClients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lClients.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView ivClientImage;
        TextView tvClientName;
        TextView tvClientTelNumber;
        TextView tvClientEmail;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder item = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        item = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_clients, null);

            item.tvClientName = (TextView) convertView
                    .findViewById(R.id.tvClientNameItem);
            item.ivClientImage = (ImageView) convertView
                    .findViewById(R.id.ivProfilePic);
            item.tvClientTelNumber = (TextView) convertView.findViewById(R.id.tvClientPhone);
            item.tvClientEmail = (TextView) convertView
                    .findViewById(R.id.tvEmailClient);

            convertView.setTag(item);
        } else {
            item = (ViewHolder) convertView.getTag();
        }
        Client client = lClients.get(position);
        // ako ima i snimka
        //item.ivProfilePic.setImageResource();
        item.tvClientName.setText(client.getFirstName() + " " + client.getLastName());
        item.tvClientTelNumber.setText(client.getPhone());
        item.tvClientEmail.setText(client.getEmail());

        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);

        return convertView;
    }

}
