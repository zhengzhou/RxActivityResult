/*
 * Copyright 2016 Víctor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rx_activity_result;

import android.app.Activity;
import android.content.Intent;

import rx.Observable;
import rx.Subscriber;

public class RxActivityResult {
    public static Observable<Result> startIntent(Intent intent, Activity hostActivity) {
        RxActivityResult rxActivityResult = new RxActivityResult();
        return rxActivityResult.startForResult(intent, hostActivity);
    }

    private Subscriber<? super Result> subscriber;

    private RxActivityResult() {}

    private Observable<Result> startForResult(final Intent intent, Activity activity) {
        Observable<Result> observable = Observable.create(new Observable.OnSubscribe<Result>() {
            @Override public void call(Subscriber<? super Result> subscriber) {
                RxActivityResult.this.subscriber = subscriber;
            }
        });

        HolderActivity.setRequest(new Request(intent, new OnResult() {
            @Override public void response(Result result) {
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }));

        activity.startActivity(new Intent(activity, HolderActivity.class));

        return observable;
    }
}
