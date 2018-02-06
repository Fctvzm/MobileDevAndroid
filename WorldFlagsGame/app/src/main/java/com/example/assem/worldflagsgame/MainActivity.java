package com.example.assem.worldflagsgame;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int [] flagImageList = new int[]{R.drawable.australia, R.drawable.brazil,
            R.drawable.canada, R.drawable.germany, R.drawable.iceland, R.drawable.ireland,
            R.drawable.italy, R.drawable.kazakhstan, R.drawable.netherlands, R.drawable.new_zealand, R.drawable.norway,
            R.drawable.singapore, R.drawable.south_africa, R.drawable.sweden, R.drawable.switzerland};
    final int size = flagImageList.length;
    boolean[] used = new boolean[size];
    String [] countryNames = new String[] {"Australia", "Brazil", "Canada", "Germany", "Iceland",
            "Ireland", "Italy", "Kazakhstan", "Netherlands", "New-Zealand", "Norway",
            "Singapore", "South-Africa", "Sweden", "Switzerland"};
    Random rn = new Random();
    TextView text;
    TableLayout tLayout;
    int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.textView);
        tLayout = (TableLayout)findViewById(R.id.flagContent);

        shuffleFlags();
        rand = rn.nextInt(size);
        text.setText(countryNames[rand]);
    }

    public void textClicking(View view) {
        shuffleFlags();
        rand = rn.nextInt(size);
        text.setText(countryNames[rand]);
    }

    public void shuffleFlags () {
        removing();
        int pos = 0;
        Arrays.fill(used, false);
        rand = rn.nextInt(size);
        TableRow.LayoutParams tlparams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < 5; i++) {
            TableRow tRow = new TableRow(this);
            tRow.setLayoutParams(tlparams);
            for (int j = 0; j < 3; j++) {
                while (used[rand]) rand = rn.nextInt(size);
                View flagView = getLayoutInflater().inflate(R.layout.image_button, tRow, true);
                ImageButton image = (ImageButton) flagView.findViewById(R.id.image_flag);
                image.setImageResource(flagImageList[rand]);
                image.setId(rand);
                used[rand] = true;
            }
            tLayout.addView(tRow);
        }
    }

    boolean checking;
    public void buttonClick(View view) {
        checking = check(view.getId());
        CustomDialogClass cdd = new CustomDialogClass(this, countryNames[view.getId()], checking);
        cdd.show();

        cdd.setOnDismissListener(new DialogInterface.OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (checking) textClicking(text);
            }
        });
    }

    public boolean check (int id){
        if (rand == id) return true;
        return false;
    }

    public void removing () {
        int count = tLayout.getChildCount();
        if (count > 0) {
            tLayout.removeViews(0, count);
        }
    }


}
