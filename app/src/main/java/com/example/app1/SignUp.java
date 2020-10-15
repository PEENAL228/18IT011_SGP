package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText Email,password,repass,uname,rollno;
    Button loginBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.emailid);
        password = findViewById(R.id.password);
        repass = findViewById(R.id.Repassword);
        uname = findViewById(R.id.uName);
        rollno = findViewById(R.id.CollegeId);

        loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String pwd = password.getText().toString();
                String user = uname.getText().toString();
                String roll = rollno.getText().toString();
                String re_pwd = repass.getText().toString();
                if(email.isEmpty()){
                    Email.setError("Please Enter Email-id");
                    Email.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                }
                else if(re_pwd.isEmpty()){
                    repass.setError("Please Enter Password");
                    repass.requestFocus();
                }
                else if(user.isEmpty()){
                    uname.setError("Please Enter User Name");
                    uname.requestFocus();
                }
                else if(roll.isEmpty()){
                    rollno.setError("Please Enter Roll No.");
                    rollno.requestFocus();
                }

                else if(!(pwd.isEmpty() && re_pwd.isEmpty()) && (!pwd.equals(re_pwd))){
                    repass.setError("Please Doesn't match");
                    repass.requestFocus();
                }
                else if (!(email.isEmpty() && pwd.isEmpty() && user.isEmpty() && roll.isEmpty() && re_pwd.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUp.this,"Sign Up UnSuccessful!",Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(SignUp.this,userDashboard.class));
                            }
                        }
                    })
                }
                else{
                    Toast.makeText(SignUp.this,"Error Occured!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
