package webconsole.co.in.cardless;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class RecievePaymentActivity extends AppCompatActivity {
    ImageView qrImg,def;
    private EditText value;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_payment);
        qrImg = (ImageView) findViewById(R.id.dispQR);
        def = (ImageView) findViewById(R.id.defQR);
        value = (EditText) findViewById(R.id.amtVal);
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

    public void generateQRCODE(View view) {
        String url = "https://api.qrserver.com/v1/create-qr-code/?data="+value.getText().toString().trim()+"&amp;size=100x100";
        def.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new ImageLoadTask(url, qrImg).execute();
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable, 500);

    }

}
