package blueberryco.workouttracker;

import android.app.Activity;
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


    ListView dataList;
    ArrayList<Client> alClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        Log.d("Call get clients: ", " --------1------");
        CustomAdapterClients adapter;
        List<Client> lClients = db.getAllClients();
        Log.d("lClients  ", lClients.get(0).getFirstName());

        alClients = new ArrayList<Client>();

        for (Client cn : lClients) {
            String log = "ID:" + cn.getId() + " Name: " + cn.getFirstName()
                    + " ,LastName: " + cn.getLastName();


            Log.d("Result: ", log);
            alClients.add(cn);

        }

        adapter = new CustomAdapterClients(this,
                alClients);
        dataList = (ListView) findViewById(R.id.lvClients);
        dataList.setAdapter(adapter);


        dataList.setOnItemClickListener(new OnItemClickListener() {


                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                                    long id) {

                                                String member_name = alClients.get(position).getFirstName();
                                                Toast.makeText(getApplicationContext(), "" + member_name,
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }

        );

    }
}
