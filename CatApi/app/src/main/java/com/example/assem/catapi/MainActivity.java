package com.example.assem.catapi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity {

    SimpleFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cat.cats = new ArrayList<Cat>();
        setContentView(R.layout.activity_main);
        new RetrieveCats().execute("","","");
        fragment = (SimpleFragment)getSupportFragmentManager().findFragmentById(R.id.frame_1);
    }

    class RetrieveCats extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://thecatapi.com/api/images/get?format=xml&results_per_page=10");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("CAT_ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
            JSONObject jsonResponse = xmlToJson.toJson();
            Log.v("Response", jsonResponse.toString());
            try {
                assert jsonResponse != null;
                JSONObject resOb = jsonResponse.getJSONObject("response");
                JSONObject data = resOb.getJSONObject("data");
                JSONObject image = data.getJSONObject("images");
                JSONArray images = image.getJSONArray("image");
                for (int i = 0; i < images.length(); i++) {
                    JSONObject urlObject = (JSONObject) images.get(i);
                    String url = urlObject.getString("url");
                    String id = urlObject.getString("id");
                    String info = urlObject.getString("source_url");
                    Cat.cats.add(new Cat(id, info, url));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            fragment.getAdapter().notifyDataSetChanged();
        }
    }
}
