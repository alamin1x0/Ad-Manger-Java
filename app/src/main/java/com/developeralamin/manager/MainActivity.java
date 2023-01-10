package com.developeralamin.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

public class MainActivity extends AppCompatActivity {

    private AdManagerAdView mAdManagerAdView;

    private AdManagerInterstitialAd mAdManagerInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdManagerAdView = findViewById(R.id.adManagerAdView);
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        mAdManagerAdView.loadAd(adRequest);
        
        showInterstialAds();
        
        showNativeAds();
    }

    private void showNativeAds() {

        AdLoader adLoader = new AdLoader.Builder(this, "/6499/example/native")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();
                        TemplateView template = findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdManagerAdRequest.Builder().build());
    }

    private void showInterstialAds() {

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        AdManagerInterstitialAd.load(this,"/6499/example/interstitial", adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {

                        interstitialAd.show(MainActivity.this);

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                        mAdManagerInterstitialAd = null;
                    }
                });
    }
}