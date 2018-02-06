package com.example.assem.picassogallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.url_field) EditText url;
    @BindView(R.id.button) com.beardedhen.androidbootstrap.BootstrapButton button;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.picture) com.beardedhen.androidbootstrap.BootstrapCircleThumbnail picture;

    private static final String[] all_images = {
            "https://upload.wikimedia.org/wikipedia/en/2/23/Pablo_Picasso%2C_1901-02%2C_Femme_au_caf%C3%A9_%28Absinthe_Drinker%29%2C_oil_on_canvas%2C_73_x_54_cm%2C_Hermitage_Museum%2C_Saint_Petersburg%2C_Russia.jpg",
            "https://www.vanartgallery.bc.ca/the_exhibitions/images/picasso.jpg",
            "https://www.wantaghschools.org/cms/lib05/NY01001016/Centricity/Domain/417/picasso-two-girls-reading.jpg",
            "http://www.artribune.com/wp-content/uploads/2012/11/Pablo-Picasso-Nature-morte-aux-tulipes.jpg"};

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setImage(position);
        url.setText(all_images[position]);
    }

    @OnClick(R.id.button)
    public void buttonClick () {
        YoYo.with(Techniques.Wobble).duration(500).playOn(button);
        setImage(position);
        url.setText(all_images[position]);
    }

    @OnItemSelected(R.id.spinner)
    public void spinnerSelected (Spinner spinner, int position) {
        this.position = position;
    }

    public void setImage (int pos) {
        Picasso.with(this).load(all_images[pos]).into(picture);
    }

}
