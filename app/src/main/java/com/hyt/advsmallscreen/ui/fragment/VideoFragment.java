package com.hyt.advsmallscreen.ui.fragment;

import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.hyt.advsmallscreen.R;
import com.hyt.advsmallscreen.domain.adv.AdvControl;
import com.hyt.advsmallscreen.ui.MainActivity;
import com.hyt.advsmallscreen.ui.base.BaseFragment;
import com.hyt.advsmallscreen.video.VideoManager;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class VideoFragment extends BaseFragment {

    private SurfaceView svVideo;
    private VideoManager videoManager;
    private AdvControl advControl;
    @Override
    protected View creatView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video, null);
    }
    @Override
    public void data() {

        advControl =   ((MainActivity) mActivity).getAdvControl ();
        videoManager = new VideoManager(mActivity, svVideo ,advControl);
    }
    @Override
    public void ui() {
        svVideo = mRootView.findViewById(R.id.sv_video);
    }

    public void player() {
        videoManager.player();
    }

    public void pause() {
        videoManager.pause();
    }

    public void stop() {
        videoManager.stop();
    }

    public void reset() {
        videoManager.reset();
    }

    public void restart() {
        videoManager.restart();
    }

    public void init() {
        videoManager.init();
    }

    public void release() {
        videoManager.release();
    }

    public AdvControl getAdvControl() {
        return advControl;
    }

    public void setAdvControl(AdvControl advControl) {
        this.advControl = advControl;
    }
}
