package com.yilmazgokhan.ironsourceads;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.yilmazgokhan.ironsourceads.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements RewardedVideoListener, InterstitialListener, OfferwallListener, BannerListener {

    private static final String APP_KEY = "ecb7d879";
    private static final String TAG = "Custom_Ads_Tag";
    private static final String PLACEMENT_INTERSTITIAL = "DefaultInterstitial";
    private static final String PLACEMENT_REWARDED = "DefaultRewardedVideo";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.initIronSource();
        this.loadAds();
        this.initAdsListener();
        this.initClicks();
    }

    private void initIronSource() {
        //Rewarded Video
        IronSource.init(this, APP_KEY, IronSource.AD_UNIT.REWARDED_VIDEO);
        //Init Interstitial
        IronSource.init(this, APP_KEY, IronSource.AD_UNIT.INTERSTITIAL);
        //Init Offerwall
        IronSource.init(this, APP_KEY, IronSource.AD_UNIT.OFFERWALL);
        //Init Banner
        IronSource.init(this, APP_KEY, IronSource.AD_UNIT.BANNER);
    }

    private void loadAds() {
        //Load Interstitial
        IronSource.loadInterstitial();
        //Check availability for Rewarded Video Ad
        boolean available = IronSource.isRewardedVideoAvailable();
        Log.d(TAG, "isRewardedVideoAvailable: " + available);
        //Banner Ad
        IronSourceBannerLayout banner = IronSource.createBanner(this, ISBannerSize.BANNER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        binding.bannerContainer.addView(banner, 0, layoutParams);
        IronSource.loadBanner(banner);
    }

    private void initAdsListener() {
        IronSource.setRewardedVideoListener(this);
        IronSource.setInterstitialListener(this);
        IronSource.setOfferwallListener(this);
    }

    private void initClicks() {
        binding.btnInterstitial.setOnClickListener(view -> {
            Log.d(TAG, "initClicks: binding.btnInterstitial");
            IronSource.showInterstitial(PLACEMENT_INTERSTITIAL);
        });
        binding.btnRewarded.setOnClickListener(view -> {
            Log.d(TAG, "initClicks: binding.btnRewarded");
            IronSource.showRewardedVideo(PLACEMENT_REWARDED);
        });
        binding.btnOfferwall.setOnClickListener(view -> {
            Log.d(TAG, "initClicks: binding.btnOfferwall");
            IronSource.showOfferwall();
        });
    }

    //region Rewarded Ad
    @Override
    public void onRewardedVideoAdOpened() {
        Log.d(TAG, "onRewardedVideoAdOpened: ");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d(TAG, "onRewardedVideoAdClosed: ");
    }

    @Override
    public void onRewardedVideoAvailabilityChanged(boolean b) {
        Log.d(TAG, "onRewardedVideoAvailabilityChanged: ");
    }

    @Override
    public void onRewardedVideoAdStarted() {
        Log.d(TAG, "onRewardedVideoAdStarted: ");
    }

    @Override
    public void onRewardedVideoAdEnded() {
        Log.d(TAG, "onRewardedVideoAdEnded: ");
    }

    @Override
    public void onRewardedVideoAdRewarded(Placement placement) {
        Log.d(TAG, "onRewardedVideoAdRewarded: ");
    }

    @Override
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "onRewardedVideoAdShowFailed: ");
    }

    @Override
    public void onRewardedVideoAdClicked(Placement placement) {
        Log.d(TAG, "onRewardedVideoAdClicked: ");
    }
    //endregion

    //region Interstitial Ad
    @Override
    public void onInterstitialAdReady() {
        Log.d(TAG, "onInterstitialAdReady: ");
    }

    @Override
    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "onInterstitialAdLoadFailed: ");
    }

    @Override
    public void onInterstitialAdOpened() {
        Log.d(TAG, "onInterstitialAdOpened: ");
    }

    @Override
    public void onInterstitialAdClosed() {
        Log.d(TAG, "onInterstitialAdClosed: ");
    }

    @Override
    public void onInterstitialAdShowSucceeded() {
        Log.d(TAG, "onInterstitialAdShowSucceeded: ");
    }

    @Override
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "onInterstitialAdShowFailed: ");
    }

    @Override
    public void onInterstitialAdClicked() {
        Log.d(TAG, "onInterstitialAdClicked: ");
    }
    //endregion

    //region Offerwall Ad
    @Override
    public void onOfferwallAvailable(boolean b) {
        Log.d(TAG, "onOfferwallAvailable: ");
    }

    @Override
    public void onOfferwallOpened() {
        Log.d(TAG, "onOfferwallOpened: ");
    }

    @Override
    public void onOfferwallShowFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "onOfferwallShowFailed: ");
    }

    @Override
    public boolean onOfferwallAdCredited(int i, int i1, boolean b) {
        Log.d(TAG, "onOfferwallAdCredited: ");
        return false;
    }

    @Override
    public void onGetOfferwallCreditsFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "onGetOfferwallCreditsFailed: ");
    }

    @Override
    public void onOfferwallClosed() {
        Log.d(TAG, "onOfferwallClosed: ");
    }
    //endregion

    //region Banner
    @Override
    public void onBannerAdLoaded() {
        Log.d(TAG, "onBannerAdLoaded: ");
    }

    @Override
    public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "onBannerAdLoadFailed: ");
    }

    @Override
    public void onBannerAdClicked() {
        Log.d(TAG, "onBannerAdClicked: ");
    }

    @Override
    public void onBannerAdScreenPresented() {
        Log.d(TAG, "onBannerAdScreenPresented: ");
    }

    @Override
    public void onBannerAdScreenDismissed() {
        Log.d(TAG, "onBannerAdScreenDismissed: ");
    }

    @Override
    public void onBannerAdLeftApplication() {
        Log.d(TAG, "onBannerAdLeftApplication: ");
    }
    //endregion

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        IronSource.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        IronSource.onPause(this);
    }
}