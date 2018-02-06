package com.example.nurzhaussyn.kbtuapplication.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nurzhaussyn.kbtuapplication.Adapter;
import com.example.nurzhaussyn.kbtuapplication.LoginFragment;
import com.example.nurzhaussyn.kbtuapplication.R;
import com.example.nurzhaussyn.kbtuapplication.Wall;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Assem on 04.10.2017.
 */

public class EventsFragment extends Fragment {

    private static final String LOG_TAG = "myLogs";


    private RecyclerView recView;
    private RecyclerView.Adapter mAdapter;
    private String [] namesOfGroups = {"Parasat", "Unit", "Faces", "Crystal", "Big City Light", "Art House"};
    public static ArrayList<Wall> walls;
    private String name;
    public static String POS = "pos";
    private String [] request_urls = {"https://api.vk.com/method/wall.get?owner_id=-112155093&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-44152963&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-59371684&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-36645235&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-69023204&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-92414177&count=10&filter=owner&v=5.52&access_token="};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.events, container, false);

        recView = (RecyclerView)v.findViewById(R.id.recycleView);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recView.setLayoutManager(mLayoutManager);
        walls = new ArrayList<>();

        mAdapter = new Adapter(walls, getContext());
        recView.setAdapter(mAdapter);

        ImageButton exit = (ImageButton)v.findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.logout();
                LoginFragment lg = new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, lg).commit();
            }
        });

        String access_token = VKAccessToken.currentToken().accessToken;
        sendingRequest(access_token);
        return v;
    }

    private class getEventsFromVk extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String jsonResponse = "";
            for (int i = 0; i < strings.length; i++) {
                name = namesOfGroups[i];
                try {
                    jsonResponse = makingHttpRequest(createUrl(strings[i]));
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error with inputstream.close", e);
                }

                try {
                    parseJson(jsonResponse);
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error with jsonParsing", e);
                } catch (ParseException e) {
                    Log.e(LOG_TAG, "Error with dateConversion", e);
                }

            }
            Collections.sort(walls);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            mAdapter.notifyDataSetChanged();
            //Toast.makeText(getApplicationContext(), walls.size()+"", Toast.LENGTH_LONG).show();
        }
    }

    private URL createUrl (String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating url", e);
            return null;
        }
        return url;
    }

    private String makingHttpRequest (URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with url connection", e);
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private void parseJson (String wallJSON) throws JSONException, ParseException {
        if (TextUtils.isEmpty(wallJSON)) {
            return;
        }
        JSONObject baseJsonResponse = new JSONObject(wallJSON);
        JSONObject response = (JSONObject) baseJsonResponse.get("response");
        JSONArray postsArray = (JSONArray) response.get("items");
        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject post = (JSONObject) postsArray.get(i);
            String text = post.getString("text");
            Date date = Wall.convertingDate(post.getString("date"));
            String imageURL = "";
            if (!post.isNull("attachments")) {
                JSONArray images = (JSONArray) post.getJSONArray("attachments");
                for (int j = 0; j < images.length(); j++) {
                    JSONObject image = (JSONObject) images.get(j);
                    if (image.getString("type").equals("photo")) {
                        JSONObject photo = (JSONObject) image.get("photo");
                        imageURL = photo.getString("photo_604");
                        break;
                    }
                }
            }
            else {continue;}
            if (!text.equals("") && !imageURL.equals("")) {
                walls.add(new Wall(name, text, date, imageURL));
            }
        }
    }

    private void sendingRequest (String token) {
        Log.d(LOG_TAG, token);
        for (int i = 0; i < request_urls.length; i++) {
            request_urls[i] = request_urls[i] + token;
        }
        new getEventsFromVk().execute(request_urls);
    }
}
