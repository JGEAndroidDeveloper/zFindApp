package google.com.jge.zfind;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ListViewActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ListView l;

    private String item;
    private String location;

    private String [] countTitle = new String[10];
    private String [] countLocation = new String[10];
    private String [] countPhone = new String[10];

    private int [] countImage = new int[10];
    private String [] countUrl = new String [10];
    private String[] countLatitude = new String[10];
    private String [] countLongitude = new String [10];



    private int[] images = {R.mipmap.ic_launcher, R.mipmap.ic_home_white_24dp};

    private String searchUrl;

    private ArrayList<SingleRow> list;
    private ArrayList<Item> itemAlpha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getString("theItem");
            location = extras.getString("theLocation");
        }

        item = getIntent().getStringExtra("theItem");
        location = getIntent().getStringExtra("theLocation");



        list = new ArrayList<SingleRow>();

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        l = (ListView) findViewById(R.id.listView);


        searchUrl ="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%27%22"+location+"%22%27%20and%20query%3D%27%22"+item+"%22%27&format=json&diagnostics=true&callback=";
        Download downloader = new Download();
        downloader.execute(searchUrl);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
        return true;
    }




    public class Download extends AsyncTask<String, Integer, Void>
    {
        Intent intent = new Intent(ListViewActivity.this,ListItemSelected.class);
        int currentValue =-1;
        private ProgressDialog dialog;
        boolean isWorking;

        @Override
        protected void onProgressUpdate(Integer... progress) {
            int current = progress[0];
            int total = progress[0];

            //float percentage = 100 * (float) current / (float) total;
int count = (int)(((double)progress[0]/currentValue)*100);
            dialog.setProgress(count);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(ListViewActivity.this);
            this.dialog.setIndeterminate(false);
            this.dialog.setMax(100);
            this.dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.dialog.setCancelable(false);
            this.dialog.setTitle("Downloading");
            this.dialog.setMessage("Now Downloading...");
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            URL url;
            HttpsURLConnection urlConnection;
            InputStream in;
            BufferedReader reader;
            String json;



            try {
                url = new URL(searchUrl);
                urlConnection = (HttpsURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in));
                json = reader.readLine();
                currentValue = urlConnection.getContentLength();
                Log.d("JSON", json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject jObjectQuery = jsonObject.getJSONObject("query");
                    JSONObject jsonObjectResults = jObjectQuery.getJSONObject("results");
                    JSONArray jsonArrayResult = jsonObjectResults.getJSONArray("Result");

                    isWorking = true;

                    if(jsonArrayResult !=  null){Log.e("ERROR","results are not null");}

                    for (int i = 0; i < jsonArrayResult.length(); i++) {

Thread.sleep(2000);
                        final int val = (int)(((float)i/(jsonArrayResult.length()-1))*100);
                        if(jsonArrayResult ==  null){Log.e("ERROR","results are null");}
                        Log.d("VALUE", "Val: " + val);
                        publishProgress(currentValue);


                        JSONObject jsonObject1 = jsonArrayResult.getJSONObject(i);
                        String jsonTitle = jsonObject1.getString("Title");
                        String jsonLocation = jsonObject1.getString("Address")+", "+jsonObject1.getString("City")+", "+jsonObject1.getString("State");

                        String jsonPhone = jsonObject1.getString("Phone");
                        String jsonBusinessUrl = jsonObject1.getString("BusinessUrl");
                        String jsonLatitude = jsonObject1.getString("Latitude");
                        String jsonLongitude = jsonObject1.getString("Longitude");

                        countTitle[i] = jsonTitle;
                        countLocation[i] =jsonLocation;
                        countPhone[i] = jsonPhone;
                        countUrl[i] = jsonBusinessUrl;

                        countLatitude[i] = jsonLatitude;
                        countLongitude[i] = jsonLongitude;




                        SingleRow theInfo = new SingleRow(jsonTitle, jsonLocation,images[0]);
                        list.add(theInfo);

                        publishProgress(val);


                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("VALUE", "Val: is null and something is wrong");
                    isWorking = false;
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            if(isWorking == true)
            {
                String[] titleArray = new String[list.size()];
                int count = 0;
                for(SingleRow theList : list){
                    titleArray[count++] = theList.title;


                }
            }else
            {
                /********************ALERT DIALOG WHEN JSON DOES NOT LOAD*************************/
                AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
                builder.setMessage("This data does not exist at the moment. Please try again another time.")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intentC = new Intent(ListViewActivity.this, MainActivity.class);
                                startActivity(intentC);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                /*******************END ALERT*********************************************************/
            }

            MyAdapter adapter = new MyAdapter(ListViewActivity.this, list);
            l.setAdapter(adapter);





            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,long id)
                {
                    String title = getCountTitle(position);
                    String location = getCountLocation(position);
                    String phone = getCountPhone(position);
                    String url =  getCountUrl(position);

                    String longitude = getCountLongitude(position);
                    String latitude = getCountLatitude(position);





                    Intent intent = new Intent(ListViewActivity.this,ListItemSelected.class);

                    intent.putExtra("Title", title);
                    intent.putExtra("Location", location);
                    intent.putExtra("Phone", phone);
                    intent.putExtra("Longitude",longitude);
                    intent.putExtra("Latitude", latitude);
                    if(url != null) {
                        intent.putExtra("WebUrl", url);
                    }
                    else {Toast.makeText(getBaseContext(),"There is no website for this now.",Toast.LENGTH_SHORT).show();}
                    startActivity(intent);


                }
            });



            super.onPostExecute(aVoid);
        }
    }

    public String getCountTitle(int num)
    {
        return countTitle[num];
    }

    public String getCountPhone(int num)
    {
        return countPhone[num];
    }

    public String getCountLocation(int num)
    {
        return countLocation[num];
    }

    public String getCountUrl(int num)
    {
        return countUrl[num];
    }

    public String getCountLongitude(int num){ return countLongitude[num];}

    public String getCountLatitude(int num){return countLatitude[num];}






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
        if (id==android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
