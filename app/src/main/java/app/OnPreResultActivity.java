package app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import app.multi_start.FirstActivity;
import app.multi_start.SecondActivity;
import io.victoralbertos.app.R;
import rx_activity_result.OnResult;
import rx_activity_result.RxActivityResult;

public class OnPreResultActivity extends AppCompatActivity {
    public static final String EXTRA_PRE = "EXTRA_PRE";
    private TextView preResult;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_pre_result);

        View startPreForResult = findViewById(R.id.start_pre_for_result);
        preResult = (TextView) findViewById(R.id.pre_result);
        result = (TextView) findViewById(R.id.result);

        startPreForResult.setOnClickListener(v ->
            RxActivityResult.on(this)
                .startIntent(new Intent(this, FirstActivity.class), (OnResult) (resultCode, data) ->
                    data.putExtra(EXTRA_PRE, "Do whatever you want with the data, but not with the UI"))
                .subscribe(result -> {
                    result.targetUI()
                        .preResult.setText(result.data().getStringExtra(EXTRA_PRE));
                    result.targetUI()
                        .result.setText(result.data().getStringExtra(FirstActivity.EXTRA));
                })
        );
    }

}
