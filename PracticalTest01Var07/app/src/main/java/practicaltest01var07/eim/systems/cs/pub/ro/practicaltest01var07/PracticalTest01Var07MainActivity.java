package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private EditText next;
    private EditText all;

    Button add;
    Button compute;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message Time]", intent.getStringExtra("time"));
            Log.d("[Message SUm:]", intent.getStringExtra("sum"));
        }
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.add:
                    try {
                        int leftNumberOfClicks = Integer.parseInt(next.getText().toString());
                        if (all.getText().toString().equals("")) {

                            all.setText(leftNumberOfClicks + "");
                        } else {
                            all.setText( all.getText().toString() + "+" + leftNumberOfClicks);
                        }
                    }
                    catch (Exception e) {

                    }

                    break;
                case R.id.compute:
                    if(last_compute.equals(all.getText().toString())) {
                        Log.d("[MESSAGE SUM]", "Sum " + result);
                        break;
                    }
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                    intent.putExtra("all", all.getText().toString());
                    last_compute = all.getText().toString();
                    startActivityForResult(intent, 1);
                    break;

            }
        }
    }

    ButtonClickListener l = new ButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        next = (EditText)  findViewById(R.id.NextTerm);
        next.setText("");
        all = (EditText) findViewById(R.id.allterms);
        all.setText("");

        add = (Button) findViewById(R.id.add);
        compute = (Button) findViewById(R.id.compute);
        add.setOnClickListener(l);
        compute.setOnClickListener(l);
        intentFilter.addAction("SACTION");
    }

    int result;
    String last_compute = "";
    boolean start_service = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            result = resultCode;
            Toast.makeText(this, "Sum " + resultCode, Toast.LENGTH_LONG).show();
            if (resultCode > 10 && start_service == false) {
                Log.d("Message", "Started service");
                start_service = true;
                Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
                intent2.putExtra("sum", result);
                getApplicationContext().startService(intent2);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // savedInstanceState.putString("all", all.getText().toString());
        savedInstanceState.putInt("sum", result);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState.containsKey("sum")) {
            result = savedInstanceState.getInt("sum");
            Log.d("[MESSAGE SUM RESTORE]", "Sum " + result);
        }

    }
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
