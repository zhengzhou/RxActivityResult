package app;

import android.content.Context;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.victoralbertos.app.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    @Rule public ActivityTestRule<StartActivity> activityRule = new ActivityTestRule<>(StartActivity.class);
    private UiDevice uiDevice;

    @Before public void init() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test public void CheckActivity() {
        onView(withId(R.id.bt_activity)).perform(click());
        takePhoto();
        checkIntentSender();
    }

    @Test public void CheckActivityCancelUserAction() {
        onView(withId(R.id.bt_activity)).perform(click());
        cancelUserAction();
    }

    @Test public void CheckFragment() {
        onView(withId(R.id.bt_fragment)).perform(click());
        takePhoto();
        checkIntentSender();
    }

    @Test public void CheckFragmentCancelUserAction() {
        onView(withId(R.id.bt_fragment)).perform(click());
        cancelUserAction();
    }

    private void takePhoto() {
        onView(withId(R.id.bt_camera)).perform(click());
        waitTime();

        clickBottomMiddleScreen();
        rotateDevice();
        clickBottomMiddleScreen();

        onView(withId(R.id.iv_thumbnail)).check(matches(new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        }));
    }

    private void checkIntentSender() {
        onView(withId(R.id.bt_intent_sender)).perform(click());
        waitTime();
    }

    private void cancelUserAction() {
        onView(withId(R.id.bt_camera)).perform(click());
        waitTime();

        clickBottomMiddleScreen();
        rotateDevice();
        uiDevice.pressBack();

        onView(withId(R.id.iv_thumbnail)).check(matches(new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override public void describeTo(Description description) {
                description.appendText("has not drawable");
            }

            @Override public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() == null;
            }
        }));
    }

    private void rotateDevice() {
        try {
            uiDevice.setOrientationLeft();
            waitTime();
            uiDevice.setOrientationNatural();
            waitTime();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void clickBottomMiddleScreen() {
        WindowManager wm = (WindowManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        uiDevice.click(width/2, height - 100);
        waitTime();
    }

    private void waitTime() {
        try {Thread.sleep(3000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }
}