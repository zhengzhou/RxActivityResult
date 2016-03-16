package app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import io.victoralbertos.app.R;
import rx_activity_result.RxActivityResult;

public class SampleActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

        findViewById(R.id.bt_camera).setOnClickListener(view -> camera());
    }

    private void camera() {
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        RxActivityResult.on(this).startIntent(takePhoto)
                .subscribe(result -> {
                    Intent data = result.data();
                    int resultCode = result.resultCode();

                    if (resultCode == RESULT_OK) {
                        result.targetUI().showImage(data);
                    } else {
                        result.targetUI().printUserCanceled();
                    }
                });
    }

    private void showImage(Intent data) {
        Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
        ((ImageView) findViewById(R.id.iv_thumbnail)).setImageBitmap(imageBitmap);
    }

    private void printUserCanceled() {
        Toast.makeText(this, getString(R.string.user_canceled_action), Toast.LENGTH_LONG).show();
    }
}
