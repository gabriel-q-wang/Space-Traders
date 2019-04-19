package cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetraders.R;
import cs2340.spacetraders.entity.Universe.Equipment.WeaponTypes;
import cs2340.spacetraders.entity.Universe.RelativePosition;
import cs2340.spacetraders.model.Model;
import cs2340.spacetraders.viewmodels.ShipyardViewModel;

/**
 * Concrete class that controls how the Shipyard UI behaves
 */
public class ShipyardActivity extends AppCompatActivity {

    private TableLayout weaponTable;
    private TableRow modelWeaponRow;
    private Button modelWeaponBuyButton;
    private Button modelWeaponInfoButton;
    private TextView modelWeaponName;
    private Button modelSellButton;

    private TextView weaponInfo;

    private TextView creditCounter;
    private TextView equippedWeapons;

    private ShipyardViewModel shipyardVM;
    private final Model model = Model.getInstance();
    private RelativePosition center;


    /**
     * Constructor for this class
     */
    public ShipyardActivity() {
    }

    /**
     * called when viewing the shipyard
     * @param savedInstanceState standard parameter required by android, is automatically
     *                           handled with startActivityForResult
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipyard_screen);

        FloatingActionButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipyardActivity.this, MenuScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        weaponTable = findViewById(R.id.weaponTable);
        modelWeaponRow = findViewById(R.id.modelWeaponRow);
        modelWeaponBuyButton = findViewById(R.id.modelWeaponBuyButton);
        modelWeaponInfoButton = findViewById(R.id.modelWeaponInfoButton);
        modelWeaponName = findViewById(R.id.modelWeaponName);
        modelSellButton = findViewById(R.id.modelSellButton);

        weaponInfo = findViewById(R.id.weapon_info);

        creditCounter = findViewById(R.id.creditCounter);
        equippedWeapons = findViewById(R.id.equippedWeapons);
        shipyardVM = new ShipyardViewModel(model.getPlayer());
        updateCreditCounter();

        setupInfo();
        weaponTable.removeViewAt(0);
        generateWeaponTable();
        updateEquippedWeaponsList();
    }

    private void generateWeaponTable() {
        for (WeaponTypes wep: WeaponTypes.values()) {
            weaponTable.addView(makeWeaponTableRow(wep));
        }
    }

    private TableRow makeWeaponTableRow(WeaponTypes weapon) {
        TableRow tablerow = new TableRow(this);
        tablerow.setLayoutParams(modelWeaponRow.getLayoutParams());

        //Copy the info button
        Button weaponInfoButton = new Button(this);
        weaponInfoButton.setLayoutParams(modelWeaponInfoButton.getLayoutParams());
        weaponInfoButton.setText("INFO");
        attachWeaponInfoEventListener(weaponInfoButton, weapon);

        //Copy weapon textView
        TextView weaponName = new TextView(this);
        weaponName.setLayoutParams(modelWeaponName.getLayoutParams());
        weaponName.setText(weapon.getEquipName());

        //Copy the sell button
        Button sellButton = new Button(this);
        sellButton.setLayoutParams(modelSellButton.getLayoutParams());
        sellButton.setEnabled(shipyardVM.containsWeaponType(weapon));
        sellButton.setText("SELL");
        attachSellButtonEventListener(sellButton, weapon);

        //Copy the weapon buy button
        Button weaponBuyButton = new Button(this);
        weaponBuyButton.setLayoutParams(modelWeaponBuyButton.getLayoutParams());
        attachWeaponBuyingEventListener(weaponBuyButton, weapon, sellButton);
        weaponBuyButton.setText("BUY");

        tablerow.addView(weaponName);
        tablerow.addView(weaponBuyButton);
        tablerow.addView(sellButton);
        tablerow.addView(weaponInfoButton);

        return tablerow;

    }

    private void attachWeaponBuyingEventListener(Button weaponBuyButton, final WeaponTypes WEAPON,
                                                 final Button weaponSellButton) {
        weaponBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String purchaseErrorString = shipyardVM.weaponBuyError(WEAPON);
                if (purchaseErrorString == null) {
                    shipyardVM.buyWeapon(WEAPON);
                    updateCreditCounter();
                    updateEquippedWeaponsList();
                    weaponSellButton.setEnabled(true);
                } else {
                    Toast.makeText(ShipyardActivity.this, purchaseErrorString,
                            Toast.LENGTH_SHORT).show();
                }
                updateInfo(WEAPON);
            }
        });
    }

    private void attachWeaponInfoEventListener(Button modelWeaponInfoButton,
                                               final WeaponTypes WEAPON) {
        modelWeaponInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo(WEAPON);
            }
        });
    }

    private void updateInfo(final WeaponTypes WEAPON) {
        final int buyPrice = WEAPON.getBuyPrice();
        final int sellPrice = WEAPON.getSellPrice();
        final int power = WEAPON.getPower();
        final int charge = WEAPON.getCharge();

        String infoStr = "";
        Object[] infoData = new Object[]{buyPrice, sellPrice, power < 0 ? "NA" : power, charge < 0 ? "NA" : charge};
        String[] infoLabels = new String[]{"Buy Price:", "Sell Price:", "Power:      ", "Charge:    "};
        for (int i = 0; i < infoLabels.length; i++) {
            infoStr += infoLabels[i] + " \t\t\t" + infoData[i] + "\n";
        }
        weaponInfo.setText(infoStr);
    }

    private void setupInfo() {
        String infoStr = "";
        Object[] infoData = new Object[]{"NA", "NA", "NA", "NA"};
        String[] infoLabels = new String[]{"Buy Price:", "Sell Price:", "Power:      ", "Charge:    "};
        for (int i = 0; i < infoLabels.length; i++) {
            infoStr += infoLabels[i] + " \t\t\t" + infoData[i] + "\n";
        }
        weaponInfo.setText(infoStr);
    }

    private void updateCreditCounter() {
        creditCounter.setText(Integer.toString(shipyardVM.getPlayerCredits()));
    }

    private void attachSellButtonEventListener(final Button MODEL_SELL_BUTTON,
                                               final WeaponTypes WEAPON) {
        MODEL_SELL_BUTTON.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                shipyardVM.sellWeapon(WEAPON);
                if (!(shipyardVM.containsWeaponType(WEAPON))) {
                    MODEL_SELL_BUTTON.setEnabled(false);
                }
                updateCreditCounter();
                updateEquippedWeaponsList();
            }
        });
    }

    private void updateEquippedWeaponsList() {
        equippedWeapons.setText(shipyardVM.getEquippedWeaponsString());
    }
}
