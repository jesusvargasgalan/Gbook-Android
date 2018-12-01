package com.example.jesus.gbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jesus.gbook.modelos.Videojuego;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public  class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;

    RecyclerView mRecyclerView;
    ArrayList<Videojuego> videojuegoArrayList;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //Variables Recycler View
        mAuth = FirebaseAuth.getInstance();
        videojuegoArrayList = new ArrayList<>();
        setUpRecyclerView();
        setUpFireBase();
        loadDataFromFirebase();


    }

    //Aqui cargo la base de datos
    private void loadDataFromFirebase() {

        db.collection("Videojuegos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot: task.getResult()){
                            Videojuego videojuego = new Videojuego(querySnapshot.getString("Nombre"),
                                    querySnapshot.getString("Genero"),
                                    querySnapshot.getString("Sinopsis"),
                                    querySnapshot.getString("Acabado"),
                                    querySnapshot.getString("Plataforma"));


                            videojuegoArrayList.add(videojuego);
                        }
                        myRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity.this,videojuegoArrayList);
                        mRecyclerView.setAdapter(myRecyclerViewAdapter);
                        registerForContextMenu(mRecyclerView);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Problem",Toast.LENGTH_LONG).show();
                    }
                });

        }

    //Preparo el Recycler view
    private void setUpRecyclerView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    //Instancio la base de datos
    private void setUpFireBase() {

        db = FirebaseFirestore.getInstance();

    }
    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {

            sendToLogin();


        }
    }

    //Ncesario para el menu contextual
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opciones");
        getMenuInflater().inflate(R.menu.contextual_menu, menu);
    }


    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option:
                Toast.makeText(getApplicationContext(), "Borramos: " + myRecyclerViewAdapter.getPosicion() + 1, Toast.LENGTH_SHORT).show();


                videojuegoArrayList.remove(myRecyclerViewAdapter.getPosicion()) ;
                //adaptador.notifyDataSetChanged() ;
                myRecyclerViewAdapter.notifyItemRemoved(myRecyclerViewAdapter.getPosicion()) ;

                return super.onContextItemSelected(item);

            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override

    //Menu que me permite añadir usuario y cerra cesion
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_logout_btn:

                logOut();

                return true;

            case R.id.action_settings_btn:

                Aniadir();

                return true;

            default:
                return false;
        }


    }

    private void logOut() {

        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
    private void addVideogame(){
        Intent addIntent = new Intent(MainActivity.this,AddVideogameActivity.class);
        startActivity(addIntent);
        finish();

    }
    private void Aniadir(){
        addVideogame();
    }
}
