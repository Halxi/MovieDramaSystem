package assignment3.fit4039.monash.mds.HomePage;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;

/**
 * Created by jeric on 7/05/2017.
 */

public class MineFragment extends Fragment {
    View vMine;
    TextView welcome;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        vMine = inflater.inflate(R.layout.fragment_mine, container, false);
        welcome = (TextView) vMine.findViewById(R.id.welcomeName);
        if (getActivity().getIntent().hasExtra("Email")){
            getName(getActivity().getIntent().getStringExtra("Email"));
        }
        else{
            SpannableString content = new SpannableString("Login");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            welcome.setText("Please " + content + " first");
            welcome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        return vMine;
    }


    public void getName(String email){
        new AsyncTask<String, Void, String>(){
            @Override
            protected String doInBackground(String... params) {
                return RestClient.findByEmail(params[0]);
            }

            @Override
            protected void onPostExecute(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String name = object.getString("fname");
                    welcome.setText("Hi, " + name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(email);
    }
}
