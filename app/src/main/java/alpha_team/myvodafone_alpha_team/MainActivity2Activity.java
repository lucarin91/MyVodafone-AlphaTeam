package alpha_team.myvodafone_alpha_team;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.net.HttpCookie;
import java.util.ArrayList;

import alpha_team.myvodafone_alpha_team.helper.HelperHttp;
import alpha_team.myvodafone_alpha_team.model.Chiamate;
import alpha_team.myvodafone_alpha_team.model.chimateAdapter;


public class MainActivity2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        ListView listView = (ListView) findViewById(R.id.listView);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.action_bar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        //listView.setOnItemClickListener(this);
        /*
        ArrayList<Chiamate> chiamList = new ArrayList<Chiamate>();
        chiamList.add(new Chiamate(123123,"aslkdj","jkljkl","jk"));
        listView.setAdapter( new chimateAdapter(getApplicationContext(), chiamList));
        */
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("data1");
        String data2 = intent.getStringExtra("data2");
        HelperHttp.downloadChiamate(getApplicationContext(),listView, data2, data1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
