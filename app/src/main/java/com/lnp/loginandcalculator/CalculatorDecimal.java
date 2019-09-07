package com.lnp.loginandcalculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorDecimal {
    private String OrExpress;
    private boolean IsSet = false;

    public CalculatorDecimal() {
    }

    public CalculatorDecimal(String oe) {
        OrExpress = oe;
        IsSet = true;
    }

    public void SetNew(String oe) {
        ops.clear();
        nums.clear();
        OrExpress = oe;
        IsSet = true;
    }

    //operator priority level 运算符优先级字典
    private Map<Character, Integer> OpPLDictionary = new HashMap<Character, Integer>() {{
        put('(', 1);
        put('+', 2);
        put('-', 2);
        put('*', 3);
        put('/', 3);
        put(')', 4);
    }};

    private Stack<Character> ops = new Stack<>();
    private Stack<BigDecimal> nums = new Stack<>();
    // 如果不使用BigDecimal会出现精度问题(如0.1+0.2!=0.3，二进制小数循环的处理方法)
    // 相比来说 C# 对于这些的处理就好一些，相同的算法条件下，使用double也不会出现精度问题(.NET应该做了额外工作)

    private boolean IsHigherThanTop(char op) {
        if (ops.empty()) return true;
        else if (OpPLDictionary.get(op) > OpPLDictionary.get(ops.peek())) return true;
        else return false;
    }

    private void CalculateOne() throws Exception {
        char tmpOp = ops.pop();
        BigDecimal rhs = nums.pop();
        BigDecimal lhs = nums.pop();
        BigDecimal tmpRes;
        switch (tmpOp) {
            case '+':
                tmpRes = lhs.add(rhs); // 注意 Java 不像 C#/C++ 一样支持运算符重载
                break;
            case '-':
                tmpRes = lhs.subtract(rhs);
                break;
            case '*':
                tmpRes = lhs.multiply(rhs);
                break;
            case '/':
                tmpRes = lhs.divide(rhs);
                break;
            default:
                throw new Exception("非法运算符");
        }
        nums.push(tmpRes);
    }

    public BigDecimal GetResult() throws Exception {
        if (!IsSet) throw new Exception("未初始化表达式");
        IsSet = false;
        String tmpNum = "";
        int tmp;
        double tmpDouble;
        for (int i = 0; i < OrExpress.length(); i++) {
            if (Character.isDigit(OrExpress.charAt(i)) || OrExpress.charAt(i) == '.') {
                tmpNum += OrExpress.charAt(i);
                if (i == OrExpress.length() - 1) {
                    nums.push(new BigDecimal(tmpNum));
                    tmpNum = "";
                }
            } else {
                if (tmpNum != "") {
                    nums.push(new BigDecimal(tmpNum));
                    tmpNum = "";
                }
                if (OrExpress.charAt(i) == '(') ops.push('(');
                else if (OrExpress.charAt(i) == ')') {
                    while (ops.peek() != '(') CalculateOne();
                    ops.pop();
                } else if (IsHigherThanTop(OrExpress.charAt(i))) ops.push(OrExpress.charAt(i));
                else {
                    while (!IsHigherThanTop(OrExpress.charAt(i))) CalculateOne();
                    ops.push(OrExpress.charAt(i));
                }
            }
        }
        while (!ops.empty()) CalculateOne();
        return nums.peek();
    }

    // 返回double类型的结果函数
    public double GetDoubleResult() throws Exception {

        BigDecimal DecimalRes = GetResult();
        return DecimalRes.doubleValue();
    }
}

