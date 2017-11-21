package assignment3.fit4039.monash.mds.HomePage;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import assignment3.fit4039.monash.mds.Client;
import assignment3.fit4039.monash.mds.HashPassword;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    String gender;

    EditText firstNameText, surnameText, dobText, emailText, passwordText, conPasswordText;
    Button registerButton;


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameText = (EditText) findViewById(R.id.fnameEditText);
        surnameText = (EditText) findViewById(R.id.surnameEditText);
        dobText = (EditText) findViewById(R.id.dobEditText);
        emailText = (EditText) findViewById(R.id.EmailEditText);
        passwordText = (EditText) findViewById(R.id.passwordEditText);
        conPasswordText = (EditText) findViewById(R.id.re_enter_password);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);

        dobText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dobText.setText(dateFormat.format(myCalendar.getTime()));
                    }
                };
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
});
    }

    public void onSexRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleButton:
                if (checked)
                    gender = "Male";
                    break;
            case R.id.femaleButton:
                if (checked)
                    gender = "Female";
                    break;
        }
        if (checked == false){
            gender = "";
        }
    }

    protected void sendToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     *create user and store it into database
     */
    @Override
    public void onClick(View v) {
        if (firstNameText.getText().toString().isEmpty() || surnameText.getText().toString().isEmpty() ||
                dobText.getText().toString().isEmpty() || emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty() ||
                gender.isEmpty()) {
            sendToast("You Must Fill All Information Inside!");
        } else if (!firstNameText.getText().toString().matches("^[a-zA-Z]+$") || !surnameText.getText().toString().matches("^[a-zA-Z]+$")) {
            sendToast("You should only enter letters, please try again");
        } else if (!emailText.getText().toString().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            sendToast("Invalid email address enter, pleast try again");
        } else if (!conPasswordText.getText().toString().equals(passwordText.getText().toString())){
            sendToast("Password not confirmed, please try again");
        }
        else {
            try {
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... params) {
//                        int count = Integer.valueOf(RestClient.getNumOfClient());
                        Client client = new Client(2, params[0], params[1], params[2], params[3], params[4], params[5]);
                        RestClient.createClient(client);
                        return "Register Successfully";
                    }

                    @Override
                    protected void onPostExecute(String response) {
                        sendToast(response);
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("Email", emailText.getText().toString());
                        finish();
                    }
                }.execute(firstNameText.getText().toString(), surnameText.getText().toString(),
                        dobText.getText().toString(), gender, emailText.getText().toString(),
                        HashPassword.MD5(passwordText.getText().toString()));
            } catch (Exception e) {
                sendToast(e.toString());
            }
        }
    }
}
