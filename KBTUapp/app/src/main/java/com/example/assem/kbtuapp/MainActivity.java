package com.example.assem.kbtuapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiPost;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLogs";

    private String[] scope = new String[]{VKScope.GROUPS};
    private RecyclerView recView;
    private RecyclerView.Adapter mAdapter;
    private String [] namesOfGroups = {"Parasat", "Unit", "Faces", "Crystal", "Big City Light", "Art House"};
    public static ArrayList<Wall> walls;
    private String name;
    private String access_token;
    public static String POS = "pos";
    private String [] request_urls = {"https://api.vk.com/method/wall.get?owner_id=-112155093&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-44152963&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-59371684&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-36645235&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-69023204&count=10&filter=owner&v=5.52&access_token=",
            "https://api.vk.com/method/wall.get?owner_id=-92414177&count=10&filter=owner&v=5.52&access_token="};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = (RecyclerView)findViewById(R.id.recycleView);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recView.setLayoutManager(mLayoutManager);

        walls = new ArrayList<Wall>();

        mAdapter = new Adapter(walls, this);
        recView.setAdapter(mAdapter);

        VKSdk.login(this, scope);

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

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                access_token = res.accessToken;
                Log.d(LOG_TAG, access_token);
                for (int i = 0; i < request_urls.length; i++) {
                    request_urls[i] = request_urls[i] + access_token;
                }
                new getEventsFromVk().execute(request_urls);
            }
            @Override
            public void onError(VKError error) {Log.e(LOG_TAG, "Error with auth " + error.toString());}}))

        {super.onActivityResult(requestCode, resultCode, data);}
    }


}
