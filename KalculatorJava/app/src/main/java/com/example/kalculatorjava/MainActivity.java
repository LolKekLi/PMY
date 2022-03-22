package com.example.kalculatorjava;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> funcValue = new ArrayList(Arrays.asList("/","+","-","*"));

    private TextView resultText;
    private TextView expressionText;
    private TextView MText;

    private ArrayList<Button> value_buttons = new ArrayList<>();

    private Button minus;
    private Button plus;
    private Button startSkobka;
    private Button endSkobka;
    private Button umnoshit;
    private Button delenie;
    private ImageButton clear;
    private Button back;
    private Button sin;
    private Button cos;
    private Button e;
    private Button result;
    private Button point;

    private Button MC;
    private Button MR;
    private Button MPlus;
    private Button MMinus;

    private String MVlaue = "";
    private String expressionString = "";

    private boolean canPoint = true;
    private boolean isResultButtonClick = false;

    enum MFuncType
    {
        MPlus,
        MMinus,
        MResult,
        MClear,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Prepare();
    }

    private void Prepare() {
        resultText = findViewById(R.id.Result);
        expressionText = findViewById(R.id.ExpressionText);
        MText = findViewById(R.id.MText);

        PrepareValueButton();
        PrepareFuncButton();
    }

    private void PrepareFuncButton()
    {
        minus = findViewById(R.id.button_minus);
        plus = findViewById(R.id.button_plus);
        delenie = findViewById(R.id.button_delenie);
        umnoshit = findViewById(R.id.button_umnoshit);
        result = findViewById(R.id.button_MResult);
        point = findViewById(R.id.button_point);

        clear = findViewById(R.id.buttuns_clear);
        back = findViewById(R.id.back);

        startSkobka = findViewById(R.id.buttuns_startScobochka);
        endSkobka = findViewById(R.id.buttuns_endScobochka);
        sin = findViewById(R.id.button_sin);
        cos = findViewById(R.id.button_cos);

        MC = findViewById(R.id.button_MClear);
        MR = findViewById(R.id.button_MR);
        MPlus = findViewById(R.id.button_MPlus);
        MMinus = findViewById(R.id.button_MMinus);

        //region MFunc
        MC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    MFunc(MFuncType.MClear);
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        MR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    MFunc(MFuncType.MResult);
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        MPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    MFunc(MFuncType.MPlus);
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        MMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    MFunc(MFuncType.MMinus);
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });
//endregion

        //region Func
        minus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression("-");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression("+");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        delenie.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression("/");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        umnoshit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression("*");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        //endregion

        result.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    if(isResultButtonClick)
                    {
                        CharSequence text = resultText.getText();
                        expressionString = (String) text;
                        expressionText.setText(resultText.getText());
                        isResultButtonClick = false;
                    }
                    RefreshResult();

                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    isResultButtonClick = false;
                    Clear();
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                isResultButtonClick = false;
                RemoveLastSumbol();
                RefreshExpression();
            }
        });

        //region TrigFunc
        sin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    TrigFunc("sin");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        cos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    TrigFunc("cos");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        sin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                OpenNewActivity("sin");
                return false;
            }
        });

        cos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                OpenNewActivity("cos");
                return false;
            }
        });
        //endregion

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression(".");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        startSkobka.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression("(");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        endSkobka.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression(")");
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });
    }

    private void MFunc(MFuncType type) throws ScriptException {

        switch (type){
            case MPlus:
                MText.setText("M");
                RefreshResult();
                RefreshExpression();
                String text = resultText.getText().toString();
                MVlaue = "+" + text;
                break;
            case MMinus:
                MText.setText("M");
                RefreshResult();
                RefreshExpression();
                Double text1 = Double.valueOf(resultText.getText().toString());
                MVlaue = "-" + text1;
                break;
            case MResult:
                if (!MVlaue.isEmpty())
                RefreshExpression(MVlaue);
                break;
            case MClear:
                MText.setText("");
                MVlaue = "";
                break;
        }
    }

    private void RefreshExpression(double mVlaue) {

        if (!expressionString.isEmpty()) {
            String lastSymbol = getLastSymbol(expressionString);
            if (funcValue.contains(lastSymbol)) {
                expressionString = removeCharAt(expressionString,expressionString.length()-1);
            }
            else if (lastSymbol.equals(")"))
            {
                return;
            }
        }

        expressionString+=mVlaue;
        expressionText.setText(expressionString);
    }

    private void TrigFunc(String trigFunc) throws ScriptException {
        if (expressionString.isEmpty())
        {
            return;
        }

        if (funcValue.contains(getLastSymbol( getLastSymbol(expressionString))))
        {
            expressionString = removeCharAt(expressionString,expressionString.length()-1);
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(expressionString);

        if (trigFunc=="cos")
        {
            double cos = Math.cos(new Double(result.toString()));
            expressionString = String.valueOf(cos);
        }
        else
        {
            double sin = Math.sin(new Double(result.toString()));
            expressionString = String.valueOf(sin);
        }

        RefreshExpression();
        RefreshResult();
    }

    private void OpenNewActivity(String func) {
        Intent intent = new Intent(this, Helper.class);
        String message = func;
        intent.putExtra("message", message);
        startActivity(intent);
    }

    private void RemoveLastSumbol() {
        if (!expressionString.isEmpty()) {
            expressionString = removeCharAt(expressionString, expressionString.length() - 1);
        }
    }

    private void Clear() throws ScriptException
    {
        expressionString ="";

        canPoint = true;

        RefreshExpression("");
        RefreshResult();
    }

    private void PrepareValueButton()
    {
        value_buttons.add(findViewById(R.id.button_1));
        value_buttons.add(findViewById(R.id.button_2));
        value_buttons.add(findViewById(R.id.button_3));
        value_buttons.add(findViewById(R.id.button_4));
        value_buttons.add(findViewById(R.id.button_5));
        value_buttons.add(findViewById(R.id.button_6));
        value_buttons.add(findViewById(R.id.button_7));
        value_buttons.add(findViewById(R.id.button_8));
        value_buttons.add(findViewById(R.id.button_9));
        value_buttons.add(findViewById(R.id.button_0));

        for (int i = 0; i <value_buttons.size(); i++) {
            AddListenerOnValueButton(value_buttons.get(i));
        }
    }

    private void AddListenerOnValueButton(Button button)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RefreshExpression(button.getText().toString());
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void RefreshExpression(String sumbol) throws ScriptException
    {
        if (funcValue.contains(sumbol))
        {
            canPoint = true;

            if (expressionString.isEmpty())
            {
                if(sumbol.equals("-"))
                {
                    expressionString += sumbol;
                    expressionText.setText(expressionString);
                }

                return;
            }

            String lastSymbol = getLastSymbol(expressionString);

            if (funcValue.contains(lastSymbol))
            {
                String s = removeCharAt(expressionString, expressionString.length() - 1);
                if (s.isEmpty())
                {
                    return;
                }
                expressionString = s;
                expressionString += sumbol;
                isResultButtonClick = false;
                expressionText.setText(expressionString);
                return;
            }
        }

        if (sumbol.equals(")"))
        {
            if (expressionString.isEmpty())
            {
                return;
            }

            String lastSymbol = getLastSymbol(expressionString);

            if (lastSymbol.equals("("))
            {
                return;
            }

            if (funcValue.contains(lastSymbol))
            {
                return;
            }
        }
        else
        {
            if (sumbol==".")
            {
                if (expressionString.isEmpty()) {
                    expressionString = "0" + sumbol;
                    isResultButtonClick = false;
                    expressionText.setText(expressionString);
                    return;
                }

                if (!canPoint)
                {
                    return;
                }

                String lastSymbol = getLastSymbol(expressionString);

                if (lastSymbol.equals(")") || lastSymbol.equals("("))
                {
                    return;
                }

                if(funcValue.contains(lastSymbol))
                {
                    sumbol = "0" + sumbol;
                }

                canPoint = false;
            }

            if (sumbol.equals("(")) {
                if (!expressionString.isEmpty()) {
                    String lastSymbol = getLastSymbol(expressionString);
                    if (!lastSymbol.equals("(")) {
                        if (!funcValue.contains(lastSymbol)) {
                            return;
                        }
                    }
                }
            }
        }

        isResultButtonClick = false;
        expressionString += sumbol;
        expressionText.setText(expressionString);
    }

    private void RefreshExpression()
    {
        expressionText.setText(expressionString);
    }

    private void RefreshResult() throws ScriptException
    {
        isResultButtonClick = true;
        String resultStr ="";

        if (!expressionString.isEmpty())
        {
            CheckFuncSumbol();

            FixScobci();

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");

            double result = (double) engine.eval(expressionString);

            if (result % 1==0)
            {
                resultStr = String.valueOf((int)result);
            }
            else {
                resultStr = String.valueOf(result);
            }
        }

        resultText.setText(resultStr);
    }

    private void CheckFuncSumbol()
    {
        boolean isWithoutFuncSumbol = true;

        for (int i = 0; i < funcValue.size(); i++)
        {
            if (expressionString.contains(funcValue.get(i)))
            {
                isWithoutFuncSumbol = false;
                break;
            }
        }


        if (isWithoutFuncSumbol)
        {
            resultText.setText(expressionString);
            return;
        }

        if (funcValue.contains(getLastSymbol(expressionString)))
        {
            RemoveLastSumbol();
            RefreshExpression();
        }
    }

    private void FixScobci() {
        int startScobkaCount =0;
        int endScobkaCount =0;

        if (!expressionString.contains(")") && !expressionString.contains("("))
        {
            return;
        }

        char[] charStrArray = expressionString.toCharArray();
        for (int i = 0; i < charStrArray.length; i++) {
            if (charStrArray[i]==')')
            {
                endScobkaCount++;
            }
            else if (charStrArray[i]=='(')
            {
                startScobkaCount++;
            }
        }

        if (startScobkaCount == endScobkaCount)
        {
            return;
        }

        if(startScobkaCount > endScobkaCount)
        {
           int count = startScobkaCount - endScobkaCount;
            for (int i = 0; i < count; i++) {
                expressionString +=")";
            }
        }
        else
        {
            int count = endScobkaCount - startScobkaCount;
            String appendStr ="";
            for (int i = 0; i < count; i++) {
                appendStr +="(";
            }
            expressionString = appendStr+expressionString;
        }

        RefreshExpression();
    }

    private String removeCharAt(String s, int pos)
    {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    private String getSymbol(String str, int pos)
    {
        if (pos >= str.length())
        {
            pos = str.length()-1;
        }

        char[] charStrArray = str.toCharArray();
        String sumbol = String.valueOf(charStrArray[pos]);

        return sumbol;
    }

    private String getLastSymbol(String str)
    {
        char[] charStrArray = str.toCharArray();
        String sumbol = String.valueOf(charStrArray[str.length()-1]);

        return sumbol;
    }
}
