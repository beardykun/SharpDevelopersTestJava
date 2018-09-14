package com.iamagamedev.sharpdeveloperstestjava;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.iamagamedev.sharpdeveloperstestjava", appContext.getPackageName());
    }
    @Test
    public void loginTest() {
        onView(withId(R.id.emailLoginText))
                .perform(typeText("q@q.com"))
                .check(matches(isDisplayed()));

        onView(withId(R.id.passwordLoginText)).perform(typeText("111111"), closeSoftKeyboard());

        onView(withId(R.id.okLoginBtn)).perform(click());

        onView(withId(R.id.profileRecipientNameText)).perform(click());
        onView(withId(R.id.profileSendAmountEdit)).perform(typeText("155"), closeSoftKeyboard());
    }
}
