package example.haim.menusanddialogs;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etText;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //BW Competability for old Android OS's
        setSupportActionBar(toolbar);

        etText = (EditText) findViewById(R.id.etText);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String text = etText.getText().toString();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.Action_hello:
//                Snackbar.make(findViewById(R.id.fab), "Hello!", Snackbar.LENGTH_INDEFINITE).show();
//                break;
            case R.id.action_search:
                Snackbar.make(findViewById(R.id.fab), text, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.action_refresh:
                etText.setText("");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //1) to create Dialog need to create instance Alert.dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Internet Connection").
                //in order to do that modal dialog - when pressing outside the dialod -> not dismissed -
                // only Quit button will close it.
                setCancelable(false).
                setMessage("This app requires an internet connection").
                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
                        startActivity(intent);

//                      or with single row
//                      startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                    }
                }).setNegativeButton("Quit App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        }).setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })
                .show();

        //2) work with the builder -> setTitle, setIcon, set***Button
        //3) to display it need "show"
    }

    public void pickAColor(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String[] items = new String[]{"Red", "Green" , "Blue"};
        builder.setTitle("Pick A Color").
                setIcon(R.mipmap.ic_launcher).
                setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
