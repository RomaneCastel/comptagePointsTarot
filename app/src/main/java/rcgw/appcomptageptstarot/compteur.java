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
import rcgw.appcomptageptstarot.Joueur;

/*READ ME:
    ID : joueur appelant : 10*i
         joueur appelé : 100*i
 */
public class compteur extends AppCompatActivity {

    private String[] joueurs;
    private Joueur[] etatJoueurs;

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


    //création des boutons joueurs preneurs, un seul peut être selectionné
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

    //création des buttons joueurs appelé, un seul peut être sélectionné
    public void joueursAppelé (){
        LinearLayout appele = (LinearLayout) findViewById(R.id.bouttonAppele);
        for(int i=0; i<joueurs.length; i++) {
            ToggleButton player = new ToggleButton(this);
            player.setText(joueurs[i]);
            player.setOnCheckedChangeListener(changeChecker);
            appele.addView(player);
            player.setId(10*i);
            player.setTextOn(joueurs[i]);
            player.setTextOff(joueurs[i]);
        }
    }

    //création des buttons de joueurs ayant une misère
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
        Log.i("lifeCycle", "onCreate");
        Intent intent = getIntent();//objet spécial pour communiquer des variables d'une activité à une autre
        if (intent != null) {
            joueurs = intent.getStringArrayExtra("joueurs");
        }
        nbrjoueurs();
        if (joueurs.length == 5) {
            joueursAppelé();
            TextView joueurAppele = (TextView) findViewById(R.id.Appele);
            joueurAppele.setVisibility(View.VISIBLE);
        }

        //tableau d'état des joueurs
        etatJoueurs = new Joueur[joueurs.length];
        for(int i=0; i<joueurs.length; i++){
            etatJoueurs[i]= new Joueur();
        }

