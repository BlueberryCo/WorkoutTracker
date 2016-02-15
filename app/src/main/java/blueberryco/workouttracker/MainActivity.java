package blueberryco.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import blueberryco.database.DatabaseHelper;
import blueberryco.entities.Client;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checkHasOwner(); //If owner is already created redirect to TrainerActivity
        bindElements();
    }

    public void bindElements(){
        Button bLoginTest = (Button) findViewById(R.id.button);
        bLoginTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ClientProfileActivity.class);
                startActivity(intent);

            }
        });

        Button btnNewUser = (Button) findViewById(R.id.btnNewUser);
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateOwnerActivity.class);
                startActivity(intent);
            }
        });

        Button btnSync = (Button) findViewById(R.id.btnSync);
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCreateClient();
            }
        });
    }

    private void checkHasOwner(){
        if(Client.hasOwner(this.getApplicationContext())){

            Intent intent = new Intent(getApplicationContext(), TrainerActivity.class);
            startActivity(intent);

            this.finish();
        }
    }

    private void testCreateClient(){
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        Client client = new Client();
        client.setFirstName("Георги");
        client.setLastName("Дончев");

        db.createClient(client);

        Log.i("INSERT USER", client.getId().toString());
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