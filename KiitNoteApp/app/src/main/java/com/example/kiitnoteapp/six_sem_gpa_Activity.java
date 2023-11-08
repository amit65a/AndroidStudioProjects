package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class six_sem_gpa_Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16;

    float a1,a2,a3,a4,a5,a6,a7,a8,a9;
    TextView textView;
    TextView g1,g2,g3,g4,g5,g6,g7,g8,g9;
    Button button;
    float s=0;

    char []gradeLabels={'O','E','A','B','C','D','F'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_sem_gpa);


        e1=findViewById(R.id.internalML);
        e2=findViewById(R.id.endML);
        e3=findViewById(R.id.internalAI);
        e4=findViewById(R.id.endAI);
        e5=findViewById(R.id.internalhumanvalues);
        e6=findViewById(R.id.endhumanvalues);
        e7=findViewById(R.id.internalelectivesix1);
        e8=findViewById(R.id.endelectivesix1);
        e9=findViewById(R.id.internalelectivesixII);
        e10=findViewById(R.id.endelectivesixII);
        e11=findViewById(R.id.internalelectivesixIII);
        e12=findViewById(R.id.endelectivesixIII);
        e13=findViewById(R.id.TotalmarksAIlab);
        e14=findViewById(R.id.totalADPlab);
        e15=findViewById(R.id.totalproject);


        // for grade

        g1=findViewById(R.id.gradeML);
        g2=findViewById(R.id.gradeAI);
        g3=findViewById(R.id.gradehumancalues);
        g4=findViewById(R.id.gradeelectivesix1);
        g5=findViewById(R.id.gradeElectivesixII);
        g6=findViewById(R.id.gradeelectivesixIII);
        g7=findViewById(R.id.gradeAIlab);
        g8=findViewById(R.id.gradeADPlab);
        g9=findViewById(R.id.gradeproject);




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

        button=findViewById(R.id.btn21);
        textView = findViewById(R.id.textView23);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(t1.isEmpty()&&t2.isEmpty()&&t3.isEmpty()&&t4.isEmpty()&&t5.isEmpty()&&t6.isEmpty()&&t7.isEmpty()&&t8.isEmpty()&&t9.isEmpty()&&t10.isEmpty()&&t11.isEmpty()&&t12.isEmpty()&&t13.isEmpty()&&t14.isEmpty()&&t15.isEmpty()){
//                    Toast.makeText(getApplicationContext(),"please fill all the details",Toast.LENGTH_SHORT).show();
//                }
//else {
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
                        float p1 = a1 * 4;
                        float p2 = a2 * 3;
                        float p3 = a3 * 3;
                        float p4 = a4 * 3;
                        float p5 = a5 * 3;
                        float p6 = a6 * 3;
                        float p7 = a7 * 1;
                        float p8 = a8 * 2;
                        float p9 = a9 * 2;
                        float total = (p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9) / 10;
                        float ss = total / 24;
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
                   }
                    else
                        Toast.makeText(six_sem_gpa_Activity.this, "Please provide correct details!!", Toast.LENGTH_SHORT).show();


//                }
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

