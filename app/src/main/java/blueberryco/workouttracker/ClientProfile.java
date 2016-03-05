package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import blueberryco.entities.Client;

public class ClientProfile extends Activity {

    Client cl;
    public static final String CLIENT_KEY = "client";
    private static final String LOGTAG = "ClientProfile";
    Button bCalendar;
    Button bProfile;
    TextView tvClientName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);
        assignUiElements();
        setFormatings();
        assignClickHandlers();
    }

    private void assignUiElements() {
        tvClientName = (TextView) findViewById(R.id.tvClientNameProfile);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(CLIENT_KEY)) {
                if ((Client) extras.getSerializable(CLIENT_KEY) != null) {
                    cl = (Client) extras.getSerializable(CLIENT_KEY);
                    if (cl != null) {
                        Log.d(LOGTAG, "cl: " + cl.getFirstName());
                        tvClientName.setText(cl.getFirstName().toString() + " " + cl.getLastName().toString());
                    }
                }
            }
        }


        bCalendar = (Button) findViewById(R.id.bClientCalendar);
        bProfile = (Button) findViewById(R.id.bClientProfile);


    }


    private void assignClickHandlers() {

        bCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientScheduleActivity.class);
                intent.putExtra(ClientScheduleActivity.CLIENT_KEY, cl);
                startActivity(intent);
            }
        });
        bProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientProfile.class);
                intent.putExtra(ClientScheduleActivity.CLIENT_KEY, cl);
                startActivity(intent);
            }
        });


    }

    private void setFormatings() {
        bCalendar.setBackgroundColor(getResources().getColor(CalendarView.curColor));
        bProfile.setBackgroundColor(getResources().getColor(CalendarView.curColor));
        //tvClientName.setBackgroundColor(getResources().getColor(CalendarView.curColor));
    }
}
