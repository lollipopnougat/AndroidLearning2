package com.lnp.loginandcalculator;
import java.text.*;

public class Random {
    public Random() {
    }

    private int randint;
    private double randoub;
    private char randchar;
    private String randStrExp;

    public int getRandint() {
        return randint;
    }

    public double getRandoub() {
        return randoub;
    }

    public char getRandchar() {
        return randchar;
    }

    public String getRandStrExp() {
        return randStrExp;
    }

    public double NextDouble() {
        randoub = Math.random();
        return randoub;
    }

    public double NextDouble(int m) {
        randoub = Math.random() * m;
        return randoub;
    }

    public int Next(int n) {
        randint = (int) (Math.random() * n);
        return randint;
    }

    public char NextOp() {
        double tmp = Math.random();
        if (tmp < 0.25) randchar = '+';
        else if (tmp < 0.5) randchar = '-';
        else if (tmp < 0.75) randchar = '*';
        else randchar = '/';
        return randchar;
    }

    public String NextExpression() {
        double lhs = getFormatDouble(NextDouble(Next(100)),2);
        double rhs;
        char op = NextOp();
        do rhs = getFormatDouble(NextDouble(Next(100)),2);
        while (op == '/' && rhs == 0);
        randStrExp = lhs + Character.toString(op) + rhs;
        return randStrExp;
    }

    public double getFormatDouble(double d, int n) {
        NumberFormat formater  =  DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(n);  //设置小数点后最长的个数
        return Double.parseDouble(formater.format(d));
    }
}
