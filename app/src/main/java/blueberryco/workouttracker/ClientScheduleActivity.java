package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import blueberryco.database.DatabaseHelper;
import blueberryco.entities.Client;

public class ClientScheduleActivity extends Activity {

    private static final String LOGTAG = "ClientScheduleActivity";
    public static final String CLIENT_KEY = "client";
    DateFormat df;
    DateFormat dfDataBase;
    Date selectedDate;


    ArrayList<Client> alClients;
    List<String> evDates;
    List<Client> lClients;

    ListView dataList;
    TextView emptyClientList;
    TextView tvDayWorkout;

    CalendarView cv;

    DatabaseHelper db;
    CustomAdapterClients adapter;

    RelativeLayout rlAdd;
    Button bAddWO;


    static Client cl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_schedule);
        assignUiElements();


        if (cl != null) {
            setCurrentClient(cl);
        }
        getWODays();
        assignClickHandlers();

    }

    public static Client getCurrentclient() {
        return cl;
    }

    public void setCurrentClient(Client client) {
        cl = client;
    }

    private void getWODays() {
        HashSet<Date> events = new HashSet<>();
        int ClientId;
        // klienti za tazi data
        if (cl != null) {
            ClientId = cl.getId();
            setCurrentClient(cl);
        } else
            ClientId = 0;

        Log.d(LOGTAG, " Dates from db ClientId " + ClientId);
        evDates = db.getWorkoutDays(ClientId);
        Log.d(LOGTAG, " Dates from db " + evDates);

        for (String stringDate : evDates) {
            Date eventDate = null;
            try {
                eventDate = df.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d(LOGTAG, " eventDate " + eventDate);
            events.add(eventDate);
        }

        cv.updateCalendar(events);
        Date today = new Date(System.currentTimeMillis());

        //fill textview for clients for selected day
        loadClientForDay(today);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day ---> za tozi den chetem ot bazata
                selectedDate = date;
                Toast.makeText(ClientScheduleActivity.this, df.format(date) + " eto tuka ima ama mnogo rabota :)", Toast.LENGTH_SHORT).show();
                loadClientForDay(selectedDate);
            }
        });

    }

    private void assignUiElements() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(CLIENT_KEY)) {
                if ((Client) extras.getSerializable(CLIENT_KEY) != null) {
                    cl = (Client) extras.getSerializable(CLIENT_KEY);
                    setCurrentClient(cl);
                    Log.d(LOGTAG, "cl: " + cl.getFirstName());
                } else {
                    cl = null;
                    setCurrentClient(cl);
                    Log.d(LOGTAG, "cl:nullllll");

                }
            }
        }


        db = new DatabaseHelper(getApplicationContext());
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        dfDataBase = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        dataList = (ListView) findViewById(R.id.lvClientsCal);
        emptyClientList = (TextView) findViewById(R.id.tvNoClientsForDay);
        tvDayWorkout = (TextView) findViewById(R.id.tvDayWorkout);
        cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.updateCalendar();
        rlAdd = (RelativeLayout) findViewById(R.id.rlForAddWO);
        bAddWO= (Button) findViewById(R.id.bAddWO);
        rlAdd.setVisibility(View.GONE);

    }
    private void assignClickHandlers() {
        // add one month and refresh UI
        bAddWO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddWorkout.class);
                startActivity(intent);

            }
        });}

    public void loadClientForDay(Date date) {

        Log.d(LOGTAG, "format yyyy-MM-dd " + date);
        String dat = df.format(date);
        int ClientId;
        String name = null;
        Log.d(LOGTAG, " " + dat);
        // klienti za tazi data
        if (cl != null) {
            ClientId = cl.getId();
            setCurrentClient(cl);
            name = cl.getFirstName();
            tvDayWorkout.setText("Тренировка за " + dfDataBase.format(date));
        } else {
            ClientId = 0;
            tvDayWorkout.setText("Клиенти за " + dfDataBase.format(date));
        }
        lClients = db.getAllClientsWorkoutForDate(dat, ClientId);
        if (lClients != null && lClients.size() != 0) {
            emptyClientList.setText("");
            alClients = new ArrayList<Client>();

            for (Client cn : lClients) {
                String log = "ID:" + cn.getId() + " Name: " + cn.getFirstName()
                        + " ,LastName: " + cn.getLastName();
                Log.d(LOGTAG, "Result: " + log);
                alClients.add(cn);

            }

            adapter = new CustomAdapterClients(ClientScheduleActivity.this,
                    alClients);
            dataList.setAdapter(adapter);
            //tuk logika za zarejdane na profil samo za edin klient
            dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position,
                                                                        long id) {

                                                    String member_name = alClients.get(position).getFirstName();
                                                    Toast.makeText(getApplicationContext(), "" + member_name,
                                                            Toast.LENGTH_SHORT).show();
                                                    Log.d(LOGTAG, "  alClients.get(position) " +  alClients.get(position).getId());
                                                    Intent intent = new Intent(getApplicationContext(), ClientScheduleActivity.class);
                                                    intent.putExtra(ClientScheduleActivity.CLIENT_KEY, alClients.get(position));
                                                    startActivity(intent);
                                                }
                                            }
            );

        } else {
            String log = "Няма клиенти";
            alClients = new ArrayList<Client>();
            rlAdd.setVisibility(View.GONE);
            if (name != null&&lClients.size()==0) {
                emptyClientList.setVisibility(View.GONE);
                rlAdd.setVisibility(View.VISIBLE);
                bAddWO.setText("Добави тренировка");

            } else {
                emptyClientList.setText(log);
            }

            adapter = new CustomAdapterClients(ClientScheduleActivity.this,
                    alClients);
            dataList.setAdapter(adapter);
            Log.d(LOGTAG, "nqma klienti za tozi den " + log);
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
