package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("all")) {
            String parts[] = intent.getStringExtra("all").toString().split("[+]");
            int sum = 0;
            for(String s : parts) {
                try {
                    sum += Integer.parseInt(s);
                }
                catch (Exception e) {}
            }
            setResult(sum,null);
        }
        finish();
    }
}