        //part scores
        SeekBar scores = (SeekBar) findViewById(R.id.scores);
        TextView attaqueScore = (TextView) findViewById(R.id.attaqueScore);
        TextView defenseScore = (TextView) findViewById(R.id.defenseScore);
        TextView attaqueScoreInt = (TextView) findViewById(R.id.attaqueScoreInt);
        TextView defenseScoreInt = (TextView) findViewById(R.id.defenseScoreInt);
        attaqueScore.setText("attaque :");
        defenseScore.setText("défense :");
        attaqueScoreInt.setText("45");
        defenseScoreInt.setText("46");
        scores.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekBarValue = seekBar.getProgress(); // get progress value from the Seek bar
                TextView attaqueScoreInt = (TextView) findViewById(R.id.attaqueScoreInt);
                TextView defenseScoreInt = (TextView) findViewById(R.id.defenseScoreInt);
                attaqueScoreInt.setText(seekBarValue + "");
                defenseScoreInt.setText((91 - seekBarValue)+ "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //part contrat
        ToggleButton petite = (ToggleButton) findViewById(R.id.petite);
        ToggleButton garde = (ToggleButton) findViewById(R.id.garde);
        ToggleButton gardeSans = (ToggleButton) findViewById(R.id.gardeSans);
        ToggleButton gardeContre = (ToggleButton) findViewById(R.id.gardeContre);
        petite.setOnCheckedChangeListener(changeChecker);
        garde.setOnCheckedChangeListener(changeChecker);
        gardeSans.setOnCheckedChangeListener(changeChecker);
        gardeContre.setOnCheckedChangeListener(changeChecker);

        //part chelem
        ToggleButton seeChelem = (ToggleButton) findViewById(R.id.seeChelem);
        seeChelem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout chelemShow = (LinearLayout) findViewById(R.id.chelemShow);
                if (isChecked) {
                    chelemShow.setVisibility(View.VISIBLE);
                } else {
                    chelemShow.setVisibility(View.GONE);
                }
            }
        });
        ToggleButton chelemSuccess = (ToggleButton) findViewById(R.id.chelemSuccess);
        ToggleButton chelemNotAnnounced = (ToggleButton) findViewById(R.id.notAnnoncedChelem);
        ToggleButton fallen = (ToggleButton) findViewById(R.id.fallen);
        ToggleButton adversarySuccess = (ToggleButton) findViewById(R.id.adversarySuccess);
        chelemSuccess.setOnCheckedChangeListener(changeChecker);
        chelemNotAnnounced.setOnCheckedChangeListener(changeChecker);
        fallen.setOnCheckedChangeListener(changeChecker);
        adversarySuccess.setOnCheckedChangeListener(changeChecker);

        //part petit au bout
        ToggleButton attaquePetit = (ToggleButton) findViewById(R.id.attaquePetit);
        ToggleButton defensePetit = (ToggleButton) findViewById(R.id.defensePetit);
        attaquePetit.setOnCheckedChangeListener(changeChecker);
        defensePetit.setOnCheckedChangeListener(changeChecker);
        ToggleButton seePetitAuBout = (ToggleButton) findViewById(R.id.petitAuBoutShow);
        seePetitAuBout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout petitAuBoutShow = (LinearLayout) findViewById(R.id.seePetitAuBout);
                if (isChecked) {
                    petitAuBoutShow.setVisibility(View.VISIBLE);
                } else {
                    petitAuBoutShow.setVisibility(View.GONE);
                }
            }
        });

        //part misere
        ToggleButton seeMisere = (ToggleButton) findViewById(R.id.seeMisere);
        seeMisere.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout misereShow = (LinearLayout) findViewById(R.id.misereShow);
                if (isChecked) {
                    misereShow.setVisibility(View.VISIBLE);
                } else {
                    misereShow.setVisibility(View.GONE);
                }
            }
        });
        LinearLayout simpleMisere = (LinearLayout) findViewById(R.id.buttonMisereSimple);
        LinearLayout doubleMisere = (LinearLayout) findViewById(R.id.buttonMisereDouble);
        joueursMisere(simpleMisere);
        joueursMisere(doubleMisere);

        //part poignee
        //set OnCheckedChangeListener for poignees
        LinearLayout poigneeShow = (LinearLayout) findViewById(R.id.poigneeShow);
        //iterate through simple, double and triple
        for (int i = 0; i < poigneeShow.getChildCount(); i++) {
            LinearLayout poignee = (LinearLayout) poigneeShow.getChildAt(i);
            //iterate through attaque, defense
            for (int j = 1; j < poignee.getChildCount(); j++) {
                ((ToggleButton) poignee.getChildAt(j)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            int j = ((LinearLayout) compoundButton.getParent()).indexOfChild(compoundButton);
                            LinearLayout poigneeShow = (LinearLayout) findViewById(R.id.poigneeShow);
                            for (int i = 0; i < poigneeShow.getChildCount(); i++) {
                                ((ToggleButton) ((LinearLayout) poigneeShow.getChildAt(i)).getChildAt(j)).setChecked(false);
                            }
                            compoundButton.setChecked(true);
                        }
                    }
                });
            }
        }
        ToggleButton seePoignee = (ToggleButton) findViewById(R.id.seePoignee);
        seePoignee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout poigneeShow = (LinearLayout) findViewById(R.id.poigneeShow);
                if (isChecked) {
                    poigneeShow.setVisibility(View.VISIBLE);
                } else {
                    poigneeShow.setVisibility(View.GONE);
                }
            }
        });

        //part validate button
        ToggleButton validate = (ToggleButton) findViewById(R.id.validate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first, complete array joueurState
                for (int i = 0; i < joueurs.length; i++) {

                    //joueurs appelé et appelant
                    ToggleButton appelant = (ToggleButton) findViewById(i);
                    ToggleButton appele = (ToggleButton) findViewById(10* i);
                    if (appelant.isChecked()) {
                        etatJoueurs[i].setAppelant(true);
                    } else {
                        etatJoueurs[i].setAppelant(false);
                    }
                    if (appele.isChecked()) {
                        etatJoueurs[i].setAppele(true);
                    } else {
                        etatJoueurs[i].setAppele(false);
                    }

                    //contrat
                    ToggleButton petite = (ToggleButton) findViewById(R.id.petite);
                    ToggleButton garde = (ToggleButton) findViewById(R.id.garde);
                    ToggleButton gardeSans = (ToggleButton) findViewById(R.id.gardeSans);
                    ToggleButton gardeContre = (ToggleButton) findViewById(R.id.gardeContre);
                    if(appele.isChecked()) {
                            if (petite.isChecked()) {
                                etatJoueurs[i].setContrat(1);
                            }
                            if (garde.isChecked()) {
                                etatJoueurs[i].setContrat(2);
                            }
                            if (gardeSans.isChecked()) {
                                etatJoueurs[i].setContrat(3);
                            }
                            if (gardeContre.isChecked()) {
                                etatJoueurs[i].setContrat(4);
                            }
                    }
                    else{
                        etatJoueurs[i].setContrat(0);
                    }
                    //scores
                    TextView attaqueScoreInt = (TextView) findViewById(R.id.attaqueScoreInt);
                    int attaqueScoreIntGet=Integer.parseInt(attaqueScoreInt.getText().toString());
                    TextView defenseScoreInt = (TextView) findViewById(R.id.defenseScoreInt);
                    int defenseScoreIntGet=Integer.parseInt(defenseScoreInt.getText().toString());
                    if(etatJoueurs[i].isAppele() || etatJoueurs[i].isAppelant()){
                        etatJoueurs[i].setScore(attaqueScoreIntGet);
                    }
                    else{
                        etatJoueurs[i].setScore(defenseScoreIntGet);
                    }

                    //poignees
                    ToggleButton attaqueSimple = (ToggleButton) findViewById(R.id.attaqueSimple);
                    ToggleButton attaqueDouble = (ToggleButton) findViewById(R.id.attaqueDouble);
                    ToggleButton attaqueTriple = (ToggleButton) findViewById(R.id.attaqueTriple);
                    ToggleButton defenseSimple = (ToggleButton) findViewById(R.id.defenseSimple);
                    ToggleButton defenseDouble = (ToggleButton) findViewById(R.id.defenseDouble);
                    ToggleButton defenseTriple = (ToggleButton) findViewById(R.id.defenseTriple);
                    if(appele.isChecked() || appelant.isChecked()){
                        if(attaqueSimple.isChecked()){
                            etatJoueurs[i].setPoignee(1);
                        }
                        else if(attaqueDouble.isChecked()){
                            etatJoueurs[i].setPoignee(2);
                        }
                        else if(attaqueTriple.isChecked()){
                            etatJoueurs[i].setPoignee(3);
                        }
                        else{
                            etatJoueurs[i].setPoignee(0);
                        }
                    }
                    else{
                        if(defenseSimple.isChecked()){
                            etatJoueurs[i].setPoignee(1);
                        }
                        else if(defenseDouble.isChecked()){
                            etatJoueurs[i].setPoignee(2);
                        }
                        else if(defenseTriple.isChecked()){
                            etatJoueurs[i].setPoignee(3);
                        }
                        else{
                            etatJoueurs[i].setPoignee(0);
                        }
                    }

                    //petitAuBout
                    ToggleButton attaquePetit = (ToggleButton) findViewById(R.id.attaquePetit);
                    ToggleButton defensePetit = (ToggleButton) findViewById(R.id.defensePetit);
                    if(appele.isChecked() || appelant.isChecked()) {
                        if (attaquePetit.isChecked()) {
                            etatJoueurs[i].setPetitAuBout(true);
                        }
                        else{
                            etatJoueurs[i].setPetitAuBout(false);
                        }
                    }
                    else{
                        if (defensePetit.isChecked()) {
                            etatJoueurs[i].setPetitAuBout(true);
                        }
                        else{
                            etatJoueurs[i].setPetitAuBout(false);
                        }
                    }

                    //misere
                    Log.i("tableau", etatJoueurs[i].toString());
                }
            }
        });
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
