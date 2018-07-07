package com.example.ali.showhide;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView Timer ;
    private Button Startgame;
    private TextView total ;
    private  TextView Score;
   private ArrayList<Button> buttons;
    private  int correctresult;
    private int first;
    private int second;
    private int num1;
    private int num2;
    private TextView math;
    boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer = findViewById(R.id.Timer);
        Startgame = findViewById(R.id.Start);
        total=findViewById(R.id.total);
        Score= findViewById(R.id.score);
        math= findViewById(R.id.math);
        buttons= new ArrayList<>();
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        correctresult=0;

        active=false;
        math.setText(num1+"/"+num2);


    }
    public  void  StartGame(View view){
        Startgame.setVisibility(View.INVISIBLE);
        first=second=0;
        Score.setText("0/0");
        active= true;
        total.setText("");
        updatebuttons();
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
            Timer.setText((l/1000)+"S");
            }

            @Override
            public void onFinish() {
                Startgame.setVisibility(View.VISIBLE);
                Timer.setText("30S");
                total.setText("your score is "+Score.getText());
                active=false;

            }
        }.start();
    }
    public void PlayGame(View view) {
        if (active) {
            boolean a = true;
            switch (view.getId()) {
                case R.id.button1:
                    a = Integer.parseInt(buttons.get(0).getText().toString()) == correctresult;
                    break;
                case R.id.button2:
                    a = Integer.parseInt(buttons.get(1).getText().toString()) == correctresult;
                    break;
                case R.id.button3:
                    a = Integer.parseInt(buttons.get(2).getText().toString()) == correctresult;
                    break;
                case R.id.button4:
                    a = Integer.parseInt(buttons.get(3).getText().toString()) == correctresult;
                    break;
            }

            updatebuttons();
            updateScore(a);
            if (a)
                total.setText("correct!");
            else total.setText("Wrong!");
        }
    }

    private void updatebuttons() {
        Random rand = new Random();
        int rundombutton  = rand.nextInt(4) %4;
        num1= rand.nextInt(50)+1;
        num2= rand.nextInt(50)+1;
        correctresult= num1+num2;
        buttons.get(rundombutton).setText(correctresult+"");
        for (int i=0 ;i<4;i++)
        {
            if (i==rundombutton)
                continue;
            buttons.get(i).setText((rand.nextInt(100)+1)+"");
        }
        math.setText(num1+"+"+num2);
    }
    private void updateScore(boolean a){
        if (a)
        {
            first++;
        }
        second++;
        Score.setText(first+"/"+second);
    }

}
