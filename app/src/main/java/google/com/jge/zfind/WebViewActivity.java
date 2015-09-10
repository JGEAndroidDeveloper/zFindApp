package google.com.jge.zfind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    String phone;
    WebView wv;
    String website;
    private String longitude;
    private String latitude;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);

        wv = (WebView) findViewById(R.id.webView);

        phone = ListItemSelected.PHONE_NUMBER;
        website = ListItemSelected.URL_ADDRESS;
        longitude = ListItemSelected.LONGITUDE;
        latitude = ListItemSelected.LATITUDE;
        title = ListItemSelected.TITLE;



        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(website);
        if(website != null)
        {
            wv.setWebViewClient(new WebViewClient());
            wv.loadUrl(website);
            wv.requestFocus();
        }else{Toast.makeText(this,"We're sorry, this webpage is not available at this time",Toast.LENGTH_LONG).show();}

        return true;
    }

    private void getMaps(String lat,String lon, String label)
    {
        //String label = title;
        String uriBegin = "geo:" + lat + "," + lon;
        String query = lat + "," + lon + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
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
            Toast.makeText(this,"I do nothing for now",Toast.LENGTH_LONG).show();
            return true;
        }
        if(id == R.id.phoneIcon)
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            startActivity(intent);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
