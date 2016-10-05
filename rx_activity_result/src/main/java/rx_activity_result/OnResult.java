package rx_activity_result;

import android.content.Intent;
import android.support.annotation.Nullable;

interface OnResult {
    void response(int resultCode, @Nullable Intent data);
}
