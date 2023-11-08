package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Second_Sem_scheme2_Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16;

    float a1,a2,a3,a4,a5,a6,a7,a8,a9;
    TextView g1,g2,g3,g4,g5,g6,g7,g8,g9;
    TextView textView;
    Button button;
    float s=0;
    char []gradeLabels={'O','E','A','B','C','D','F'};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sem_scheme2);


        e1=findViewById(R.id.physics1);
        e2=findViewById(R.id.physics2);
        e3=findViewById(R.id.internalmaths1);
        e4=findViewById(R.id.endemmaths1);
        e5=findViewById(R.id.internalsls1);
        e6=findViewById(R.id.endsls1);
        e7=findViewById(R.id.internalEvs1);
        e8=findViewById(R.id.endEvs1);
        e9=findViewById(R.id.internalelective11);
        e10=findViewById(R.id.endelective11);
        e11=findViewById(R.id.internalelective21);
        e12=findViewById(R.id.endelective21);
        e13=findViewById(R.id.endphylab1);
        e14=findViewById(R.id.totalmarkprogramming1);
        e15=findViewById(R.id.totalmarkEgraphics1);

        //fore grade
        g1=findViewById(R.id.physics3);
        g2=findViewById(R.id.grademaths1);
        g3=findViewById(R.id.gradesls1);
        g4=findViewById(R.id.gradeEvs1);
        g5=findViewById(R.id.gradeelective11);
        g6=findViewById(R.id.gradeelective21);
        g7=findViewById(R.id.gradephylab1);
        g8=findViewById(R.id.gradeprogramming1);
        g9=findViewById(R.id.gradeEgraphics1);





        button=findViewById(R.id.btn11);
        textView = findViewById(R.id.textView31);
        String t1=e1.getText().toString();
        String t2=e2.getText().toString();
        String t3=e3.getText().toString();
        String t4=e4.getText().toString();
        String t5=e5.getText().toString();
        String t6=e6.getText().toString();
        String t7=e7.getText().toString();
        String t8=e8.getText().toString();
        String t9=e9.getText().toString();
        String t10=e10.getText().toString();
        String t11=e11.getText().toString();
        String t12=e12.getText().toString();
        String t13=e13.getText().toString();
        String t14=e14.getText().toString();
        String t15=e15.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.isEmpty()&&t2.isEmpty()&&t3.isEmpty()&&t4.isEmpty()&&t5.isEmpty()&&t6.isEmpty()&&t7.isEmpty()&&t8.isEmpty()&&t9.isEmpty()&&t10.isEmpty()&&t11.isEmpty()&&t12.isEmpty()&&t13.isEmpty()&&t14.isEmpty()&&t15.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else {
                    a1 = Integer.parseInt(e1.getText().toString()) + Integer.parseInt(e2.getText().toString());
                    a2 = Integer.parseInt(e3.getText().toString()) + Integer.parseInt(e4.getText().toString());
                    a3 = Integer.parseInt(e5.getText().toString()) + Integer.parseInt(e6.getText().toString());
                    a4 = Integer.parseInt(e7.getText().toString()) + Integer.parseInt(e8.getText().toString());
                    a5 = Integer.parseInt(e9.getText().toString()) + Integer.parseInt(e10.getText().toString());
                    a6 = Integer.parseInt(e11.getText().toString()) + Integer.parseInt(e12.getText().toString());
                    a7 = Integer.parseInt(e13.getText().toString());
                    a8 = Integer.parseInt(e14.getText().toString());
                    a9 = Integer.parseInt(e15.getText().toString());
                    if ((a1 <= 100 && a1 >= 0) && (a2 <= 100 && a2 >= 0) && (a3 <= 100 && a3 >= 0) && (a4 <= 100 && a4 >= 0) && (a5 <= 100 && a5 >= 0) && (a6 <= 100 && a6 >= 0) && (a7 <= 100 && a7 >= 0) && (a8 <= 100 && a8 >= 0) && (a9 <= 100 && a9 >= 0)) {
                        float p1 = a1 * 3;
                        float p2 = a2 * 4;
                        float p3 = a3 * 2;
                        float p4 = a4 * 2;
                        float p5 = a5 * 2;
                        float p6 = a6 * 2;
                        float p7 = a7 * 1;
                        float p8 = a8 * 4;
                        float p9 = a9 * 1;
                        float total = (p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9) / 10;
                        float ss = total / 21;
                        textView.setText(String.valueOf(ss));

                        g1.setText(String.valueOf(calculateGrade(a1)));
                        g2.setText(String.valueOf(calculateGrade(a2)));
                        g3.setText(String.valueOf(calculateGrade(a3)));
                        g4.setText(String.valueOf(calculateGrade(a4)));
                        g5.setText(String.valueOf(calculateGrade(a5)));
                        g6.setText(String.valueOf(calculateGrade(a6)));
                        g7.setText(String.valueOf(calculateGrade(a7)));
                        g8.setText(String.valueOf(calculateGrade(a8)));
                        g9.setText(String.valueOf(calculateGrade(a9)));
                    } else
                        Toast.makeText(Second_Sem_scheme2_Activity.this, "Please provide correct details!!", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    private char calculateGrade(float totalMarks) {
        if (totalMarks >= 90 && totalMarks <= 100) {
            return gradeLabels[0]; //O
        } else if (totalMarks >= 80 && totalMarks < 90) {
            return gradeLabels[1]; //E
        } else if (totalMarks >= 70 && totalMarks < 80) {
            return gradeLabels[2]; // A
        } else if (totalMarks >= 60 && totalMarks < 70) {
            return gradeLabels[3]; // B
        } else if (totalMarks >= 50 && totalMarks < 60) {
            return gradeLabels[4]; // C
        } else if (totalMarks >= 40 && totalMarks < 50) {
            return gradeLabels[5]; // D
        }
        return gradeLabels[6]; // F
    }
}