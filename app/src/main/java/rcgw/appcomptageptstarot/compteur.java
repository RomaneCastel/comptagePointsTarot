package rcgw.appcomptageptstarot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class compteur extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //affichage menu dans l'activité
        getMenuInflater().inflate(R.menu.menu_compteur, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compteur);
        Log.i("lifeCycle","onCreate");
        /*Button validate = (Button) findViewById(R.id.validate); */ //back to the game menu and save data in the menu

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
