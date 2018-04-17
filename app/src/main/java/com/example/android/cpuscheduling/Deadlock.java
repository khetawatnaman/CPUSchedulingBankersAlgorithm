package com.example.android.cpuscheduling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Deadlock extends AppCompatActivity {

    int a[][] = new int[4][3];
    int b[][] = new int[4][3];
    int c[] = new int[3];
    int need[][] = new int[4][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlock);
    }

    public void submit(View view) {
        EditText a1 = (EditText) findViewById(R.id.e1);
        EditText a2 = (EditText) findViewById(R.id.e2);
        EditText a3 = (EditText) findViewById(R.id.e3);
        EditText a4 = (EditText) findViewById(R.id.e4);
        EditText a5 = (EditText) findViewById(R.id.e5);
        EditText a6 = (EditText) findViewById(R.id.e6);
        EditText a7 = (EditText) findViewById(R.id.e7);
        EditText a8 = (EditText) findViewById(R.id.e8);
        EditText a9 = (EditText) findViewById(R.id.e9);
        EditText a10 = (EditText) findViewById(R.id.e10);
        EditText a11 = (EditText) findViewById(R.id.e11);
        EditText a12 = (EditText) findViewById(R.id.e12);
        EditText a13 = (EditText) findViewById(R.id.e13);
        EditText a14 = (EditText) findViewById(R.id.e14);
        EditText a15 = (EditText) findViewById(R.id.e15);
        EditText a16 = (EditText) findViewById(R.id.e16);
        EditText a17 = (EditText) findViewById(R.id.e17);
        EditText a18 = (EditText) findViewById(R.id.e18);
        EditText a19 = (EditText) findViewById(R.id.e19);
        EditText a20 = (EditText) findViewById(R.id.e20);
        EditText a21 = (EditText) findViewById(R.id.e21);
        EditText a22 = (EditText) findViewById(R.id.e22);
        EditText a23 = (EditText) findViewById(R.id.e23);
        EditText a24 = (EditText) findViewById(R.id.e24);
        EditText a25 = (EditText) findViewById(R.id.e25);
        EditText a26 = (EditText) findViewById(R.id.e26);
        EditText a27 = (EditText) findViewById(R.id.e27);
        a[0][0] = Integer.valueOf(a1.getText().toString());
        a[0][1] = Integer.valueOf(a2.getText().toString());
        a[0][2] = Integer.valueOf(a3.getText().toString());
        a[1][0] = Integer.valueOf(a4.getText().toString());
        a[1][1] = Integer.valueOf(a5.getText().toString());
        a[1][2] = Integer.valueOf(a6.getText().toString());
        a[2][0] = Integer.valueOf(a7.getText().toString());
        a[2][1] = Integer.valueOf(a8.getText().toString());
        a[2][2] = Integer.valueOf(a9.getText().toString());
        a[3][0] = Integer.valueOf(a10.getText().toString());
        a[3][1] = Integer.valueOf(a11.getText().toString());
        a[3][2] = Integer.valueOf(a12.getText().toString());
        b[0][0] = Integer.valueOf(a13.getText().toString());
        b[0][1] = Integer.valueOf(a14.getText().toString());
        b[0][2] = Integer.valueOf(a15.getText().toString());
        b[1][0] = Integer.valueOf(a16.getText().toString());
        b[1][1] = Integer.valueOf(a17.getText().toString());
        b[1][2] = Integer.valueOf(a18.getText().toString());
        b[2][0] = Integer.valueOf(a19.getText().toString());
        b[2][1] = Integer.valueOf(a20.getText().toString());
        b[2][2] = Integer.valueOf(a21.getText().toString());
        b[3][0] = Integer.valueOf(a22.getText().toString());
        b[3][1] = Integer.valueOf(a23.getText().toString());
        b[3][2] = Integer.valueOf(a24.getText().toString());
        c[0] = Integer.valueOf(a25.getText().toString());
        c[1] = Integer.valueOf(a26.getText().toString());
        c[2] = Integer.valueOf(a27.getText().toString());
        TextView myText = (TextView) findViewById(R.id.deadlock1);
        TextView myText1 = (TextView) findViewById(R.id.deadlock2);
        TextView myText2 = (TextView) findViewById(R.id.deadlock3);


        //String sequence = "";
        int n = 4, m = 3;
        int i, j;
        int p[] = new int[4];
        String abc = "";
//        myText.setText("");
       myText1.setText("");
        myText2.setText("");

        for (i = 0; i < n; i++)
            p[i] = 0;

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                need[i][j] = b[i][j] - a[i][j];
            }
        }

        int k, y = 0;
//        int count[] = new int[4];
//        for(i=0;i<n;i++){
//            count[i] = 0;
//        }
        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if ((need[i][j] > c[j]) && (need[i][j] > 0))
                        break;
                }
                if (j == m) {
                    if (p[i] == 0) {
                        p[i] = i + 1;
                        abc += "P";
                        abc += Integer.toString(p[i]);
                        abc += " ";
                    }
                    for (j = 0; j < m; j++) {
                        c[j] += a[i][j];
                        need[i][j] = 0;
                    }
                }
            }
        }
        for (i = 0; i < n; i++) {
            if (p[i] == 0) {
                myText.setText("*UNSAFE STATE*");
                break;
            }
        }
        if (i == n) {
            myText.setText("*SAFE STATE*");
            myText1.setText("Safe Sequence is: -");
            myText2.setText(abc);
        }
    }
}

