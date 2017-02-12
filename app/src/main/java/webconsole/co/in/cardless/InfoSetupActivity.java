package webconsole.co.in.cardless;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoSetupActivity extends AppCompatActivity {

    private EditText mobNum, accNum, codeText;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_setup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mobNum = (EditText) findViewById(R.id.mobNum);
        accNum = (EditText) findViewById(R.id.accNum);
        codeText = (EditText) findViewById(R.id.ifscText);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

    }

    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            startActivity(new Intent(InfoSetupActivity.this, MainActivity.class));
            finish();
        }
    };

    public void setupData(View view) {
        if(mobNum.getText().toString().equals("") || mobNum.getText().toString().length()<10){
            Snackbar.make(view, "Please enter valid Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(accNum.getText().toString().equals("") || accNum.getText().toString().length()<15){
            Snackbar.make(view, "Please enter valid Account Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(codeText.getText().toString().equals("") || codeText.getText().toString().length()<7){
            Snackbar.make(view, "Please enter valid IFSC Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{
            saveData(view);
            Handler myHandler = new Handler();
            myHandler.postDelayed(mMyRunnable, 1000);
        }

    }

    private void saveData(View view) {
        String mobNumber = mobNum.getText().toString().trim();
        String accNumber = accNum.getText().toString().trim();
        String codeTextVal = codeText.getText().toString().trim();

        UserInformation userInformation = new UserInformation(mobNumber,accNumber,codeTextVal);
        FirebaseUser user = auth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Snackbar.make(view, "Information Saved", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public void getIFSC(View view) {
        Snackbar.make(view, "Not implemented yet", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
