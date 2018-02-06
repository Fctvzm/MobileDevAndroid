package com.example.assem.worldflagsgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Assem on 26.09.2017.
 */

public class CustomDialogClass extends Dialog implements View.OnClickListener{
    public Activity c;
    public Dialog d;
    public String country;
    public boolean check;

    public CustomDialogClass(Activity a, String country, boolean check) {
        super(a);
        this.c = a;
        this.country = country;
        this.check = check;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature();
        setContentView(R.layout.custom_layout);
        Button got_it = (Button)findViewById(R.id.btn_yes);
        got_it.setOnClickListener(this);

        TextView title = (TextView)findViewById(R.id.txt_title);
        title.setText(country);

        TextView text = (TextView)findViewById(R.id.txt_content);
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta leo nisl. " +
                "Praesent porta erat non rhoncus vestibulum. Quisque consectetur tellus in lacinia fringilla. " +
                "Phasellus libero tellus, consectetur a metus vitae, condimentum convallis leo. " +
                "Mauris mi risus, placerat lobortis velit at, gravida fermentum ipsum.";
        text.setText(content);

        LinearLayout lLayout = (LinearLayout)findViewById(R.id.layout);
        if (!check) lLayout.setBackgroundColor(Color.RED);
    }

    @Override
    public void onClick(View view) {
         dismiss();
    }

}
