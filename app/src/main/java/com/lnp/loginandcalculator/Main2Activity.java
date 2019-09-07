package com.lnp.loginandcalculator;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    private final String correctUname = "LNP";
    private final String correctPass = "1324";
    private TextView tv1 = null;
    private TextView tv2 = null;
    private double calRes;
    private boolean isCal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String tUname = getIntent().getStringExtra("Uname");
        String tPass = getIntent().getStringExtra("Pass");
        if (!(tUname.equals(correctUname) && tPass.equals(correctPass))) {
            //Toast.makeText(this, "错误!uname= " + tUname + " pass= " + tPass, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, tUname, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("Status", "用户名或密码错误");
            setResult(RESULT_FIRST_USER, intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.expression);
        tv1.setText(getIntent().getStringExtra("Expression") + "=?");
    }

    private CalculatorDecimal calculator = new CalculatorDecimal();
    private String OrExpression = "";

    public void press1(View v) {
        OrExpression += "1";
        tv2.setText(OrExpression);
    }

    public void press2(View v) {
        OrExpression += "2";
        tv2.setText(OrExpression);
    }

    public void press3(View v) {
        OrExpression += "3";
        tv2.setText(OrExpression);
    }

    public void press4(View v) {
        OrExpression += "4";
        tv2.setText(OrExpression);
    }

    public void press5(View v) {
        OrExpression += "5";
        tv2.setText(OrExpression);
    }

    public void press6(View v) {
        OrExpression += "6";
        tv2.setText(OrExpression);
    }

    public void press7(View v) {
        OrExpression += "7";
        tv2.setText(OrExpression);
    }

    public void press8(View v) {
        OrExpression += "8";
        tv2.setText(OrExpression);
    }

    public void press9(View v) {
        OrExpression += "9";
        tv2.setText(OrExpression);
    }

    public void press0(View v) {
        OrExpression += "0";
        tv2.setText(OrExpression);
    }

    public void pressPoint(View v) {
        OrExpression += ".";
        tv2.setText(OrExpression);
    }

    public void pressAdd(View v) {
        OrExpression += "+";
        tv2.setText(OrExpression);
    }

    public void pressSub(View v) {
        OrExpression += "-";
        tv2.setText(OrExpression);
    }

    public void pressMul(View v) {
        OrExpression += "*";
        tv2.setText(OrExpression);
    }

    public void pressDiv(View v) {
        OrExpression += "/";
        tv2.setText(OrExpression);
    }

    public void pressClear(View v) {
        if (OrExpression.length() > 0)
            OrExpression = OrExpression.substring(0, OrExpression.length() - 1);
        if (OrExpression.isEmpty()) tv2.setText(R.string.expression);
        else tv2.setText(OrExpression);
    }

    public void clickCal(View v) {
        if (OrExpression.isEmpty()) {
            Toast.makeText(this, "不能计算空值表达式!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            calculator = new CalculatorDecimal(OrExpression);
            calRes = calculator.GetDoubleResult();
            isCal = true;
            Toast.makeText(getApplicationContext(), "结果是" + calRes, Toast.LENGTH_SHORT).show();
            tv2.setText(OrExpression + "=" + calRes);
        } catch (Exception er) {
            Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickSubmit(View v) {
        if (!isCal) {
            Toast.makeText(this, "请先计算", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("Result", calRes);
        setResult(RESULT_OK, intent);
        finish();
    }


}



