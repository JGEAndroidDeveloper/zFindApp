package google.com.jge.zfind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ListItemSelected extends AppCompatActivity {

    TextView tvHeader;
    TextView tvAddress;
    TextView tvPhone;

    Intent intentA;
    Intent intentB;

    Toolbar toolbar;

    public static String PHONE_NUMBER;
    public static String URL_ADDRESS;
    public static String LATITUDE;
    public static String LONGITUDE;
    public static String TITLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_selected);

        initialize();
        setTextViews();
        URL_ADDRESS = getIntent().getExtras().getString("WebUrl");
        PHONE_NUMBER = getIntent().getExtras().getString("Phone");
        LONGITUDE = getIntent().getExtras().getString("Longitude");
        LATITUDE = getIntent().getExtras().getString("Latitude");
        TITLE =  getIntent().getExtras().getString("Title");




    }

    private void setTextViews()
    {
        tvHeader.setText(getIntent().getExtras().getString("Title"));
        tvAddress.setText(getIntent().getExtras().getString("Location"));
        tvPhone.setText(getIntent().getExtras().getString("Phone"));
    }

    private void initialize()
    {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*********************************************************************/
        tvHeader = (TextView)findViewById(R.id.textView3);
        tvAddress = (TextView)findViewById(R.id.textView4);
        tvPhone = (TextView)findViewById(R.id.textView5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_item_selected, menu);
        return true;
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
        if(id == R.id.phoneIcon)
        {
            intentA = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PHONE_NUMBER));
            startActivity(intentA);
            return true;
        }
        if(id == R.id.browserIcon)
        {
            if(URL_ADDRESS!= " ")
            {
                intentB = new Intent(this, WebViewActivity.class);
                startActivity(intentB);
            }else{
                Toast.makeText(this,"We're sorry this website is unavailable at this time.",Toast.LENGTH_SHORT).show();}

            return true;
        }
        if(id== R.id.homeIcon)
        {
            Intent intentC = new Intent(this, MainActivity.class);
            startActivity(intentC);
            return true;
        }
        if(id == R.id.mapIcon)
        {
            Intent intentD = new Intent(this, MapsActivityA.class);
            startActivity(intentD);
            //getMaps(latitude,longitude,title);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
