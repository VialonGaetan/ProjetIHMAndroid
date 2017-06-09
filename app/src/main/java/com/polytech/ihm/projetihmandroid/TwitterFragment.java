package com.polytech.ihm.projetihmandroid;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by Vialon Gaetan
 * on 17/05/2017.
 */

public class TwitterFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("IHM_Example")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getContext())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
