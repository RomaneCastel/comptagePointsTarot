package rcgw.appcomptageptstarot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class compteur extends AppCompatActivity {

    private String[] joueurs;


    public boolean nbrjoueurs (MenuItem item){
        LinearLayout preneur = (LinearLayout) findViewById(R.id.bouttonPreneur);
        for(int i=0; i<joueurs.length; i++){
            Button player = new Button(this);
            player.setText(joueurs[i]);
            player.setId(i);
            preneur.addView(player);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compteur);
        Log.i("lifeCycle","onCreate");
        /*Button validate = (Button) findViewById(R.id.validate); */ //back to the game menu and save data in the menu
        Intent intent = getIntent();//objet spécial pour communiquer des variables d'une activité à une autre
        if(intent!=null){
            joueurs = intent.getStringArrayExtra("joueurs");
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("lifeCycle", "onRestar");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("lifeCycle", "onStop");
    }
}
