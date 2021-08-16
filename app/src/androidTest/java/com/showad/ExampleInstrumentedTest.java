package com.showad;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import com.smaato.sdk.banner.widget.BannerError;
import com.smaato.sdk.banner.widget.BannerView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.showad", appContext.getPackageName());
    }

    @Test
    public void showAd_Activity() throws InterruptedException {
        // Type text and then press the button.
        setListener();
        onView(withId(R.id.loadAdBtn)).perform(click());
    }


    private void setListener() {
        activityRule.getActivity().setEventListener(new BannerView.EventListener() {
            @Override
            public void onAdLoaded(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdLoaded------------------------111111");
                onView(withId(R.id.bannerView)).perform(click());
            }

            @Override
            public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {
                Log.e("EventListener", "onAdFailedToLoad------------------------111111");
            }

            @Override
            public void onAdImpression(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdImpression------------------------111111");
            }

            @Override
            public void onAdClicked(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdClicked------------------------111111");
            }

            @Override
            public void onAdTTLExpired(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdTTLExpired------------------------111111");
            }
        });
    }
}
