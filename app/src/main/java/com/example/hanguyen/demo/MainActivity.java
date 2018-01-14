package com.example.hanguyen.demo;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{
    int soA, soB, result;
    EditText editSoA, editSoB;
    TextView txtResult;
    Button btnResult, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gan cac element xml thanh cac bien java
        editSoA = (EditText) findViewById(R.id.editSoA);
        editSoB = (EditText) findViewById(R.id.editSoB);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnReset = (Button) findViewById(R.id.btnReset);
        //bat su kien onKey cho editor text
        // muc dich: khong cho xuong hang khi bam phim enter
        editSoA.setOnKeyListener(this);
        editSoB.setOnKeyListener(this);
        //bat su kien onclick cho buttons
        btnResult.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        hideTheKeyBoard();//an key board sau khi click
        String sSoA = editSoA.getText().toString();// get value tu editor
        String sSoB = editSoB.getText().toString();
        switch (v.getId()){
            case R.id.btnResult : //neu nhu click vao nut btnResult
                showResult(sSoA,sSoB);// thuc hien ham show
                break;
            case R.id.btnReset : //neu nhu click vao nut btnReset
                resetAll(); // thuc hien ham resetAll,
                break;
        }
    }
    public  void showResult(String sSoA, String sSoB){
        //kiem tra xem la da nhap du truong hay chua
        if(sSoA.isEmpty() || sSoB.isEmpty() ){
            Toast.makeText(MainActivity.this, "Bạn nhập thiếu ít nhất một trường! Mời nhập lại", Toast.LENGTH_LONG).show();
            return;
        }
        //bat ngoai le khi nguoi dung nhap vao chu cai hay khoang trang
        try{
            soA = Integer.parseInt(sSoA);
            soB = Integer.parseInt(sSoB);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Bạn Nhap Sai! Yêu Cầu Nhập Lại", Toast.LENGTH_LONG).show();
            resetEditor();
            return;
        }
        //bat ngoai le khi giai phuong trinh
        try{
            txtResult.setText("Kết quả:  " + String.valueOf(giaiPTB1(soA,soB)));
        }catch (PTBNhatException ptEx){
            txtResult.setText("Kết quả:  " + ptEx.getMessage());
            resetEditor();
        }
    }

    public  void resetEditor(){
        editSoA.setText("");
        editSoB.setText("");
    }

    public void resetAll(){
        resetEditor();
        txtResult.setText("Kết quả:  ");
    }

    public void hideTheKeyBoard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
    // ham giai phuong trinh co nem ngoai le
    public float giaiPTB1(int a, int b) throws PTBNhatException{
        if(a==0){
            throw new PTBNhatException("a phai khac 0"); // nem ngoai le thong qua class PTBexception bang mot messeage
        }else{
            return (float) -b/a; // return kq
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //neu nguoi dung bam phim enter thi khong lam gi
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            return true;
        }
        //mac dinh bat tat ca cac key khac voi enter
        return super.onKeyDown(keyCode, event);
    }
}
