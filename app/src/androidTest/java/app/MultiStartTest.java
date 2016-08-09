package app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import app.multi_start.MultiStartActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.victoralbertos.app.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MultiStartTest {
    @Rule public ActivityTestRule<MultiStartActivity> activityRule = new ActivityTestRule<>(MultiStartActivity.class);

    @Test public void CheckHasBothResults() {
        onView(withId(R.id.start_two_for_result)).perform(click());
        onView(withId(R.id.first_result)).check(matches(withText("Well done first")));
        onView(withId(R.id.second_result)).check(matches(withText("Well done second")));
    }

}