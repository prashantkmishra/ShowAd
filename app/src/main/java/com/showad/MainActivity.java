package com.showad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.smaato.sdk.banner.ad.BannerAdSize;
import com.smaato.sdk.banner.widget.BannerError;
import com.smaato.sdk.banner.widget.BannerView;
import com.smaato.sdk.core.SmaatoSdk;
import com.smaato.sdk.core.ad.AdDimension;

public class MainActivity extends AppCompatActivity {

    private BannerView.EventListener mEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGDPRConsent();

        BannerView bannerView = findViewById(R.id.bannerView);
        Button loadAdBtn = findViewById(R.id.loadAdBtn);
        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bannerView.loadAd("130635694", BannerAdSize.XX_LARGE_320x50);
            }
        });

        bannerView.setEventListener(new BannerView.EventListener() {
            @Override
            public void onAdLoaded(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdLoaded-----------------------");
                if (mEventListener != null)
                    mEventListener.onAdLoaded(bannerView);
            }

            @Override
            public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {
                Log.e("EventListener", "onAdFailedToLoad------------------" + bannerError.toString());
                if (mEventListener != null)
                    mEventListener.onAdFailedToLoad(bannerView, bannerError);
            }

            @Override
            public void onAdImpression(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdImpression--------------------");
                if (mEventListener != null)
                    mEventListener.onAdImpression(bannerView);
            }

            @Override
            public void onAdClicked(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdClicked------------------------");
                if (mEventListener != null)
                    mEventListener.onAdClicked(bannerView);
            }

            @Override
            public void onAdTTLExpired(@NonNull BannerView bannerView) {
                Log.e("EventListener", "onAdTTLExpired----------------------");
                if (mEventListener != null)
                    mEventListener.onAdTTLExpired(bannerView);
            }
        });


    }

    private void setGDPRConsent() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putString("IABConsent_SubjectToGDPR", "1");
        editor.putString("IABConsent_ParsedVendorConsents", "1");
        editor.putString("IABConsent_ParsedPurposeConsents", "1111100000");
        editor.putString("IABConsent_ConsentString", "BOvyXKmOvyXKmAGABBENC_-AAAAuh7_______9______9uz_Ov_v_f__33e8__9v_l_7_-___u_-33d4-_1vf99yfm1-7ftr3tp_87ues2_Xur__79__3z3_9pxP78k89r7337Mw_v-_v-b7JCON_IwAAA");
        editor.putBoolean("IABConsent_CMPPresent", true);
        editor.apply();
    }

    public void setEventListener(BannerView.EventListener listener) {
        mEventListener = listener;
    }
}
