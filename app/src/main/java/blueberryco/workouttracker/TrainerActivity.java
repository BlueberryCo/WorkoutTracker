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

public class TrainerActivity extends Activity {
    ImageButton ibAdd;
    ImageButton ibProgram;
    ImageButton ibSetting;
    ImageButton ibWOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        ibAdd = (ImageButton) findViewById(R.id.ibAdd);
        ibProgram = (ImageButton) findViewById(R.id.ibProgram);
        ibSetting = (ImageButton) findViewById(R.id.ibSettings);
        ibWOD = (ImageButton) findViewById(R.id.ibWOD);

        ibSetting.setOnClickListener(oclBtn);
        ibAdd.setOnClickListener(oclBtn);
        ibWOD.setOnClickListener(oclBtn);
        ibProgram.setOnClickListener(oclBtn);

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
                        Toast.makeText(TrainerActivity.this, "Button 1 pressed ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ClientProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.ibProgram:
                        Toast.makeText(TrainerActivity.this, "Button 2 pressed ", Toast.LENGTH_SHORT).show();
                        Intent intentibProgram = new Intent(getApplicationContext(), WorkoutDetailsActivity.class);
                        startActivity(intentibProgram);
                        break;
                    case R.id.ibSettings:
                        Toast.makeText(TrainerActivity.this, "Button 3 pressed ", Toast.LENGTH_SHORT).show();
                        Intent intentibSettings = new Intent(getApplicationContext(), ClientStatsActivity.class);
                        startActivity(intentibSettings);
                        break;
                    case R.id.ibWOD:
                        Toast.makeText(TrainerActivity.this, "Button 4 pressed ", Toast.LENGTH_SHORT).show();
                        Intent intentibWOD = new Intent(getApplicationContext(), WorkoutDetailsActivity.class);
                        startActivity(intentibWOD);
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
