package webconsole.co.in.cardless;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_payment);
        qrImg = (ImageView) findViewById(R.id.dispQR);
        def = (ImageView) findViewById(R.id.defQR);
        //value = (EditText) findViewById(R.id.amtVal);
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
                        String url = "https://api.qrserver.com/v1/create-qr-code/?data="+userInputDialogEditText.getText().toString().trim()+"&amp;size=100x100";
                        def.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        new ImageLoadTask(url, qrImg).execute();
                        Handler myHandler = new Handler();
                        myHandler.postDelayed(mMyRunnable, 1000);
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
    }

    public void requestMoney(View view) {
        loadDialogForm();
    }
}
