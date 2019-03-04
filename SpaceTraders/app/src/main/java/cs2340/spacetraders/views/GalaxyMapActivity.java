package cs2340.spacetraders.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;

import java.util.List;
import java.util.Map;
import java.util.Random;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.CelestialName;
import cs2340.spacetraders.entity.Galaxy;
import cs2340.spacetraders.entity.Planet;
import cs2340.spacetraders.entity.SolarSystem;
import cs2340.spacetraders.model.Model;

public class GalaxyMapActivity extends AppCompatActivity {

    int[] smallStars = {
            R.drawable.blue_s,
            R.drawable.red_s,
            R.drawable.yellow_s,
            R.drawable.white_s
    };

    int[] mediumStars = {
            R.drawable.blue_m,
            R.drawable.red_m,
            R.drawable.yellow_m,
            R.drawable.white_m
    };

    int[] largeStars = {
            R.drawable.blue_l,
            R.drawable.red_l,
            R.drawable.yellow_l,
            R.drawable.white_l
    };

    /** Called when the application starts. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_map);

//        Map <String, Planet> wholePlanetList = Model.getInstance().getGame().getGalaxy().getWholePlanetList();
        List<SolarSystem> solarSystemList = Model.getInstance().getGame().getGalaxy().getSolarSystemList();
//        solarSystemList.get(0).getPlanetList();

        //19 solar systems
//        Random generator = new Random();
//        for (int i = 0; i < 19; i++) {
//            ImageButton star = (ImageButton) findViewById(R.id.star);
//            if (starsmall){
//                int randomSmallStar = smallStars[generator.nextInt(smallStars.length)];
//                star.setImageResource(randomSmallStar);
//            } else if (star.getS){
//                int randomMediumStar = mediumStars[generator.nextInt(mediumStars.length)];
//                star.setImageResource(randomMediumStar);
//            } else{
//                int randomLargeStar = largeStars[generator.nextInt(largeStars.length)];
//                star.setImageResource(randomLargeStar);
//            }
//        }
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d( "onStart","The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "The onDestroy() event");
    }
}
