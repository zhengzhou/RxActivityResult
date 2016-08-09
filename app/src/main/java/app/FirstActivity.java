package app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.victoralbertos.app.R;


public class FirstActivity extends AppCompatActivity {

    public static final String EXTRA = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Intent data = new Intent();
        data.putExtra(EXTRA, "Well done first");
        setResult(RESULT_OK, data);
        finish();
    }
}
