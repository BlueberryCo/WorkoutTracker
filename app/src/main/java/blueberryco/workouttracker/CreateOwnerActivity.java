package blueberryco.workouttracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import blueberryco.entities.Util;

public class CreateOwnerActivity extends Activity {

    private DatePickerDialog birthDateDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_owner);
        this.bindElements();
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

    private void bindElements(){
        CheckBox cbxMoreInfo = (CheckBox)findViewById(R.id.cbxMoreInfo);
        cbxMoreInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                LinearLayout pnlMoreInfo = (LinearLayout) findViewById(R.id.pnlMoreInfo);
                if (isChecked) {
                    pnlMoreInfo.setVisibility(LinearLayout.VISIBLE);
                } else {
                    pnlMoreInfo.setVisibility(LinearLayout.GONE);
                }
            }
        });

        Button btnCreateOwner = (Button)findViewById(R.id.btnCreateOwner);
        btnCreateOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOwner();
            }
        });

        this.setDateTimeField();
    }

    private void createOwner(){

    }

    private void setDateTimeField(){

        Calendar calendar = Calendar.getInstance();

        dateFormatter = new SimpleDateFormat(Util.DATE_FORMAT , Locale.US);

        final EditText etBirthDate = (EditText)findViewById(R.id.etBirthDate);etBirthDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                birthDateDialog.show();
                return true;
            }
        });

        birthDateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etBirthDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
}
