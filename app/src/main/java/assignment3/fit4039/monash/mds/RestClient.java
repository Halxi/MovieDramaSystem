package assignment3.fit4039.monash.mds;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jeric on 15/05/2017.
 */

public class RestClient {

    private static final String BASE_URI =
            "http://192.168.75.1:62276/MDS_Database/webresources/";

    public static String findAllMovie(){
        final String methodPath = "restclient.movie";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findAllCinema(){
        final String methodPath = "restclient.cinema";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findSession(String cinemaName, String movieName, String sessionDate){
        final String methodPath = "restclient.session/findByCinemaAndMovieAndDate/" + cinemaName + "/" + movieName + "/" + sessionDate;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findAllSession(){
        final String methodPath = "restclient.session";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findByEmailAndPassword(String email, String password) {
        final String methodPath = "restclient.client/findByEmailAndPassword/"  + email + "/" + password;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static void createClient(Client client){
        final String methodPath = "restclient.client/";
        URL url = null;
        HttpURLConnection conn = null;
        try {
            Gson gson =new Gson();
            String stringStudentJson=gson.toJson(client);
            System.out.println(stringStudentJson);
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
//set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringStudentJson.getBytes().length);
//add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
//Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringStudentJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public static String getNumOfClient(){
        final String methodPath = "restclient.client/count";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findByEmail(String email){
        final String methodPath = "restclient.client/findByEmail/" + email;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
}
