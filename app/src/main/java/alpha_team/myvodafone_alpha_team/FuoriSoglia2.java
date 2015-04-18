package alpha_team.myvodafone_alpha_team;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import alpha_team.myvodafone_alpha_team.helper.HelperHttp;
import alpha_team.myvodafone_alpha_team.helper.JSONParser;


public class FuoriSoglia2 extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    String TAG = "FUORI_SOGLIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuori_soglia2);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.action_bar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Typeface fontExb = Typeface.createFromAsset(getAssets(), "VODAFONEEXB.TTF");
        Typeface fontErg = Typeface.createFromAsset(getAssets(), "VODAFONERG_0.TTF");

        final TextView chiamate = (TextView) findViewById(R.id.chiamate);
        final TextView messaggi = (TextView) findViewById(R.id.messaggi);
        final TextView internet = (TextView) findViewById(R.id.internet);
        final TextView addOn = (TextView) findViewById(R.id.addOn);

        final TextView chiamateText = (TextView) findViewById(R.id.chiamateText);
        final TextView messaggiText = (TextView) findViewById(R.id.messaggiText);
        final TextView internetText = (TextView) findViewById(R.id.internetText);
        final TextView addOnText = (TextView) findViewById(R.id.addonText);
        //HelperHttp.downloadSumFuoriSoglia(getApplicationContext(), chiamate, "");

        chiamate.setTypeface(fontExb);
        messaggi.setTypeface(fontExb);
        internet.setTypeface(fontExb);
        addOn.setTypeface(fontExb);

        chiamateText.setTypeface(fontErg);
        messaggiText.setTypeface(fontErg);
        internetText.setTypeface(fontErg);
        addOnText.setTypeface(fontErg);

        final GridLayout chiamateGrid = (GridLayout) findViewById(R.id.chiamateGrid);
        final GridLayout messaggiGrid = (GridLayout) findViewById(R.id.messaggiGrid);
        final GridLayout internetGrid = (GridLayout) findViewById(R.id.iternetGrid);
        final GridLayout addOnGrid = (GridLayout) findViewById(R.id.addonGrid);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String data1 = "";
                String data2 = "";
                Calendar date = null;
                switch (pos){
                    case 0:
                        date = Calendar.getInstance();
                        //Date d = date.getTime();
                        data1 = date.get(Calendar.DAY_OF_MONTH)+"/"+String.valueOf(date.get(Calendar.MONTH) + 1)+"/"+date.get(Calendar.YEAR);
                        data2=data1;
                        break;
                    case 1:
                        date = Calendar.getInstance();
                        data1 = date.get(Calendar.DAY_OF_MONTH)+"/"+String.valueOf(date.get(Calendar.MONTH) + 1)+"/"+date.get(Calendar.YEAR);
                        date.add(Calendar.MONTH,-1);
                        data2 = date.get(Calendar.DAY_OF_MONTH)+"/"+String.valueOf(date.get(Calendar.MONTH) + 1)+"/"+date.get(Calendar.YEAR);
                        break;
                    case 2:
                        date = Calendar.getInstance();
                        date.add(Calendar.MONTH,-1);
                        data1 = date.get(Calendar.DAY_OF_MONTH)+"/"+String.valueOf(date.get(Calendar.MONTH) + 1)+"/"+date.get(Calendar.YEAR);
                        date.add(Calendar.MONTH,-2);
                        data2 = date.get(Calendar.DAY_OF_MONTH)+"/"+String.valueOf(date.get(Calendar.MONTH) + 1)+"/"+date.get(Calendar.YEAR);

                        break;
                }

                Log.i(TAG, data1.toString());
                Log.i(TAG, data2.toString());
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(), chiamate, chiamateGrid, 0, data2, data1);
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(), messaggi, messaggiGrid, 1, data2, data1);
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(), internet, internetGrid, 2, data2, data1);
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(), addOn, addOnGrid, 3, data2, data1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.fuori_soglia2, menu);
            restoreActionBar();
            return true;
        //}
        //return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fuori_soglia2, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((FuoriSoglia2) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
