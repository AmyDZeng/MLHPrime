package com.choosemuse.example.libmuse;

import android.os.AsyncTask;
import android.util.Log;

import com.choosemuse.example.libmuse.CallTask;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by frieda on 16-08-06.
 */
public class CallTask extends AsyncTask<URL, Integer, String> {
    protected String doInBackground(URL... urls) {
        HttpURLConnection conn = null;
        String result = "";
        try {
            URL url = new URL("http://10.20.3.174:8090/select");
//            URL url = urls[0];
            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            String body = "<ContentItem source=\"SPOTIFY\" type=\"uri\" location=\"spotify:track:0wlVropyv3VsqG5HepMfu2\"  sourceAccount=\"bosehack19\"></ContentItem>";
            OutputStream output = new BufferedOutputStream(conn.getOutputStream());
//            OutputStream output = new DataOutputStream(conn.getOutputStream ());
            output.write(body.getBytes());
            output.flush();
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder_result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                builder_result.append(line);
            }
            System.out.println(builder_result.toString());
            result = builder_result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch(ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        return result;
    }

    protected void onProgressUpdate(Integer... progress) {
//        setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
//        showDialog("Downloaded " + result + " bytes");
    }
}