package com.example.android.cpuscheduling;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.cpuscheduling.R.layout.add_pp;
import static java.sql.Types.INTEGER;
import static java.sql.Types.NULL;

public class pnp extends AppCompatActivity {

    int count = 0;
    int a[] = new int[10];
    int b[] = new int[10];
    int pr[]= new int[10];
    private TextView myText = null;
    private Button buttonClick;
    private Button deletebutton;
    private Button resetbutton;
    private Button computebutton;
    public Dialog dialog =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnp);
        TextView myText = (TextView) findViewById(R.id.textview1pnp);
        myText.append("P[i]\t\tbt\t\tat\t\tpriority\n\n");
        buttonClick = (Button) findViewById(R.id.buttonClick);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create custom dialog object
                dialog = new Dialog(pnp.this);
                // Include dialog.xml file
                dialog.setContentView(add_pp);
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
                        pr[count]=NULL;

                        if(count!=0) {
                            count--;
                        }
                        Context context = getApplicationContext();
                        CharSequence text = "Last process is deleted";
                        int duration = Toast.LENGTH_SHORT;



                        int i;
                        TextView myText = (TextView) findViewById(R.id.textview1fcfs);
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
                            pr[i]=NULL;
                        }
                        count=0;

                        TextView myText = (TextView) findViewById(R.id.textview1pnp);
                        myText.setText("P[i]\t\tbt\t\tat\t\tpriority\n\n");
                    }
                });

                computebutton = (Button) findViewById(R.id.compute);
                computebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //write code for computing here
                        int i,j,n,time,sum_wait=0,sum_turnaround=0;
                        int smallest,remain;
                        float awt,atat,p,q;
                        remain=count;

                        int wt[]=new int[10];
                        int tat[]=new int[10];

                        pr[9]=11;
                        for(i=0;i<count+1;i++)
                        {

                        }
                        TextView myText = (TextView) findViewById(R.id.textview1pnp);
                        myText.append("\n\nCOMPUTATIONS\n\nProcesses\t\tta time\t\tWa time\n\n");
                        j=0;


                        for(time=0;remain!=0;)
                        {
                            smallest=count-1;
                            int small = 999999999;
                            for(i=0;i<count;i++)
                            {
                                if(a[i]<=time && pr[i]<small && b[i]>0)
                                {
                                    smallest=i;
                                    small=pr[i];
                                }
                            }

                            time+=b[smallest];
                            remain--;
                            wt[smallest]=time-a[smallest]-b[smallest];
                            tat[smallest]=time-a[smallest];
                            myText.append("P["+Integer.toString(smallest+1)+"]\t\t\t"+tat[smallest]+"\t\t\t"+wt[smallest]+"\n\n");
                            j++;
                            b[smallest]=0;
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
        EditText pri = (EditText) dialog.findViewById(R.id.priority);
        // Perform action on click

        b[count] = Integer.valueOf(bt.getText().toString());
        a[count] = Integer.valueOf(at.getText().toString());
        pr[count] = Integer.valueOf(pri.getText().toString());

        TextView myText = (TextView) findViewById(R.id.textview1pnp);
        myText.append("P["+count+"]\t\t"+b[count]+"\t\t"+a[count]+"\t\t"+pr[count]+"\n\n");
        count++;
    }
}
