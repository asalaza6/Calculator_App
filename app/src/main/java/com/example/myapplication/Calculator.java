package com.example.myapplication;

import android.widget.TextView;
import java.text.DecimalFormat;

public class Calculator {
    //two numbers used in operation
    double firstNum;
    double lastNum;
    //operation variable
    int operation; //no operation = -1
    //actual text space, showing numbers
    TextView workspace;
    //tool to help round numbers to 7 decimals
    DecimalFormat df;
    //booleans insure only 1 decimal is added, and to delete the current number from workspace on next number choice
    boolean deleteOld;
    boolean decimalAdded;

    //constructor
    public Calculator(){
        df = new DecimalFormat("#.#######");
        operation = -1;
        deleteOld = true;
        decimalAdded = false;
    }
    //add digit to workspace
    public void appendDigit(int num){
        //if deleteold clear workspace before adding new number
        if(deleteOld){
            workspace.setText("");
            deleteOld = false;
        }
        workspace.append(String.valueOf(num));
    }
    //add a decimal
    public void appendDecimal(){
        //if deleteold clear workspace before adding new number
        if(deleteOld){
            workspace.setText("");
            deleteOld = false;
        }else if(decimalAdded){ //checks if decimal has already been added
            return;
        }
        //flag to indicate decimal has been added
        decimalAdded = true;
        //add decimal
        workspace.append(".");
    }
    //apply percent, change workspace value by dividing by 100
    public void applyPercent(){
        workspace.setText(String.valueOf(Double.parseDouble(workspace.getText().toString())/100.0));
    }
    //change sign of workspace value
    public void signChange(){
        workspace.setText(String.valueOf(-1*Double.parseDouble(workspace.getText().toString())));
    }
    //check if workspace is empty - useful to avoid errors
    public boolean empty(){
        return(workspace.getText().length() == 0);
    }
    //change/add value of firstNum
    public void addFirst(){
        firstNum = Double.parseDouble(workspace.getText().toString());
    }
    //change/add value of secondNum
    public void addLast(){
        lastNum = Double.parseDouble(workspace.getText().toString());
    }
    //change operation variable
    public void addOperation(int op){
        operation = op; //0 for / 1 for * 2 for - 3 for +
        deleteOld = true;
    }
    //clear calculator not necessary to reset numbers b/c operation = -1 tells program to restart
    public void clear(){
        operation = -1;
        workspace.setText("");
        decimalAdded = false;
    }
    //evaluate and change workspace to answer
    public void evaluate(){
        //if no operation selected return
        if(operation == -1){
            return;
        }
        double answer = 0;
        switch(operation){

            //switch statement to go through different operations
            case 0://divide
                answer = (firstNum/lastNum);
                break;
            case 1://multiply
                answer = firstNum*lastNum;
                break;
            case 2://subtract
                answer = firstNum-lastNum;
                break;
            case 3://add
                answer = firstNum+lastNum;
                break;
        }
        //change firstNum to lastNum to ensure the repeatability of the equal sign.
        firstNum = lastNum;
        //set flag to true to delete number on next append
        deleteOld = true;
        //use df to round
        workspace.setText(df.format(answer));
    }
}
