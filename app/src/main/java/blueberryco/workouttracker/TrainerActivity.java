package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import blueberryco.entities.Client;

public class TrainerActivity extends Activity {
    ImageButton ibAdd;
    ImageButton ibProgram;
    ImageButton ibInfo;
    ImageButton ibWOD;
    ImageButton ibClients;
    ImageButton ibCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        ibAdd = (ImageButton) findViewById(R.id.ibAdd);
        ibProgram = (ImageButton) findViewById(R.id.ibProgram);
        ibInfo = (ImageButton) findViewById(R.id.ibInfo);
        ibWOD = (ImageButton) findViewById(R.id.ibWOD);
        ibClients = (ImageButton) findViewById(R.id.ibClients);
        ibCalendar = (ImageButton) findViewById(R.id.ibCalendar);

        ibInfo.setOnClickListener(oclBtn);
        ibAdd.setOnClickListener(oclBtn);
        ibWOD.setOnClickListener(oclBtn);
        ibProgram.setOnClickListener(oclBtn);
        ibClients.setOnClickListener(oclBtn);
        ibCalendar.setOnClickListener(oclBtn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // create a listener
    View.OnClickListener oclBtn;

    {
        oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ibAdd:
                        Toast.makeText(TrainerActivity.this, "Button CreateOwnerActivity ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CreateOwnerActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.ibClients:
                        Toast.makeText(TrainerActivity.this, "Button ClientsActivity", Toast.LENGTH_SHORT).show();
                        Intent intentibClients = new Intent(getApplicationContext(), ClientsActivity.class);
                        startActivity(intentibClients);
                        break;
                    case R.id.ibCalendar:

                   Client cl =null;
                        Toast.makeText(TrainerActivity.this, "Button ClientScheduleActivity ", Toast.LENGTH_SHORT).show();
                        Intent intentibCalendar = new Intent(getApplicationContext(), ClientScheduleActivity.class);
                        intentibCalendar.putExtra(ClientScheduleActivity.CLIENT_KEY, cl);
                        startActivity(intentibCalendar);
                        break;
                    case R.id.ibWOD:
                        Toast.makeText(TrainerActivity.this, "Button WorkoutDetailsActivity wod of trainer ", Toast.LENGTH_SHORT).show();
                        Intent intentibWOD = new Intent(getApplicationContext(), WorkoutDetailsActivity.class);
                        startActivity(intentibWOD);
                        break;
                    case R.id.ibProgram:
                        Toast.makeText(TrainerActivity.this, "Button BasicExercisesAcivity", Toast.LENGTH_SHORT).show();
                        Intent intentibProgram = new Intent(getApplicationContext(), BasicExercisesAcivity.class);
                        startActivity(intentibProgram);
                        break;
                    case R.id.ibInfo:
                        Toast.makeText(TrainerActivity.this, "Button InfoActivity ", Toast.LENGTH_SHORT).show();
                        Intent intentibInfo = new Intent(getApplicationContext(), InfoActivity.class);
                        startActivity(intentibInfo);
                        break;

                }
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }
}
