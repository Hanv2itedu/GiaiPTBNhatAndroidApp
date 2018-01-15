package com.example.hanguyen.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnPTB1, btnPTB2;    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent != null){
            Toast.makeText(MainActivity.this,
                    "Last item you did is " + intent.getStringExtra("soA") + "x + " +
                    intent.getStringExtra("soB") + "= 0" + intent.getStringExtra("result"),
                    Toast.LENGTH_LONG).show();
        }

        btnPTB1 = (Button) findViewById(R.id.btnPTB1);
        btnPTB2 = (Button) findViewById(R.id.btnPTB2);

        btnPTB1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {       
        switch (v.getId()){
            case R.id.btnPTB1 : //neu nhu click vao nut btnPTB1
                Intent newIntent = new Intent(MainActivity.this, ActivityPhuongTrinhBacNhat.class);
                startActivity(newIntent);
                break;
            case R.id.btnPTB2 : //neu nhu click vao nut btnPTB2
                break;
        }
    }

}
