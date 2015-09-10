package google.com.jge.zfind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView l;
    private EditText eWhat;
    private EditText eZip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        initialize();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initialize()
    {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eWhat = (EditText) findViewById(R.id.editText);
        eZip = (EditText) findViewById(R.id.editText2);
    }

    public void search(View v)
    {
        String item   =  eWhat.getText().toString();
        String location  =  eZip.getText().toString();

        Intent intent = new Intent(this, ListViewActivity.class);

        intent.putExtra("theItem", item);
        intent.putExtra("theLocation", location);

        startActivity(intent);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.homeIcon) {

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
