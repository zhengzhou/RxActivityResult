RxActivityResult
================
The api which Android SDK exposes to retrieve the data from a 'returning system call' (camera, gallery, email...) just does not give a shit about [Don't break the chain](http://blog.danlew.net/2015/03/02/dont-break-the-chain) leitmotiv. Indeed, the [OnActivityResult](http://developer.android.com/intl/es/training/basics/intents/result.html) approach will broke entirely your observable chaining. 

I did this library to not have to deal with this OnActivityResult pattern. Never. Ever.  

RxActivityResult features:
--------------------------
* Launch the intent from any class, as long as you supply a valid activity instance.
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
    compile "com.github.VictorAlbertos:RxActivityResult:0.1"
    compile "io.reactivex:rxjava:1.1.0"
}
```

Usage
=====
Create an instance of the Intent you want to launch and supply it -with a valid reference to the current activity, to the static [RxActivityResult.startIntent]() method.
Observe the emitted [Result]() item to know the resultCode and retrieve the associated data if appropriate.  

```java
Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

RxActivityResult.startIntent(takePhoto, this)
        .subscribe(result -> {
            Intent data = result.data();
            int resultCode = result.resultCode();
            
            if (resultCode == RESULT_OK) {
                showImage(data);
            } else {
                printUserCanceled();
            }
        });
```

Examples
--------
There is an example of RxActivityResult in the [app module](https://github.com/VictorAlbertos/RxActivityResult/tree/master/app)

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