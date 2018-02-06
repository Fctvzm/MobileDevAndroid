package com.example.assem.json_fragments;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnListFragmentInteractionListener {

    SimpleFragment fragment;
    boolean port_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cat.cats = new ArrayList<Cat>();
        setContentView(R.layout.activity_main);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            fragment = new SimpleFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout, fragment).commit();
            port_mode = true;
        } else {
            fragment = (SimpleFragment)getSupportFragmentManager().findFragmentById(R.id.simpleFragment);
            port_mode = false;
        }
        new RetrieveCats().execute();
    }

    @Override
    public void onListFragmentInteraction(int position) {
        LandFragment landFrag = (LandFragment)
                getSupportFragmentManager().findFragmentById(R.id.landFragment);

        if (landFrag != null && !port_mode) {
            landFrag.updateContent(position);
        } else {
            LandFragment newFragment = new LandFragment();
            Bundle args = new Bundle();
            args.putInt(LandFragment.ARG_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentLayout, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    class RetrieveCats extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... urls) {
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
