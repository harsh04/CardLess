package webconsole.co.in.cardless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UnlockActivity extends AppCompatActivity implements View.OnClickListener{

    TextView heading;
    private int[] digit = new int[4];
    private int posOfPointer = 0;
    private int wrongCount = 3;

    Button one,two,three,four,five,six,seven,eight,nine,zero,cross,next;
    ImageView[] digitImg = new ImageView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        heading = (TextView) findViewById(R.id.heading);
        heading.setText("PIN to UNLOCK");

        next = (Button) findViewById(R.id.nextBtn);
        next.setEnabled(false);
        one = (Button) findViewById(R.id.num1);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.num2);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.num3);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.num4);
        four.setOnClickListener(this);
        five = (Button) findViewById(R.id.num5);
        five.setOnClickListener(this);
        six = (Button) findViewById(R.id.num6);
        six.setOnClickListener(this);
        seven = (Button) findViewById(R.id.num7);
        seven.setOnClickListener(this);
        eight = (Button) findViewById(R.id.num8);
        eight.setOnClickListener(this);
        nine = (Button) findViewById(R.id.num9);
        nine.setOnClickListener(this);
        zero = (Button) findViewById(R.id.num0);
        zero.setOnClickListener(this);
        cross = (Button) findViewById(R.id.cross);
        cross.setOnClickListener(this);

        digitImg[0] = (ImageView) findViewById(R.id.digit0);
        digitImg[1] = (ImageView) findViewById(R.id.digit1);
        digitImg[2] = (ImageView) findViewById(R.id.digit2);
        digitImg[3] = (ImageView) findViewById(R.id.digit3);

    }
    protected void imgHandler(int pos,int val){
        if(val == 0){
            digitImg[pos].setImageResource(R.drawable.unlock);
        }else if(val == 1){
            digitImg[pos].setImageResource(R.drawable.dot);
        }
    }

    /* TO DISABLE BACKPRESS FOR SECURITY REASON */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void verifyPin(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("infoondata-userinfo", Context.MODE_PRIVATE);
        int val0 = Integer.parseInt(sharedPreferences.getString("digit0",""));
        int val1 = Integer.parseInt(sharedPreferences.getString("digit1",""));
        int val2 = Integer.parseInt(sharedPreferences.getString("digit2",""));
        int val3 = Integer.parseInt(sharedPreferences.getString("digit3",""));
        if(( val0 == digit[0]) && ( val1 == digit[1]) && ( val2 == digit[2]) && ( val3 == digit[3])){
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT);
            startActivity(new Intent(UnlockActivity.this, MainActivity.class));
            finish();
        }else{
            Snackbar.make(view, "Wrong Pin!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            posOfPointer = 0;
            imgHandler(0,0);
            imgHandler(1,0);
            imgHandler(2,0);
            imgHandler(3,0);
            buttonHandle(true);
            wrongCount--;
            if(wrongCount<=0){
                finish();
            }
        }
    }

    private void buttonHandle(Boolean bool){
        next.setEnabled(!bool);
        one.setEnabled(bool);
        two.setEnabled(bool);
        three.setEnabled(bool);
        four.setEnabled(bool);
        five.setEnabled(bool);
        six.setEnabled(bool);
        seven.setEnabled(bool);
        eight.setEnabled(bool);
        nine.setEnabled(bool);
        zero.setEnabled(bool);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.num0:
                digit[posOfPointer] = 0;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num1:
                digit[posOfPointer] = 1;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num2:
                digit[posOfPointer] = 2;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num3:
                digit[posOfPointer] = 3;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num4:
                digit[posOfPointer] = 4;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num5:
                digit[posOfPointer] = 5;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num6:
                digit[posOfPointer] = 6;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num7:
                digit[posOfPointer] = 7;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num8:
                digit[posOfPointer] = 8;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.num9:
                digit[posOfPointer] = 9;
                imgHandler(posOfPointer,1);
                if(posOfPointer == 3){
                    buttonHandle(false);
                }else {
                    posOfPointer++;
                }
                break;
            case R.id.cross:
                posOfPointer = 0;
                imgHandler(0,0);
                imgHandler(1,0);
                imgHandler(2,0);
                imgHandler(3,0);
                buttonHandle(true);
                break;
            default: break;
        }
    }
}