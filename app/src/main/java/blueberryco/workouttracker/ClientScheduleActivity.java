package blueberryco.workouttracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import blueberryco.database.DatabaseHelper;
import blueberryco.entities.Client;

public class ClientScheduleActivity extends Activity {

    private SimpleDateFormat dateFormatter;
    ListView dataList;
    ArrayList<Client> alClients;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    Date selectedDate;
    TextView emptyClientList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_schedule);
        dataList = (ListView) findViewById(R.id.lvClientsCal);
        emptyClientList = (TextView) findViewById(R.id.tvNoClientsForDay);
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        CalendarView cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.updateCalendar(events);
        Date today = new Date(System.currentTimeMillis());
        loadClientForDay(today);
        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day ---> tuk chete ot bazata za izbranq den
                selectedDate = date;
                Toast.makeText(ClientScheduleActivity.this, df.format(date) + " eto tuka ima ama mnogo rabota :)", Toast.LENGTH_SHORT).show();

                loadClientForDay(selectedDate);
            }
        });

    }


    public void loadClientForDay(Date date) {

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        CustomAdapterClients adapter;
        Log.d("to format date-> ", date + "");
        String dat = df.format(date);
        Log.d("date-> ", dat + "");
        List<Client> lClients = db.getAllClientsWorkoutForDate(dat);
        if (lClients != null && lClients.size() != 0) {
            Log.d("lClients  ", lClients.get(0).getFirstName());
            emptyClientList.setText("");
            alClients = new ArrayList<Client>();

            for (Client cn : lClients) {
                String log = "ID:" + cn.getId() + " Name: " + cn.getFirstName()
                        + " ,LastName: " + cn.getLastName();


                Log.d("Result: ", log);
                alClients.add(cn);

            }

            adapter = new CustomAdapterClients(ClientScheduleActivity.this,
                    alClients);

            dataList.setAdapter(adapter);


            dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                                        long id) {

                                                    String member_name = alClients.get(position).getFirstName();
                                                    Toast.makeText(getApplicationContext(), "" + member_name,
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }

            );

        } else {
            String log = "Няма клиенти за " + dat;
            emptyClientList.setText(log);
            alClients = new ArrayList<Client>();
            adapter = new CustomAdapterClients(ClientScheduleActivity.this,
                    alClients);

            dataList.setAdapter(adapter);
            Log.d("Result: ", log);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
