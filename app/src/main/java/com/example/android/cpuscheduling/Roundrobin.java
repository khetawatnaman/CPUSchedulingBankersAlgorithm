package com.example.android.cpuscheduling;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.cpuscheduling.R.layout.add_fcfs;
import static java.sql.Types.NULL;

public class Roundrobin extends AppCompatActivity {
    int count = 0;
    int timequantum;
    int a[] = new int[10];
    int b[] = new int[10];
    private TextView myText = null;
    private Button buttonClick;
    private Button deletebutton;
    private Button resetbutton;
    private Button computebutton;
    public Dialog dialog =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roundrobin);
        TextView myText = (TextView) findViewById(R.id.textview1rr);
        myText.append("P[i]\t\tbt\t\tat\n\n");
        buttonClick = (Button) findViewById(R.id.buttonClick);
        EditText et = (EditText) findViewById(R.id.editText);
       try{ timequantum = Integer.valueOf(et.getText().toString());}
       catch(NumberFormatException e){

       }
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create custom dialog object
                dialog = new Dialog(Roundrobin.this);
                // Include dialog.xml file
                dialog.setContentView(add_fcfs);
                // Set dialog title
                dialog.setTitle("Custom Dialog");
                // set values for custom dialog components - text, image and button
                dialog.show();
                Button save = (Button) dialog.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        check();
                        dialog.dismiss();
                    }
                });
                deletebutton = (Button) findViewById(R.id.delete);
                deletebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a[count]=NULL;
                        b[count]=NULL;

                        if(count!=0) {
                            count--;
                        }
                        Context context = getApplicationContext();
                        CharSequence text = "Last process is deleted";
                        int duration = Toast.LENGTH_SHORT;



                        int i;
                        TextView myText = (TextView) findViewById(R.id.textview1rr);
                        myText.setText("");
                        myText.append("P[i]\t\tbt\t\tat\n\n");
                        for(i=0;i<count;i++)
                        {
                            myText.append("P["+i+"]\t\t"+b[i]+"\t\t"+a[i]+"\n\n");
                        }


                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
                resetbutton = (Button) findViewById(R.id.reset);
                resetbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int i;
                        for(i=0;i<count;i++){
                            a[i]=NULL;
                            b[i]=NULL;
                        }
                        count=0;

                        TextView myText = (TextView) findViewById(R.id.textview1rr);
                        myText.setText("P[i]\t\tbt\t\tat\n"+"\n");
                    }
                });

                computebutton = (Button) findViewById(R.id.compute);
                computebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //write code for computing here
                        int i,j,time,remain,flag=0;
                        float awt,atat,p,q;
                        int wt[]=new int[10];
                        int tat[]=new int[10];
                        int rt[]=new int[10];
                        remain=count;
                        TextView myText = (TextView) findViewById(R.id.textview1rr);
                        for(i=0;i<count;i++)
                        {
                            rt[i]=b[i];
                        }

                        for(time=0,i=0;remain!=0;)
                        {
                            if (rt[i] <= timequantum && rt[i] > 0) {
                                time += rt[i];
                                rt[i] = 0;
                                flag = 1;
                            } else if (rt[i] > 0) {
                                rt[i] -= timequantum;
                                time += timequantum;
                            }
                            if (rt[i] == 0 && flag == 1) {
                                remain--;
                                wt[i] = time - a[i] - b[i];
                                tat[i] = time - a[i];
                                myText.append("P["+Integer.toString(i+1)+"]\t\t" + wt[i] + "\t\t" + tat[i] + "\n\n");
                                flag = 0;
                            }
                            if (i == count - 1)
                                i = 0;
                            else if (a[i + 1] <= time)
                                i++;
                            else
                                i = 0;
                        }

                        p=0;
                        q=0;
                        for(i=0;i<count;i++)
                        {
                            p+=wt[i];
                        }
                        awt=p/count;
                        for(i=0;i<count;i++)
                        {
                            q+=tat[i];
                        }
                        atat=q/count;
                        myText.append("Average Waiting Time is "+awt+"\n\n"+"Average Turnaround Time is "+atat);
                    }
                });

            }
        });

    }


    public void check() {
        EditText at = (EditText) dialog.findViewById(R.id.at);
        EditText bt = (EditText) dialog.findViewById(R.id.bt);
        b[count] = Integer.valueOf(bt.getText().toString());
        a[count] = Integer.valueOf(at.getText().toString());

        TextView myText = (TextView) findViewById(R.id.textview1rr);
        myText.append("P["+count+"]\t\t"+b[count]+"\t\t"+a[count]+"\n\n");
        count++;
    }
}
