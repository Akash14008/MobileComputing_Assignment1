package com.example.karan.assignment_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mCorrectButton;
    private Button mIncorrectButton;
    private Button mNextButton;
    private TextView mRandNum;
    private int[] prime = new  int[1001];
    private int randomNumber;
    private Toast rightToast;
    private Toast wrongToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCorrectButton = (Button) findViewById(R.id.correctButton);
        mIncorrectButton = (Button) findViewById(R.id.incorrectButton);
        mNextButton = (Button) findViewById(R.id.nextButton);
        mRandNum = (TextView) findViewById(R.id.randNum);
        rightToast = Toast.makeText(getApplicationContext(), "Your answer is right!", Toast.LENGTH_SHORT);
        wrongToast = Toast.makeText(getApplicationContext(), "Your answer is wrong!", Toast.LENGTH_SHORT);

        prime[1] = 0;                                                                               // Sieve of Eratosthenes
        int i, j;
        for(i = 2; i <= 1000; i++)
            prime[i] = 1;
        i = 2;
        while(i * i <= 1000)  {
            if(prime[i] == 1) {
                j = 2 * i;
                while(j <= 1000) {
                    prime[j] = 0;
                    j = j + i;
                }
            }
            i++;
        }

        if(savedInstanceState != null) {
            mRandNum.setText(savedInstanceState.getCharSequence("savedRandNum"));
            randomNumber = Integer.parseInt(mRandNum.getText().toString());
        }
        else {
            Random r = new Random();                                                                // Generate Random Numbers
            randomNumber = r.nextInt(1000) + 1;
            mRandNum.setText("" + randomNumber);
        }

        mCorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prime[randomNumber] == 1)
                    rightToast.show();
                else
                    wrongToast.show();
            }
        });

        mIncorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prime[randomNumber] == 1)
                    wrongToast.show();
                else
                    rightToast.show();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                randomNumber = r.nextInt(1000) + 1;
                mRandNum.setText("" + randomNumber);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("savedRandNum", mRandNum.getText());
    }
}
