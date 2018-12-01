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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddVideogameActivity extends AppCompatActivity {

    private EditText nom_vid;
    private EditText gen_vid;
    private EditText plat_vid;
    private EditText syn_vid;
    private EditText fin_vid;
    private Button add_vid;
    private ProgressBar reg_progress;


    private FirebaseAuth mAuth;
    private FirebaseDatabase fbDB;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_videogame);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        nom_vid = findViewById(R.id.nameVideogame);
        gen_vid = findViewById(R.id.genVideogame);
        plat_vid = findViewById(R.id.platVideogame);
        syn_vid = findViewById(R.id.synopsisVideogame);
        fin_vid = findViewById(R.id.finishVideogame);
        add_vid = findViewById(R.id.addVideogame_btn);
        reg_progress = findViewById(R.id.addVid_progress);


        add_vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = nom_vid.getText().toString().trim();
                final String gen = gen_vid.getText().toString().trim();
                final String plat = plat_vid.getText().toString().trim();
                final String synopsis = syn_vid.getText().toString().trim();
                final String finished = fin_vid.getText().toString().trim();



                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(gen) && !TextUtils.isEmpty(synopsis) && !TextUtils.isEmpty(finished)) {

                    reg_progress.setVisibility(View.VISIBLE);


                    Map<String,String> videoGameMap = new HashMap<>();
                    videoGameMap.put("Nombre",name);
                    videoGameMap.put("Genero",gen);
                    videoGameMap.put("Plataforma",plat);
                    videoGameMap.put("Sinopsis",synopsis);
                    videoGameMap.put("Acabado",finished);


                    mFirestore.collection("Videojuegos").add(videoGameMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddVideogameActivity.this,"Videojuego añadido correctamente",Toast.LENGTH_LONG).show();
                            reg_progress.setVisibility(View.INVISIBLE);
                            sendToMain();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();

                            Toast.makeText(AddVideogameActivity.this,"Error" + error,Toast.LENGTH_LONG).show();
                        }
                    });


                }

            }
        });
    }
    private void sendToMain() {

        Intent mainIntent = new Intent(AddVideogameActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }


}
