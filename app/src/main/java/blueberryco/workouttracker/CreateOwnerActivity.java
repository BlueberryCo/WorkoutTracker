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

import blueberryco.entities.Client;
import blueberryco.entities.Util;

public class CreateOwnerActivity extends Activity {

    private DatePickerDialog birthDateDialog;
    private CheckBox cbxTrainer;
    private EditText etFirstName;
    private EditText etLastName;
    private CheckBox cbxMoreInfo;
    private LinearLayout pnlMoreInfo;
    private EditText etBirthDate;
    private EditText etHeight;
    private EditText etWeight;
    private EditText etPhone;
    private EditText etEmail;
    private Button btnCreateOwner;
    private SimpleDateFormat dateFormatter;

    private Client client = null;

    private boolean isCreateOwner = false;

    public static final String EXISTING_CLIENT_KEY = "client";
    public static final String IS_CREATE_OWNER_KEY = "is_create_owner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_owner);
        this.findFields();
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

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.containsKey(EXISTING_CLIENT_KEY)) {
                client = (Client)extras.getSerializable(EXISTING_CLIENT_KEY);
            }

            if(extras.containsKey(IS_CREATE_OWNER_KEY)){
                isCreateOwner = extras.getBoolean(IS_CREATE_OWNER_KEY);
            }
        }

        this.setExistingClient();

        cbxMoreInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    pnlMoreInfo.setVisibility(LinearLayout.VISIBLE);
                } else {
                    clearFields();
                    pnlMoreInfo.setVisibility(LinearLayout.GONE);
                }
            }
        });

        btnCreateOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrUpdateClient();
            }
        });

        this.setDateTimeField();
    }

    private void createOrUpdateClient(){

        boolean hasErrors = false;
        if(Util.isNullOrEmptyString(etFirstName.getText().toString())){
            etFirstName.setError("задължително");
            hasErrors = true;
        }

        if(Util.isNullOrEmptyString(etLastName.getText().toString())){
            etLastName.setError("задължително");
            hasErrors = true;
        }

        if(hasErrors){
            return;
        }

        if(hasClient()){

        }else {
            client = new Client();
        }
    }

    private void setDateTimeField(){

        Calendar calendar = Calendar.getInstance();

        dateFormatter = new SimpleDateFormat(Util.DATE_FORMAT , Locale.US);

        etBirthDate.setOnTouchListener(new View.OnTouchListener() {
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

    private void findFields(){
        cbxTrainer = (CheckBox)findViewById(R.id.cbxTrainer);
        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        cbxMoreInfo = (CheckBox)findViewById(R.id.cbxMoreInfo);
        pnlMoreInfo = (LinearLayout)findViewById(R.id.pnlMoreInfo);
        etBirthDate = (EditText)findViewById(R.id.etBirthDate);
        etHeight = (EditText)findViewById(R.id.etHeight);
        etWeight = (EditText)findViewById(R.id.etWeight);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnCreateOwner = (Button)findViewById(R.id.btnCreateOwner);
    }

    private void clearFields(){
        etBirthDate.getText().clear();
        etHeight.getText().clear();
        etWeight.getText().clear();
        etPhone.getText().clear();
        etEmail.getText().clear();
    }

    private void setExistingClient(){
        if(hasClient()){
            cbxTrainer.setVisibility(CheckBox.GONE);
            cbxMoreInfo.setVisibility(CheckBox.GONE);
            pnlMoreInfo.setVisibility(LinearLayout.VISIBLE);

            etFirstName.setText(client.getFirstName());
            etLastName.setText(client.getLastName());

            if(client.getBirthDate() != null){
                etBirthDate.setText(client.birthDateAsString());
            }

            if(client.getHeight() != null){
                etHeight.setText(Util.floatToString(client.getHeight()));
            }

            if(client.getWeight() != null){
                etWeight.setText(Util.floatToString(client.getWeight()));
            }

            if(!Util.isNullOrEmptyString(client.getPhone())){
                etPhone.setText(client.getPhone());
            }

            if(!Util.isNullOrEmptyString(client.getEmail())){
                etEmail.setText(client.getEmail());
            }
        }
    }

    private boolean hasClient(){
        return client != null && client.getId() != null;
    }
}
