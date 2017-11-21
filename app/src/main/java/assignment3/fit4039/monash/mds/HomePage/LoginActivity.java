package assignment3.fit4039.monash.mds.HomePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import assignment3.fit4039.monash.mds.HashPassword;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button signinButton;
    Button registerButton;
    ImageView imageView;
    TextView skipLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        signinButton = (Button) findViewById(R.id.sign_in_button);
        registerButton = (Button) findViewById(R.id.register_button);
        imageView = (ImageView) findViewById(R.id.imageView);
        skipLoginText = (TextView) findViewById(R.id.skip_login);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        skipLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPrefs = getSharedPreferences("Client", Context.MODE_PRIVATE);
                String email = mPrefs.getString("Email", "");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Email", email);
                startActivity(intent);
                finish();
            }
        });
    }

    private void attemptLogin() {
        if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()
                || !emailEditText.getText().toString().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            sendToast("Wrong email address or password, please try again");
        } else {
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {
                    return RestClient.findByEmailAndPassword(params[0], params[1]);
                }

                @Override
                protected void onPostExecute(String response) {
                    if (response.equals("[]")) {
                        sendToast("Wrong email address or password, please try again");
                    } else {
                        SharedPreferences prefs = getSharedPreferences("Client", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Email", emailEditText.getText().toString());
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("Email", emailEditText.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                }

            }.execute(emailEditText.getText().toString(), HashPassword.MD5(passwordEditText.getText().toString()));
        }
    }

    protected void sendToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


}
