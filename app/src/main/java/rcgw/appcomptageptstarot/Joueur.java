package rcgw.appcomptageptstarot;

import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Utilisateur on 24/03/2018.
 */

public class Joueur {
    private boolean appelant =false;
    private boolean appele=false;
    private int contrat=0; // pas appelant
    private int score=0;
    private int poignee=0; //1 simple, 2 double, 3 triple, 0 aucune
    private boolean petitAuBout=false;
    private int misere=0; //0 pas de misère, 1 simple, 2 double
    private int chelem=0; //0 no chelem

    //methods to reach Joueur States

    public boolean isAppelant() {
        return appelant;
    }

    public void setAppelant(boolean appelant) {
        this.appelant = appelant;
    }

    public boolean isAppele() {
        return appele;
    }

    public void setAppele(boolean appele) {
        this.appele = appele;
    }

    public int getContrat() {
        return contrat;
    }

    public void setContrat(int contrat) {
        this.contrat = contrat;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPoignee() {
        return poignee;
    }

    public void setPoignee(int poignee) {
        this.poignee = poignee;
    }

    public boolean isPetitAuBout() {
        return petitAuBout;
    }

    public void setPetitAuBout(boolean petitAuBout) {
        this.petitAuBout = petitAuBout;
    }

    public int getMisere() {
        return misere;
    }

    public void setMisere(int misere) {
        this.misere = misere;
    }

    public int getChelem() {
        return chelem;
    }

    public void setChelem(int chelem) {
        this.chelem = chelem;
    }

    public String toString(){
        return ("appele"+ appele + ", appelant" + appelant + ", contrat" + contrat + ", score" + score + ", poignee"+ poignee + ", petitAuBout"+ petitAuBout + ", misère" + misere + ", chelem" + chelem);
    }
}
