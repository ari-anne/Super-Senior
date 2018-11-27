package com.cpsc.supersenior.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import org.json.JSONArray;

public class DataBaseApi {
    String URL = "http://34.207.89.47/";
    public JSONArray get_HighScore() {
        JSONArray myResponse = new JSONArray();
        try {
            URL obj = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Super-Senior/1.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + URL);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            myResponse = new JSONArray(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return myResponse;
    }
    public void set_UserScore(String username, int score) {
        JSONArray myResponse = new JSONArray();
        try {
            URL += "add.php";
            URL obj = new URL(URL);
            String parameters = "username="+username+"&score="+ Integer.toString(score);
            byte[] postData       = parameters.getBytes(StandardCharsets.UTF_8);
            int    postDataLength = postData.length;
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            //add request header
            con.setRequestProperty("User-Agent", "Super-Senior/1.0");
            DataOutputStream wr = new DataOutputStream( con.getOutputStream());
            try{
                wr.write( postData );
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + URL);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}