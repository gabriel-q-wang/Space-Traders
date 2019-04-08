package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import cs2340.spacetraders.R;
import cs2340.spacetraders.model.Model;

public class MenuScreen extends AppCompatActivity {
    private Button shipMarketButton;
    private Button shipyardButton;
    private Button shipRepairButton;
    private Button hireCrewButton;
    private Button starMapButton;
    private Button retireButton;
    private Button planetMarketButton;
    private Button saveGameButton;

    /**
     * called when menu screen is viewed
     * @param savedInstanceState the saved instance
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        shipMarketButton = findViewById(R.id.shipMarketButton);
        shipyardButton = findViewById(R.id.shipyardButton);
        shipRepairButton = findViewById(R.id.shipRepairButton);
        hireCrewButton = findViewById(R.id.hireCrewButton);
        starMapButton = findViewById(R.id.starMapButton);
        retireButton = findViewById(R.id.retire_button);
        planetMarketButton = findViewById(R.id.planetMarketButton);
        saveGameButton = findViewById(R.id.saveGame);

        shipMarketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, ShipMarketActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        shipyardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, ShipyardActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        shipRepairButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, ShipRepairActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        hireCrewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, HireCrewActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        starMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, GalaxyMapActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        planetMarketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, MarketScreenActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        saveGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveState();
            }
        });

        retireButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, RetireActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * to save the game state
     */
    private void saveState() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(Model.getInstance());
            out.close();
            Toast.makeText(getApplication(), "Saved Game", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplication(), "Error Reading from Database", Toast.LENGTH_LONG).show();
        }
        String msg = Base64.getEncoder().encodeToString(bos.toByteArray());
        myRef.setValue(msg);
    }
}
