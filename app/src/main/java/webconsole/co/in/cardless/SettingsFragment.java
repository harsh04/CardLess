package webconsole.co.in.cardless;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

    private EditText mobNum, accNum, codeText;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private Button profButton;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mobNum = (EditText) view.findViewById(R.id.mobNum);
        accNum = (EditText) view.findViewById(R.id.accNum);
        codeText = (EditText) view.findViewById(R.id.ifscText);
        profButton = (Button) view.findViewById(R.id.updateSetup);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        profButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupData(v);
            }
        });
        return view;

    }


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

        }

    }

    private void saveData(View view) {
        String mobNumber = mobNum.getText().toString().trim();
        String accNumber = accNum.getText().toString().trim();
        String codeTextVal = codeText.getText().toString().trim();

        UserInformation userInformation = new UserInformation(mobNumber,accNumber,codeTextVal);
        FirebaseUser user = auth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Snackbar.make(view, "Information updated successfully!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public void getIFSC(View view) {
        Snackbar.make(view, "Not implemented yet", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
