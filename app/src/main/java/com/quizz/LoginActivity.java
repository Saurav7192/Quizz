package com.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
   AppCompatButton mAppCompactButton;
    AppCompatButton mLoginButton;

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    TextInputLayout mEmailTextInputLayout;
    TextInputLayout mPasswordTextInputLayout;

    EditText mEmailEditTextView;
    EditText mPasswordEditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mEmailTextInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        mPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);

        mEmailEditTextView = (EditText) findViewById(R.id.emailEditTextView);
        mPasswordEditTextView = (EditText) findViewById(R.id.passwordEditTextView);

        mAppCompactButton = (AppCompatButton) findViewById(R.id.not_a_member_signup_button);
        mAppCompactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

        mLoginButton = (AppCompatButton) findViewById(R.id.Login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailEditTextView.getText().toString();
                String password = mPasswordEditTextView.getText().toString();

                 email = email.trim();
                 password = password.trim();

                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();

                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Sign up failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });

    }

}
