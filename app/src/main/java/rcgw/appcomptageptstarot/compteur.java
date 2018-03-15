package rcgw.appcomptageptstarot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class compteur extends AppCompatActivity {

    private String[] joueurs;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton button, boolean isChecked) {

            }
    };

    public void nbrjoueurs (){
        LinearLayout preneur = (LinearLayout) findViewById(R.id.bouttonPreneur);
        for(int i=0; i<joueurs.length; i++) {
            ToggleButton player = new ToggleButton(this);
            player.setText(joueurs[i]);
            player.setId(i);
            preneur.addView(player);
            player.setId(i);
            player.setOnCheckedChangeListener(onCheckedChangeListener);
        }
    }

    public void joueursAppelé (){
        LinearLayout appele = (LinearLayout) findViewById(R.id.bouttonAppele);
        for(int i=0; i<joueurs.length; i++) {
            ToggleButton player = new ToggleButton(this);
            player.setText(joueurs[i]);
            player.setId(i);
            appele.addView(player);
            player.setId(i+joueurs.length);
        }
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
        nbrjoueurs();
        joueursAppelé();

    }


    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("lifeCycle", "onRestart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("lifeCycle", "onStop");
    }
}
