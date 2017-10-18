package com.sergiohilgert.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
  
  Button button0;
  Button button1;
  Button button2;
  Button button3;
  TextView score;
  TextView time;
  TextView operation;
  TextView message;
  Button start;
  int correctAnswer;
  int total;
  int right;
  List<Integer> answares = new ArrayList<Integer>();
  
  
  public void start(View view){
    reset();
    start.setVisibility(View.INVISIBLE);
    generateNewQuestion();
    
    new CountDownTimer(30100, 1000){
  
      @Override
      public void onTick(long l) {
        time.setText(String.valueOf(l/1000) + "s");
    
      }
  
      @Override
      public void onFinish() {
        start.setText("Play again");
        time.setText("0s");
        start.setVisibility(View.VISIBLE);
        message.setText("Your score is " + right + "/" + total);
        button0.setClickable(false);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
      }
    }.start();
    
  }
  
  public void reset(){
    total = 0;
    right = 0;
    time.setText("30s");
    score.setText("0/0");
    message.setText("");
    button0.setClickable(true);
    button1.setClickable(true);
    button2.setClickable(true);
    button3.setClickable(true);
  }
  
  public void generateNewQuestion(){
    Random random = new Random();
    int a = random.nextInt(51);
    int b = random.nextInt(51);
    operation.setText(Integer.toString(a) + " + " + Integer.toString(b));
    correctAnswer = random.nextInt(4);
    answares.clear();
    for(int i = 1; i <= 4; ++i){
      if (i-1 == correctAnswer){
        answares.add(a+b);
      }else{
        if(i % 2 == 0){
          answares.add(a+b-i);
        }else{
          answares.add(a+b+i);
        }
      }
    }
    button0.setText(Integer.toString(answares.get(0)));
    button1.setText(Integer.toString(answares.get(1)));
    button2.setText(Integer.toString(answares.get(2)));
    button3.setText(Integer.toString(answares.get(3)));
    
  }
  
  public void checkAnswer(View view){
    if(view.getTag().equals(Integer.toString(correctAnswer))){
      right++;
      message.setText("Correct");
    }else{
      message.setText("Wrong");
    }
    total++;
    score.setText(Integer.toString(right) + "/" + Integer.toString(total));
    generateNewQuestion();
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    start = (Button) findViewById(R.id.startButton);
    
    button0 = (Button) findViewById(R.id.button0);
    button1 = (Button) findViewById(R.id.button1);
    button2 = (Button) findViewById(R.id.button2);
    button3 = (Button) findViewById(R.id.button3);
    
    message = (TextView) findViewById(R.id.messageTextView);
    score = (TextView) findViewById(R.id.scoreTextView);
    time = (TextView) findViewById(R.id.timeTextView);
    operation = (TextView) findViewById(R.id.operationTextView);
    
    
  }
}
