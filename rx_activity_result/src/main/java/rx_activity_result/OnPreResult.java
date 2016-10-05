package rx_activity_result;

import android.content.Intent;
import android.support.annotation.Nullable;

import rx.Observable;

public interface OnPreResult<T> {
    Observable<T> response(int resultCode, @Nullable Intent data);
}
