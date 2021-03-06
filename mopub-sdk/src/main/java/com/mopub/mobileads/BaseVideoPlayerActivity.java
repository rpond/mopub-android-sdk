package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import com.mopub.common.logging.MoPubLog;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.mopub.common.DataKeys.BROADCAST_IDENTIFIER_KEY;
import static com.mopub.mobileads.VastVideoViewController.VAST_VIDEO_CONFIGURATION;

public class BaseVideoPlayerActivity extends Activity {
    static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    public static void startMraid(final Context context, final String videoUrl) {
        final Intent intentVideoPlayerActivity = createIntentMraid(context, videoUrl);
        try {
            context.startActivity(intentVideoPlayerActivity);
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentMraid(final Context context,
            final String videoUrl) {
        final Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, "mraid");
        intentVideoPlayerActivity.putExtra(VIDEO_URL, videoUrl);
        return intentVideoPlayerActivity;
    }

    static void startVast(final Context context,
            final VastVideoConfiguration vastVideoConfiguration,
            final long broadcastIdentifier) {
        final Intent intentVideoPlayerActivity = createIntentVast(context, vastVideoConfiguration, broadcastIdentifier);
        try {
            context.startActivity(intentVideoPlayerActivity);
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentVast(final Context context,
            final VastVideoConfiguration vastVideoConfiguration,
            final long broadcastIdentifier) {
        final Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, "vast");
        intentVideoPlayerActivity.putExtra(VAST_VIDEO_CONFIGURATION, vastVideoConfiguration);
        intentVideoPlayerActivity.putExtra(BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        return intentVideoPlayerActivity;
    }
}
