[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxActivityResult-green.svg?style=true)](https://android-arsenal.com/details/1/3284)

RxActivityResult
================
The api which Android SDK exposes to retrieve the data from a 'returning system call' (camera, gallery, email...) just does not give a shit about [Don't break the chain](http://blog.danlew.net/2015/03/02/dont-break-the-chain) leitmotiv. Indeed, the [OnActivityResult](http://developer.android.com/intl/es/training/basics/intents/result.html) approach will break entirely your observable chaining. 

I did this library to not have to deal with this OnActivityResult pattern. Never. Ever.  

RxActivityResult features:
--------------------------
* Launch the intent from any class, as long as you supply a valid activity or fragment instance.
* Get the intent back with the data encapsulated in an observable and keep going crazy chaining operations. 

Setup
-----

Add the JitPack repository in your build.gradle (top level module):
```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

And add next dependencies in the build.gradle of the module:
```gradle
dependencies {
    compile "com.github.VictorAlbertos:RxActivityResult:0.2.2"
    compile "io.reactivex:rxjava:1.1.0"
}
```

Usage
=====
Call RxActivityResult.register in your Android Application class, supplying as parameter the current Android Application instance.
        
```java
public class SampleApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        RxActivityResult.register(this);
    }
}
```

You can call RxActivityResult.on(this).startIntent(intent) supplying both, an activity instance or a fragment instance.
Observe the emitted [Result](https://github.com/VictorAlbertos/RxActivityResult/blob/master/rx_activity_result/src/main/java/rx_activity_result/Result.java) item to know the resultCode and retrieve the associated data if appropriate.  


```java
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
```

Please pay attention to the targetUI() method in the Result object emitted. 

This method returns a safety instance of the current Activity/Fragment. Because the original instance of the Activity/Fragment may be recreated (due to configuration changes or some other system events) it would be unsafe calling it. 

Instead, you must call any method/variable of your Activity/Fragment from this instance encapsulated in the Result object.  

Examples
--------
There is an example of RxActivityResult using both activity and fragment in the [app module](https://github.com/VictorAlbertos/RxActivityResult/tree/master/app)

Author
-------
**Víctor Albertos**

* <https://twitter.com/_victorAlbertos>
* <https://linkedin.com/in/victoralbertos>
* <https://github.com/VictorAlbertos>

Another author's libraries using RxJava:
----------------------------------------
* [RxCache](https://github.com/VictorAlbertos/RxCache): Reactive caching library for Android and Java.
* [RxGcm](https://github.com/VictorAlbertos/RxGcm): A reactive wrapper for Android Google Cloud Messaging to get rid of Service(s) configuration, handling foreground and background notifications depending on application state.
