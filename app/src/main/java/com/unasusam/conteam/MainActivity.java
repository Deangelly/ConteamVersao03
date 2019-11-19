package com.unasusam.conteam;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Apresentacao> list = new ArrayList<>();
    List<Apresentacao> resumos = new ArrayList<>();

    DatabaseReference reff;
    FirebaseFirestore db;
    FirebaseStorage st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reff = FirebaseDatabase.getInstance().getReference("resumes");
        db = FirebaseFirestore.getInstance();
        st = FirebaseStorage.getInstance();


       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        recyclerView = findViewById(R.id.recycler);
        mAdapter = new MyAdapter(getApplicationContext(), this,st, MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setDados(Apresentacao apresentacao) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        ((MyAdapter) mAdapter).setDado(apresentacao);
    }
   /* public void populate(){
        list.add(new Apresentacao("Deangelly Coitinho Figueiredo","Aplicação Android",null));
        list.add(new Apresentacao("Danny Coutinho Figueiredo","Fisioterapia",null));
        list.add(new Apresentacao("Deanny Coutinho Figueiredo","Pediatria",null));
        list.add(new Apresentacao("Dean Pimentel de Figueiredo","Fazer bolo",null));
        list.add(new Apresentacao("Valdenize Monteiro Coitinho","Fazer bolo/2",null));
        list.add(new Apresentacao("Tassiane Oliveira Picanço","Autônoma",null));

    }*/
   /*public void getResumos(){
       reff = FirebaseDatabase.getInstance().getReference().child("resumes");


       ValueEventListener valueEventListener = new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot ds : dataSnapshot.getChildren()) {
                   String nome = ds.child("name").getValue(String.class);
                   String title = ds.child("title").getValue(String.class);
                   Log.d("###", nome + " / " + title);
                   resumos.add(new Apresentacao(nome,title,null));
               }
               setDados(resumos);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Log.d("###", databaseError.getMessage());
           }
       };
       newsRef.addListenerForSingleValueEvent(valueEventListener);
   }*/

    @Override
    protected void onStart() {
        super.onStart();


/*

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot resumosSnapshot : dataSnapshot.getChildren()){

                    collectApresentationData((Map<String,Object>) dataSnapshot.getValue());
                }
                setDados(resumos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        list.clear();
        ((MyAdapter) mAdapter).clearList();

        db.collection("resumes")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Apresentacao apresentacao = new Apresentacao((String)document.getData().get("name"),(String)document.getData().get("title"),(String)document.getId());
                        list.add(apresentacao);
                        Log.d("###", document.getId() + " => " + document.getData());
                    }

                    setImagesApresentation(list);

                } else {
                    Log.w("###", "Error getting documents.", task.getException());
                }
            }
        });


    }

  /*  private void collectApresentationData(Map<String,Object> users) {

        ArrayList<Apresentacao> apresentacoes = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            apresentacoes.add(new Apresentacao((String) singleUser.get("nome"),(String) singleUser.get("title")));
        }
        resumos.addAll(apresentacoes);


    }*/

    public void setImagesApresentation(List<Apresentacao> lista){
        for (final Apresentacao apresentacao : lista) {

            st.getReference().child(apresentacao.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    apresentacao.setFoto_apresentador(uri);
                    setDados(apresentacao);


                }

            });

        }

    }
}
