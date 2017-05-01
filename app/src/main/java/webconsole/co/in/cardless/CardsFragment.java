package webconsole.co.in.cardless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class CardsFragment extends Fragment {

    private final int CREATE_NEW_CARD = 10;
    private LinearLayout cardContainer;
    private Button addCardButton;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cards, container, false);
        initialize(view);
        listeners();
        return view;
    }


    private void initialize(View view) {
        addCardButton = (Button) view.findViewById(R.id.add_card);
        cardContainer = (LinearLayout) view.findViewById(R.id.card_container);
        saveUserInfo();
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    CardInformation ci = new CardInformation();
                    ci.setName(ds.child(sharedPreferences.getString("mobNum","")).getValue(CardInformation.class).getName());
                    ci.setCardNumber(ds.child(sharedPreferences.getString("mobNum","")).getValue(CardInformation.class).getCardNumber());
                    ci.setExpiry(ds.child(sharedPreferences.getString("mobNum","")).getValue(CardInformation.class).getExpiry());
                    ci.setCvv(ds.child(sharedPreferences.getString("mobNum","")).getValue(CardInformation.class).getCvv());

                    populate(ci.getName(),ci.getCvv(),ci.getExpiry(),ci.getCardNumber());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        populate("Harsh Mathur","420","04/50","4052424242424242");
        populate("Harsh","420","02/30","5826424242424242");
        populate("Harsh Mathur","420","05/30","482242424242");
        populate("Harsh","420","02/30","426862424242");
    }


    private void populate(String n, String cv, String ex, String cn) {
        CreditCardView sampleCreditCardView = new CreditCardView(getContext());

        String name = n;
        String cvv = cv;
        String expiry = ex;
        String cardNumber = cn;

        sampleCreditCardView.setCVV(cvv);
        sampleCreditCardView.setCardHolderName(name);
        sampleCreditCardView.setCardExpiry(expiry);
        sampleCreditCardView.setCardNumber(cardNumber);

        cardContainer.addView(sampleCreditCardView);
        int index = cardContainer.getChildCount() - 1;
        addCardListener(index, sampleCreditCardView);
    }

    private void listeners() {
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CardEditActivity.class);
                startActivityForResult(intent, CREATE_NEW_CARD);
            }
        });
    }

    private void addCardListener(final int index, CreditCardView creditCardView) {
        creditCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreditCardView creditCardView = (CreditCardView) v;
                String cardNumber = creditCardView.getCardNumber();
                String expiry = creditCardView.getExpiry();
                String cardHolderName = creditCardView.getCardHolderName();
                String cvv = creditCardView.getCVV();

                Intent intent = new Intent(getContext(), CardEditActivity.class);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME, cardHolderName);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_NUMBER, cardNumber);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_EXPIRY, expiry);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_SHOW_CARD_SIDE, CreditCardUtils.CARD_SIDE_BACK);
                intent.putExtra(CreditCardUtils.EXTRA_VALIDATE_EXPIRY_DATE, false);

                // start at the CVV activity to edit it as it is not being passed
                intent.putExtra(CreditCardUtils.EXTRA_ENTRY_START_PAGE, CreditCardUtils.CARD_CVV_PAGE);
                startActivityForResult(intent, index);
            }
        });
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);
            Toast.makeText(getActivity(), name+"***"+cardNumber, Toast.LENGTH_LONG).show();



            if (reqCode == CREATE_NEW_CARD) {

                CreditCardView creditCardView = new CreditCardView(getContext());

                creditCardView.setCVV(cvv);
                creditCardView.setCardHolderName(name);
                creditCardView.setCardExpiry(expiry);
                creditCardView.setCardNumber(cardNumber);

                cardContainer.addView(creditCardView);
                int index = cardContainer.getChildCount() - 1;
                addCardListener(index, creditCardView);
                populate(name, cvv, expiry, cardNumber);
                Toast.makeText(getContext(), name+"-"+cardNumber, Toast.LENGTH_LONG).show();
            } else {

                CreditCardView creditCardView = (CreditCardView) cardContainer.getChildAt(reqCode);

                creditCardView.setCardExpiry(expiry);
                creditCardView.setCardNumber(cardNumber);
                creditCardView.setCardHolderName(name);
                creditCardView.setCVV(cvv);

                Toast.makeText(getContext(), name+"++"+cardNumber, Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(getContext(),"no card save", Toast.LENGTH_LONG).show();
        }

    }
    private void saveUserInfo(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("infoondata-userinfo", Context.MODE_PRIVATE);
        int val0 = Integer.parseInt(sharedPreferences.getString("digit0",""));
        int val1 = Integer.parseInt(sharedPreferences.getString("digit1",""));
        int val2 = Integer.parseInt(sharedPreferences.getString("digit2",""));
        int val3 = Integer.parseInt(sharedPreferences.getString("digit3",""));
        String passKey = val0+""+val1+""+val2+""+val3;
        String enc_name = null;
        String enc_cvv = null;
        String enc_card = null;
        String enc_exp = null;
        try {
            //put pwd here
            AESAlgorithm aesAlgo=new AESAlgorithm(passKey);

            //put account here for encryption
            enc_name=aesAlgo.encrypt(sharedPreferences.getString("username",""));
             enc_cvv=aesAlgo.encrypt("360");
             enc_card=aesAlgo.encrypt("58293047182947");
             enc_exp=aesAlgo.encrypt("03/30");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CardInformation cardInformation = new CardInformation();
        cardInformation.setName(enc_name);
        cardInformation.setCvv(enc_cvv);
        cardInformation.setCardNumber(enc_card);
        cardInformation.setExpiry(enc_exp);
        databaseReference.child(sharedPreferences.getString("mobNum","")).setValue(cardInformation);
        Toast.makeText(getContext(),"Info saved",Toast.LENGTH_LONG).show();
    }

}
