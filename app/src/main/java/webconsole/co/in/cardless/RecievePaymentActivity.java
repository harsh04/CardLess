package webconsole.co.in.cardless;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class RecievePaymentActivity extends AppCompatActivity {
    ImageView qrImg,def;
    final Context c = this;
    private EditText userInputDialogEditText;
    ProgressBar progressBar;
    String url, amtValue, userNumber;
    EditText userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_payment);
        qrImg = (ImageView) findViewById(R.id.dispQR);
        def = (ImageView) findViewById(R.id.defQR);
        userID = (EditText) findViewById(R.id.useridNum);
        userNumber = userID.getText().toString().trim();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        qrImg.setVisibility(View.INVISIBLE);
    }

    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            progressBar.setVisibility(View.INVISIBLE);
            qrImg.setVisibility(View.VISIBLE);
        }
    };


    public void loadDialogForm(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        url = "https://api.qrserver.com/v1/create-qr-code/?data="+userInputDialogEditText.getText().toString().trim()+"&amp;size=100x100";
                        amtValue = userInputDialogEditText.getText().toString().trim();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    //dialogbox with edittext and button
    public void generateQRCODE(View view) {
        loadDialogForm();
        def.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new ImageLoadTask(url, qrImg).execute();
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable, 1000);
    }

    public void requestMoney(View view) {
        loadDialogForm();
    }

    public void passScreen(View view) {
        if(userNumber.equals("") || userNumber.length()<10){
            Snackbar.make(view, "Enter Valid Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
