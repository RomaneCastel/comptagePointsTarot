package rcgw.appcomptageptstarot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class compteur extends AppCompatActivity {

    private String[] joueurs;
    private int joueur1Score;
    private int joueur2Score;
    private int joueur3Score;
    private int joueur4Score;
    private int joueur5Score;

    /*private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton button, boolean isChecked) {

            }
    };*/

    private CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                LinearLayout contrat = (LinearLayout) buttonView.getParent();
                for(int i=0; i<contrat.getChildCount(); i++) {
                    if (contrat.getChildAt(i) instanceof ToggleButton) {
                        ((ToggleButton) contrat.getChildAt(i)).setChecked(false);
                    }
                }
                buttonView.setChecked(true);
            }
        }
    };

   /* private CompoundButton.OnCheckedChangeListener changeCheckerPoignee = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                LinearLayout poignee = (LinearLayout) buttonView.getParent();
                for(int i=0; i<3; i++){
                    ((ToggleButton) ((LinearLayout) (poignee.getChildAt(i))).getChildAt(0)).setChecked(false);
                }
                buttonView.setChecked(true);
                Log.i("oncheck", "utilise onchekpoignee");
            }
        }
    };*/


    public void nbrjoueurs (){
        LinearLayout preneur = (LinearLayout) findViewById(R.id.bouttonPreneur);
        for(int i=0; i<joueurs.length; i++) {
            ToggleButton player = new ToggleButton(this);
            player.setText(joueurs[i]);
            player.setId(i);
            preneur.addView(player);
            player.setId(i);
            player.setOnCheckedChangeListener(changeChecker);
            player.setTextOn(joueurs[i]);
            player.setTextOff(joueurs[i]);
        }
    }

    public void joueursAppelé (){
        LinearLayout appele = (LinearLayout) findViewById(R.id.bouttonAppele);
        for(int i=0; i<joueurs.length; i++) {
            ToggleButton player = new ToggleButton(this);
            player.setText(joueurs[i]);
            player.setOnCheckedChangeListener(changeChecker);
            appele.addView(player);
            player.setId(i+joueurs.length);
            player.setTextOn(joueurs[i]);
            player.setTextOff(joueurs[i]);
        }
    }

    public void joueursMisere (LinearLayout layout){
        for(int i=0; i<joueurs.length; i++) {
            ToggleButton player = new ToggleButton(this);
            player.setText(joueurs[i]);
            player.setId(i);
            layout.addView(player);
            player.setId(i+joueurs.length);
            player.setTextOn(joueurs[i]);
            player.setTextOff(joueurs[i]);
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
        SeekBar scores = (SeekBar) findViewById(R.id.scores);
        TextView attaqueScore = (TextView) findViewById(R.id.attaqueScore);
        TextView defenseScore = (TextView) findViewById(R.id.defenseScore);
        attaqueScore.setText("attaque :" + 45);
        defenseScore.setText("défense :" +46);
        scores.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekBarValue= seekBar.getProgress(); // get progress value from the Seek bar
                TextView attaqueScore = (TextView) findViewById(R.id.attaqueScore);
                TextView defenseScore = (TextView) findViewById(R.id.defenseScore);
                attaqueScore.setText("attaque :" + seekBarValue);
                defenseScore.setText("défense :" + (91-seekBarValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ToggleButton petite = (ToggleButton) findViewById(R.id.petite);
        ToggleButton garde = (ToggleButton) findViewById(R.id.garde);
        ToggleButton gardeSans = (ToggleButton) findViewById(R.id.gardeSans);
        ToggleButton gardeContre = (ToggleButton) findViewById(R.id.gardeContre);
        petite.setOnCheckedChangeListener(changeChecker);
        garde.setOnCheckedChangeListener(changeChecker);
        gardeSans.setOnCheckedChangeListener(changeChecker);
        gardeContre.setOnCheckedChangeListener(changeChecker);
        ToggleButton seePoignee = (ToggleButton) findViewById(R.id.seePoignee);
        seePoignee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout poigneeShow = (LinearLayout) findViewById(R.id.poigneeShow);
                if(isChecked){
                    poigneeShow.setVisibility(View.VISIBLE);
                }
                else{
                    poigneeShow.setVisibility(View.GONE);
                }
            }
        });
        ToggleButton attaquePetit = (ToggleButton) findViewById(R.id.attaquePetit);
        ToggleButton defensePetit = (ToggleButton) findViewById(R.id.defensePetit);
        attaquePetit.setOnCheckedChangeListener(changeChecker);
        defensePetit.setOnCheckedChangeListener(changeChecker);
        ToggleButton seeMisere = (ToggleButton) findViewById(R.id.seeMisere);
        seeMisere.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout misereShow = (LinearLayout) findViewById(R.id.misereShow);
                if(isChecked){
                    misereShow.setVisibility(View.VISIBLE);
                }
                else{
                    misereShow.setVisibility(View.GONE);
                }
            }
        });
        /*ToggleButton attaqueSimple = (ToggleButton) findViewById(R.id.attaqueSimple);
        ToggleButton attaqueDouble = (ToggleButton) findViewById(R.id.attaqueDouble);
        ToggleButton attaqueTriple = (ToggleButton) findViewById(R.id.attaqueTriple);
        ToggleButton defenseSimple = (ToggleButton) findViewById(R.id.defenseSimple);
        ToggleButton defenseDouble = (ToggleButton) findViewById(R.id.defenseDouble);
        ToggleButton defenseTriple = (ToggleButton) findViewById(R.id.defenseTriple);
        attaqueSimple.setOnCheckedChangeListener(changeCheckerPoignee);
        attaqueDouble.setOnCheckedChangeListener(changeCheckerPoignee);
        attaqueTriple.setOnCheckedChangeListener(changeCheckerPoignee);
        defenseSimple.setOnCheckedChangeListener(changeCheckerPoignee);
        defenseDouble.setOnCheckedChangeListener(changeCheckerPoignee);
        defenseTriple.setOnCheckedChangeListener(changeCheckerPoignee);*/
        LinearLayout simpleMisere = (LinearLayout) findViewById(R.id.buttonMisereSimple);
        LinearLayout doubleMisere = (LinearLayout) findViewById(R.id.buttonMisereDouble);
        joueursMisere(simpleMisere);
        joueursMisere(doubleMisere);
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
