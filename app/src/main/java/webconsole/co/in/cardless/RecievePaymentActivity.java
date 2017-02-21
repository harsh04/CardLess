package webconsole.co.in.cardless;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class RecievePaymentActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView qrImg,def;
    final Context c = this;
    private EditText userInputDialogEditText;
    ProgressBar progressBar;
    String url, amtValue, userNumber;
    EditText userID;
    Button viaNum, viaLink, viaQr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_payment);
        qrImg = (ImageView) findViewById(R.id.dispQR);
        def = (ImageView) findViewById(R.id.defQR);
        userID = (EditText) findViewById(R.id.useridNum);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        qrImg.setVisibility(View.GONE);
        viaNum = (Button) findViewById(R.id.viaNum);
        viaNum.setOnClickListener(this);
        viaLink = (Button) findViewById(R.id.viaLink);
        viaLink.setOnClickListener(this);
        viaQr = (Button) findViewById(R.id.viaQR);
        viaQr.setOnClickListener(this);
    }

    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            progressBar.setVisibility(View.GONE);
            qrImg.setVisibility(View.VISIBLE);
        }
    };


    public boolean loadDialogForm(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_amount_dialog_box, null);
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
        return true;
    }

    public void loadPasswordForm(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_password_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        //TODO: make this work
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

    public void loadSendLinkForm(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_sendlink_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        //TODO: make this work

                    }
                })
                .setNeutralButton("Copy",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                       //TODO: MAKE THIS WORK
                    }
                })
                .setNegativeButton("Cancle",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                 });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viaNum:
                if(userID.getText().toString().trim().equals("") || userID.getText().toString().trim().length()<10){
                    Snackbar.make(v, "Enter a valid Mobile Number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    if(loadDialogForm()){
                        loadPasswordForm();
                    }
                }
                break;
            case R.id.viaLink:
                if(loadDialogForm()){
                    loadSendLinkForm();
                }
                break;
            case R.id.viaQR:
                loadDialogForm();
                def.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new ImageLoadTask(url, qrImg).execute();
                Handler myHandler = new Handler();
                myHandler.postDelayed(mMyRunnable, 1000);
                break;
            default:
                        break;
        }
    }
}
