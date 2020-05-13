package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set mainactivity to layout activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create calculator object and connect workspace to layout workspace and initialize to 0
        final Calculator calc = new Calculator();
        calc.workspace = findViewById(R.id.workspace);
        calc.workspace.setText("0");

        //set up all number buttons
        int[] idList = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,};
        //for loop will add correct click listener based on idList which contains button ids.
        for(i = 0; i < 10; i++){
            findViewById(idList[i]).setOnClickListener(new View.OnClickListener(){
                final int number = i;
                public void onClick(View v) {
                    calc.appendDigit(number);
                }
            });
        }

        //special buttons
        //add proper click listener to each button
        //clear button
        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                calc.clear();
            }
        });

        //equal button
        findViewById(R.id.equal_button). setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty() || calc.workspace.getText().toString()=="∞"){
                    return;
                }
                calc.addLast(); //add second
                calc.evaluate(); //evaluate
            }
        });
        // decimal
        findViewById(R.id.decimal_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                calc.appendDecimal();
            }
        });
        // plus minus
        findViewById(R.id.plus_minus_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty()){
                    return;
                }
                //calculator sign function
                calc.signChange();
            }
        });
        // division
        findViewById(R.id.div_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty()){
                    //change operation anyways
                    calc.addOperation(0);
                    return;
                }
                calc.addFirst(); //add first
                calc.addOperation(0);
            }
        });
        // multiplication
        findViewById(R.id.times_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty()){
                    //change operation anyways
                    calc.addOperation(1);
                    return;
                }
                
                calc.addFirst(); //add first
                calc.addOperation(1);
            }
        });
        // subtraction
        findViewById(R.id.sub_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty()){
                    //change operation anyways
                    calc.addOperation(2);
                    return;
                }
                calc.addFirst(); //add first
                calc.addOperation(2);
            }
        });
        // plus
        findViewById(R.id.plus_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty()){
                    //change operation anyways
                    calc.addOperation(3);
                    return;
                }
                calc.addFirst(); //add first
                calc.addOperation(3);
            }
        });
        // percent
        findViewById(R.id.percent_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //to avoid errors check if empty
                if(calc.empty()){
                    return;
                }
                calc.applyPercent(); //add first

            }
        });
    }
}
