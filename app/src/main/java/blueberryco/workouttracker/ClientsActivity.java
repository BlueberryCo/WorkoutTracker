package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import blueberryco.database.DatabaseHelper;
import blueberryco.entities.Client;

public class ClientsActivity extends Activity {
    private static final String LOG = "ClientsActivity";


    ListView dataList;

    ArrayList<Client> alClients;
    List<Client> lClients;

    DatabaseHelper db;
    CustomAdapterClients adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        db = new DatabaseHelper(getApplicationContext());

        assignUiElements();
        loadListClients();

    }

    private void assignUiElements() {
        dataList = (ListView) findViewById(R.id.lvClients);
    }

    private void loadListClients() {
        lClients = db.getAllClients();

        alClients = new ArrayList<Client>();

        for (Client cn : lClients) {
            String log = " ID:" + cn.getId() + " Name: " + cn.getFirstName()
                    + " ,LastName: " + cn.getLastName();
            Log.d(LOG, log);
            alClients.add(cn);

        }

        adapter = new CustomAdapterClients(this,
                alClients);
        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                                    long id) {
                                                String member_name = alClients.get(position).getFirstName();
                                                Toast.makeText(getApplicationContext(), "" + member_name,
                                                        Toast.LENGTH_SHORT).show();

                                                Client cl = alClients.get(position);
                                                Intent intent = new Intent(getApplicationContext(), ClientScheduleActivity.class);
                                                intent.putExtra(ClientScheduleActivity.CLIENT_KEY, cl);
                                                startActivity(intent);


                                            }
                                        }

        );
    }

}
