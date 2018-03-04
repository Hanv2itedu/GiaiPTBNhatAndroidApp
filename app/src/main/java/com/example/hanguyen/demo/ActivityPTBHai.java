package com.example.hanguyen.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class ActivityPTBHai extends AppCompatActivity implements View.OnClickListener {
    Button btnResult, btnReset, btnBack;
    EditText editSoA, editSoB, editSoC;
    int soA, soB, soC;
    String sResult;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptbhai);

        editSoA = findViewById(R.id.editSoA);
        editSoB = findViewById(R.id.editSoB);
        editSoC = findViewById(R.id.editSoC);

        txtResult = findViewById(R.id.txtResult);
        btnResult = findViewById(R.id.btnResult);
        btnReset = findViewById(R.id.btnReset);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        hideTheKeyBoard();//an key board sau khi click
        String sSoA = editSoA.getText().toString();// get value tu editor
        String sSoB = editSoB.getText().toString();
        String sSoC = editSoC.getText().toString();
        switch (v.getId()){
            case R.id.btnResult : //neu nhu click vao nut btnResult
                showResult(sSoA,sSoB,sSoC);// thuc hien ham show
                break;
            case R.id.btnReset : //neu nhu click vao nut btnReset
                resetAll(); // thuc hien ham resetAll,
                break;
            case R.id.btnBack : // neu nhu click vao btnBack
                Intent newIntent = new Intent(this, MainActivity.class);
                newIntent.putExtra("soA", sSoA);
                newIntent.putExtra("soB", sSoB);
                newIntent.putExtra("soC", sSoC);
                newIntent.putExtra("result", sResult);
                startActivity(newIntent);
        }
    }

    public  void resetEditor(){
        editSoA.setText("");
        editSoB.setText("");
        editSoC.setText("");
    }

    public void resetAll(){
        resetEditor();
        txtResult.setText("Kết quả:  ");
    }
    private void showResult(String sSoA,String sSoB,String sSoC){
        //kiem tra xem la da nhap du truong hay chua
        if(sSoA.isEmpty() || sSoB.isEmpty() || sSoB.isEmpty() ){
            Toast.makeText(ActivityPTBHai.this, "Bạn nhập thiếu ít nhất một trường! Mời nhập lại", Toast.LENGTH_LONG).show();
            return;
        }
        try{
            soA = Integer.parseInt(sSoA);
            soB = Integer.parseInt(sSoB);
            soC = Integer.parseInt(sSoC);
            sResult =  giaiPTB2(soA,soB,soC);
            txtResult.setText("Kết quả:  " + sResult);
        }catch (NumberFormatException e){
            Toast.makeText(ActivityPTBHai.this, "Bạn Nhap Sai! Yêu Cầu Nhập Lại", Toast.LENGTH_LONG).show();
            resetEditor();
            return;
        }

    }

    private String giaiPTB2(int a, int b, int c) {
        if(a==0){
            return "a phai khac 0";
        }else{
            int d;
            d = b*b - 4*a*c;
            if(d<0) {
                return "Phương trình vô nghiệm";
            }
            else if(d==0) return "Phương trình có nghiệm x = " + -b/2/a;
            else return "Phương trình có 2 nghiệm x1 = " +((-b-Math.sqrt(d))/2/a)+ ", x2 = "+((-b+Math.sqrt(d))/2/a);
        }
    }

    public void hideTheKeyBoard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
