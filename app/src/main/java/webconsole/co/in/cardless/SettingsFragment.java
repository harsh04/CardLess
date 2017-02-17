package webconsole.co.in.cardless;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private EditText mobNum, accNum, codeText, emailAddr, usern;
    private Button profButton;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        usern = (EditText) view.findViewById(R.id.username);
        mobNum = (EditText) view.findViewById(R.id.mobNum);
        emailAddr = (EditText) view.findViewById(R.id.emailAddr);
        accNum = (EditText) view.findViewById(R.id.accNum);
        codeText = (EditText) view.findViewById(R.id.ifscText);
        profButton = (Button) view.findViewById(R.id.updateSetup);
        profButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupData(v);
            }
        });
        return view;

    }


    public void setupData(View view) {
        if(usern.getText().toString().equals("")){
            Snackbar.make(view, "Please enter your username", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else if(mobNum.getText().toString().equals("") || mobNum.getText().toString().length()<10){
            Snackbar.make(view, "Please enter valid Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else if(emailAddr.getText().toString().equals("")){
            Snackbar.make(view, "Please enter your Email address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else if(accNum.getText().toString().equals("") || accNum.getText().toString().length()<15){
            Snackbar.make(view, "Please enter valid Account Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(codeText.getText().toString().equals("") || codeText.getText().toString().length()<7){
            Snackbar.make(view, "Please enter valid IFSC Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{
            saveData(view);

        }

    }

    private void saveData(View view) {
        String username = usern.getText().toString().trim();
        String mobNumber = mobNum.getText().toString().trim();
        String emailAdr = emailAddr.getText().toString().trim();
        String accNumber = accNum.getText().toString().trim();
        String codeTextVal = codeText.getText().toString().trim();

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("infoondata-userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("mobNum", mobNumber);
        editor.putString("emailAddr", emailAdr);
        editor.putString("accNumber", accNumber);
        editor.putString("codeTextVal", codeTextVal);
        editor.apply();
        Snackbar.make(view, "Information updated successfully!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        MainFragment homeFragment = new MainFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.fragment_main,
                homeFragment,
                homeFragment.getTag()
        ).commit();

    }

}
