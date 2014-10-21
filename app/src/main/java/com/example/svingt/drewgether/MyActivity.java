package com.example.svingt.drewgether;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MyActivity extends Activity {
    MyView thisview;
    TextView textview1, textview2;
    Spinner spinner1, spinner2;
    Button button1, button2, button3, button4;
    ArrayAdapter colorAdapter;
    MyApplication appState;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        init();

    }

    protected void init(){
        ab = getActionBar();
        setAB();
        appState = (MyApplication)getApplicationContext();
        thisview = (MyView)findViewById(R.id.myview1);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        setButtons();
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        textview1 = (TextView)findViewById(R.id.textView);
        textview2 = (TextView)findViewById(R.id.textView2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_list_item_1);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textview1.setText(Integer.toString(parent.getSelectedItemPosition()));
                switch(parent.getSelectedItemPosition()){
                    case 0:
                        thisview.changePaint(Color.BLACK);
                        break;
                    case 1:
                        thisview.changePaint(Color.GRAY);
                        break;
                    case 2:
                        thisview.changePaint(Color.WHITE);
                        break;
                    case 3:
                        thisview.changePaint(Color.BLUE);
                        break;
                    case 4:
                        thisview.changePaint(Color.RED);
                        break;
                    case 5:
                        thisview.changePaint(Color.GREEN);
                        break;
                    case 6:
                        thisview.changePaint(Color.YELLOW);
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textview2.setText(Integer.toString(parent.getSelectedItemPosition()));
                switch(parent.getSelectedItemPosition()){
                    case 0:
                        thisview.changeBackground(Color.BLACK);
                        break;
                    case 1:
                        thisview.changeBackground(Color.GRAY);
                        break;
                    case 2:
                        thisview.changeBackground(Color.WHITE);
                        break;
                    case 3:
                        thisview.changeBackground(Color.BLUE);
                        break;
                    case 4:
                        thisview.changeBackground(Color.RED);
                        break;
                    case 5:
                        thisview.changeBackground(Color.GREEN);
                        break;
                    case 6:
                        thisview.changeBackground(Color.YELLOW);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void setButtons(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thisview.pencil)
                    thisview.pencil = false;
                else
                    thisview.pencil = true;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisview.clear();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisview.eraserPaint();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMyActivity2(v);
            }
        });
    }

    public void goMyActivity2(View view){
        Intent intent = new Intent(this, MyActivity2.class);
        startActivity(intent);
    }

    public void setAB(){

        ab.setLogo(R.drawable.ic_launcher);
        ab.setTitle("Scribble");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
