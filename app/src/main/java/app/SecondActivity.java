package app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.victoralbertos.app.R;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent data = new Intent();
        data.putExtra(EXTRA, "Well done second");
        setResult(RESULT_OK, data);
        finish();
    }
}
