package webconsole.co.in.cardless;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
        final View mView = layoutInflaterAndroid.inflate(R.layout.user_amount_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if(Integer.valueOf(userInputDialogEditText.getText().toString().trim())<=0 || Integer.valueOf(userInputDialogEditText.getText().toString().trim())>=5000){
                            Snackbar.make(mView, "Valid Amount range from Rs.1 to Rs.4999", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            loadPasswordForm();

                        }

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
        final View mView = layoutInflaterAndroid.inflate(R.layout.user_password_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                       loadCards();
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

    public void loadCards(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        final View mView = layoutInflaterAndroid.inflate(R.layout.get_card_list, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        enterOTP();
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
    public void enterOTP(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        final View mView = layoutInflaterAndroid.inflate(R.layout.user_otp_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        userSuccess();
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
    public void userSuccess(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        final View mView = layoutInflaterAndroid.inflate(R.layout.user_success, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setNegativeButton("Done",
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
                    loadDialogForm();

                }
                break;
            case R.id.viaLink:
                if(loadDialogForm()){
                    loadSendLinkForm();
                }
                break;
            case R.id.viaQR:
                //loadDialogForm();
                SharedPreferences sharedPreferences = getSharedPreferences("infoondata-userinfo", Context.MODE_PRIVATE);
                String n = sharedPreferences.getString("username","");
                String m = sharedPreferences.getString("mobNum","");
                String e = sharedPreferences.getString("emailAddr","");
                String a = sharedPreferences.getString("accNumber","");
                String c = sharedPreferences.getString("codeTextVal","");
                url = "https://api.qrserver.com/v1/create-qr-code/?data="+n+m+e+a+c+"&amp;size=100x100";
                def.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new ImageLoadTask(url, qrImg).execute();
                Handler myHandler = new Handler();
                myHandler.postDelayed(mMyRunnable, 500);
                break;
            default:
                        break;
        }
    }
}
