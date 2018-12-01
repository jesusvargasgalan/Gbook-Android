package com.example.jesus.gbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText reg_email_field;
    private EditText reg_pass_field;
    private EditText reg_confirm_pass_field;
    private Button reg_btn;
    private Button reg_login_btn;
    private ProgressBar reg_progress;

    private FirebaseAuth mAuth;
    private FirebaseDatabase fbDB;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();


      reg_email_field =  findViewById(R.id.reg_correo);
      reg_pass_field =  findViewById(R.id.reg_pwd);
      reg_confirm_pass_field =  findViewById(R.id.confirm_pwd);
      reg_btn =  findViewById(R.id.reg_btn);
      reg_login_btn = findViewById(R.id.reg_log_btn);
      reg_progress =  findViewById(R.id.reg_progress);

      reg_login_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              enviarLogin();
          }
      });

      reg_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              final String email = reg_email_field.getText().toString();
              final String pass = reg_pass_field.getText().toString();
              String confirm_pass = reg_confirm_pass_field.getText().toString();

              if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirm_pass)) {

                  if(pass.equals(confirm_pass)){

                      reg_progress.setVisibility(View.VISIBLE);

                      mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Map<String,String> userMap = new HashMap<>();
                                userMap.put("correo",email);
                                userMap.put("contrasenia",pass);

                                mFirestore.collection("Usuarios").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(RegisterActivity.this,"Usuario añadido correctamente",Toast.LENGTH_LONG).show();

                                        sendToMain();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        String error = e.getMessage();

                                        Toast.makeText(RegisterActivity.this,"Error" + error,Toast.LENGTH_LONG).show();
                                    }
                                });


                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this,"Error : " + errorMessage,Toast.LENGTH_LONG).show();

                            }
                              reg_progress.setVisibility(View.INVISIBLE);
                          }
                      });
                  } else{
                      Toast.makeText(RegisterActivity.this,"Los campos contraseña deben coincidir",Toast.LENGTH_LONG).show();

                  }
              }
          }
      });

    }

    private void enviarLogin() {

        Intent volverIntent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(volverIntent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser !=null){
            
            sendToMain();
        }
    }

    private void sendToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
