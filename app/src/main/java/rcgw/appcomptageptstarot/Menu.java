package rcgw.appcomptageptstarot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button addPlayerBtn = findViewById(R.id.addPlayer);
        addPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout playersLayout = findViewById(R.id.players);
                int playerNb = playersLayout.getChildCount();
                if (playerNb >= 6){
                    Toast.makeText(getApplicationContext(), "5 joueurs max", Toast.LENGTH_SHORT).show();
                    return;
                }
                LinearLayout onePlayer = new LinearLayout(getApplicationContext());
                onePlayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                TextView number = new TextView(getApplicationContext());
                number.setText(getResources().getString(R.string.player) + " " + playerNb);
                EditText name = new EditText(getApplicationContext());
                name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                onePlayer.addView(number);
                onePlayer.addView(name);
                playersLayout.addView(onePlayer, playerNb-1);
            }
        });

        Button go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout playersLayout = findViewById(R.id.players);
                int playerNb = playersLayout.getChildCount();
                String[] names = new String[playerNb-1];
                for (int i=0; i<playerNb-1;i++){
                    LinearLayout onePlayer = (LinearLayout) playersLayout.getChildAt(i);
                    names[i] = ((EditText) onePlayer.getChildAt(1)).getText().toString();
                }
                Intent intent = new Intent(Menu.this, compteur.class);
                intent.putExtra("joueurs", names);
                startActivity(intent);
            }
        });
    }
}
