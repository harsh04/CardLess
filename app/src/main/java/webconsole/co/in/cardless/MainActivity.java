package webconsole.co.in.cardless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(MainActivity.this,UnlockActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainFragment homeFragment = new MainFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.fragment_main,
                homeFragment,
                homeFragment.getTag()
        ).commit();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences sharedPreferences = getSharedPreferences("infoondata-userinfo", Context.MODE_PRIVATE);
        if((sharedPreferences.getString("username","").equals("")) || (sharedPreferences.getString("emailAddr","").equals(""))){
            SettingsFragment settingsFragment = new SettingsFragment();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    settingsFragment,
                    settingsFragment.getTag()
            ).commit();
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user_name = (TextView)hView.findViewById(R.id.user_name);
        nav_user_name.setText(sharedPreferences.getString("username",""));
        TextView nav_user_email = (TextView)hView.findViewById(R.id.user_email);
        nav_user_email.setText(sharedPreferences.getString("emailAddr",""));

        navigationView.setNavigationItemSelectedListener(this);


        if((sharedPreferences.getString("digit0","").equals("")) || (sharedPreferences.getString("digit1","").equals("")) || (sharedPreferences.getString("digit2","").equals("")) || (sharedPreferences.getString("digit3","").equals(""))){
            startActivity(new Intent(MainActivity.this, PinSetupActivity.class));      // Launch Pin setup screen if Pin is not set properly

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            MainFragment homeFragment = new MainFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    homeFragment,
                    homeFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_cards) {
            CardsFragment cardsFragment = new CardsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    cardsFragment,
                    cardsFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_transactions) {
            TransactionFragment transactionFragment = new TransactionFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    transactionFragment,
                    transactionFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_setings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    settingsFragment,
                    settingsFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_feedback) {
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    feedbackFragment,
                    feedbackFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.fragment_main,
                    aboutFragment,
                    aboutFragment.getTag()
            ).commit();
        } /*else if (id == R.id.nav_log_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("AUTH","USER LOGGED OUT");
                            finish();
                        }
                    });
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void makePayment(View view) {

    }

    public void recievePayment(View view) {
        startActivity(new Intent(MainActivity.this,RecievePaymentActivity.class));
    }


    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {

            SharedPreferences sharedPreferences = getSharedPreferences("infoondata-userinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();


            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumbeer = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            editor.putString("cardHolderName", cardHolderName);
            editor.putString("cardNumbeer", cardNumbeer);
            editor.putString("expiry", expiry);
            editor.putString("cvv", cvv);
            editor.apply();
        }
    }
}

