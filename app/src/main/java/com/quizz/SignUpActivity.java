package com.quizz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout mNameTextInputLayout;
     EditText mNameEditText;

    TextInputLayout mEmailTextInputLayout;
    EditText mEmailEditText;

    TextInputLayout mPasswordTextInputLayout;
    EditText mPasswordEditText;

    AppCompatButton mAppCompactButton;

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

         mNameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);
         mNameEditText = (EditText) findViewById(R.id.nameEditText);

         mEmailTextInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
         mEmailEditText = (EditText) findViewById(R.id.emailEditTextView);

         mPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditTextView);

        mAppCompactButton = (AppCompatButton) findViewById(R.id.registerAppCompactButton);

        mAppCompactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(SignUpActivity.this,"Sign up failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
