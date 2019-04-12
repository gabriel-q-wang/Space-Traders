package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.spacetraders.R;

/**
 * HireCrewActivity runs the actions relevant
 * to the HireCrew UI
 */
public class HireCrewActivity extends AppCompatActivity {

    /** Called when player wants to hire crew
     * @param savedInstanceState the saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hire_crew_screen);

        FloatingActionButton menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireCrewActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
