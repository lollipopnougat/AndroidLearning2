package com.lnp.loginandcalculator;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button btn = null;
    private EditText et1 = null;
    private EditText et2 = null;
    private TextView tv1 = null;
    private Random random = new Random();
    private String randExpression;
    private double correctResult;
    private CalculatorDecimal calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        btn = findViewById(R.id.submit);
        et1 = findViewById(R.id.uname);
        et2 = findViewById(R.id.pass);
        tv1 = findViewById(R.id.express1);
        tv1.setText(random.NextExpression() + "=?");
        randExpression = random.getRandStrExp();
        try {
            calculator = new CalculatorDecimal(randExpression);
            correctResult = calculator.GetDoubleResult();
            //startActivity(intent);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("Uname", et1.getText().toString());
                intent.putExtra("Pass", et2.getText().toString());
                intent.putExtra("Expression", randExpression);
                //startActivity(intent);
                startActivityForResult(intent, 1000);
            });
        } catch (Exception er) {
            Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "取消操作", Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_OK) {
            double calRes = data.getDoubleExtra("Result", 0.00);
            if (calRes == correctResult) {
                tv1.setText(randExpression + "=" + calRes);
                Toast.makeText(this, "正确!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "计算结果错误！", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, data.getStringExtra("Status"), Toast.LENGTH_SHORT).show();
    }


}
