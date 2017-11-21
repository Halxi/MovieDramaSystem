package assignment3.fit4039.monash.mds;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jeric on 8/05/2017.
 */

public class GoogleCustomerSearch {

    static Bitmap bitmap;

    public static String GoogleSearh(String keyword) {
        String API_key = "AIzaSyAtkuaU6TIpV3eOlcEYJoy8pDNhtK7Xf_o";
        String SEARCH_ID_cx = "009146497320098619568:wrufwwgmz6q";
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection conn = null;
        String strResponse = "";
        String snippet = "";
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key=" + API_key
                    + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + "&alt=json");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                strResponse += inStream.nextLine();
            }
            JSONObject json = new JSONObject(strResponse);
            JSONArray jsonarray = json.getJSONArray("items");
            if (jsonarray != null && jsonarray.length() > 0) {
                snippet = jsonarray.getJSONObject(0).getString("snippet");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return snippet;
    }

    public static String ImageSearch(String keyword) {
        String API_key = "AIzaSyAtkuaU6TIpV3eOlcEYJoy8pDNhtK7Xf_o";
        String SEARCH_ID_cx = "009146497320098619568:wrufwwgmz6q";
        keyword = keyword.replace(" ", "+") + "poster";
        String strResponse = "";
        String image = "";
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key=" + API_key
                    + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + "&alt=json");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                strResponse += inStream.nextLine();
            }

            JSONObject json = new JSONObject(strResponse);
            image = json.getJSONArray("items").getString(0);
            json = new JSONObject(image);
            image = json.getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("src");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return image;
    }

    public static Bitmap downloadImage(String image){
        new AsyncTask<String, Void, Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(input);
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }.execute(image);
        return bitmap;
    }
}
