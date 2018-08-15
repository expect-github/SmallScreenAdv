package com.hyt.advsmallscreen.ui;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyt.advsmallscreen.R;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.ui.base.BaseActivity;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.SharedUtlis;
import com.hyt.advsmallscreen.utils.ToastTools;
import com.hyt.advsmallscreen.utils.ViewUtil;


public class MoreSettingActivity extends BaseActivity implements View.OnClickListener, PopupWindow.OnDismissListener {

    private SharedUtlis sharedUtlis;
    private ImageView iv_adv_subtract;
    private ImageView iv_face_subtract;
    private ProgressBar pb_adv_;
    private ProgressBar pb_face_;
    private CheckBox cbTimeStart;
    private CheckBox cbTimeEnd;
    private RelativeLayout rlStart;
    private RelativeLayout rlEnd;
    private TextView tv_timeStart;
    private TextView tv_timeSEnd;
    private EditText etThreshold;
    private EditText etFaceSize;
    private EditText etFaceUITime;
    private Button btConfirm;
    private AudioManager audioManager;
    private int advMaxVolume;
    private int faceMaxVolume;
    private int advVolume;
    private int faceVolume;
    private TextView tv_faceVloum;
    private TextView tv_advVloum;
  private   MoreSettingActivity context;
    private ImageView ivAdvPlus;
    private ImageView ivPlusFace;
    private String TAG = getClass().getName();
    private CheckBox cbUseface;
    private RelativeLayout rlPager;
    private CheckBox cbPager;
    private TextView tvTimePager;
    private PopupWindow timePagerWindow;
    private Button bt_reset;
    private CheckBox cbScreen;
    private RelativeLayout rlScreen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more_seeting);
        sharedUtlis = new SharedUtlis(this, Shared.config);
        context = this;
        initview();
        initdata();
    }


    private void initview() {

        ViewUtil viewUtil = new ViewUtil(this);

        iv_adv_subtract = (ImageView) viewUtil.viewFromId(R.id.imageView);
        iv_face_subtract = (ImageView) viewUtil.viewFromId(R.id.imageView_face);

        ivAdvPlus = (ImageView) viewUtil.viewFromId(R.id.iv_adv_plus);
        ivPlusFace = (ImageView) viewUtil.viewFromId(R.id.iv_adv_plus_face);

        pb_adv_ = (ProgressBar) viewUtil.viewFromId(R.id.pb_adv_Volume);
        pb_face_ = (ProgressBar) viewUtil.viewFromId(R.id.pb_adv_Volume_face);


        cbUseface = (CheckBox) viewUtil.viewFromId(R.id.cb_use_id);
        cbPager = (CheckBox) viewUtil.viewFromId(R.id.cb_time_pager);
        cbScreen = (CheckBox) viewUtil.viewFromId(R.id.cb_time_pager_screen);


        rlPager = (RelativeLayout) viewUtil.viewFromId(R.id.rl_time_pager);
        rlScreen = (RelativeLayout) viewUtil.viewFromId(R.id.rl_time_screen_pager);


        tv_advVloum = (TextView) viewUtil.viewFromId(R.id.tv_hint_adv_vloum);
        tv_faceVloum = (TextView) viewUtil.viewFromId(R.id.tv_hint_face_vloum);
        tvTimePager = (TextView) viewUtil.viewFromId(R.id.tv_time_hint_pager);


        etThreshold = (EditText) viewUtil.viewFromId(R.id.et_face_threshold);
        etFaceSize = (EditText) viewUtil.viewFromId(R.id.et_face_size_min);
        etFaceUITime = (EditText) viewUtil.viewFromId(R.id.et_face_ui_time);

        btConfirm = (Button) viewUtil.viewFromId(R.id.bt_confirm);
        bt_reset = (Button) viewUtil.viewFromId(R.id.bt_reset_password);

        bt_reset.setOnClickListener(this);
        iv_adv_subtract.setOnClickListener(this);
        iv_face_subtract.setOnClickListener(this);
        ivAdvPlus.setOnClickListener(this);
        ivPlusFace.setOnClickListener(this);
//        rlStart.setOnClickListener(this);
//        rlEnd.setOnClickListener(this);
        rlPager.setOnClickListener(this);
        rlScreen.setOnClickListener(this);
        btConfirm.setOnClickListener(this);

    }

    private void initdata() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        advMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        faceMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);


        advVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        faceVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);


        advVolume = sharedUtlis.getInt(Shared.advVideoVolume, advVolume);
        faceVolume = sharedUtlis.getInt(Shared.advFaceVolume, faceVolume);


        pb_adv_.setMax(advMaxVolume);
        pb_face_.setMax(faceMaxVolume);

        pb_adv_.setProgress(advVolume);
        pb_face_.setProgress(faceVolume);

        tv_advVloum.setText(advVolume + "");
        tv_faceVloum.setText(faceVolume + "");

//        tv_timeStart.setText(sharedUtlis.getString(Shared.advVideoAudioStart, "08:00"));
//        tv_timeSEnd.setText(sharedUtlis.getString(Shared.advVideoAudioEnd, "18:00"));

        etThreshold.setText(sharedUtlis.getInt(Shared.faceThreshold, 60) + "");
        etFaceUITime.setText(sharedUtlis.getInt(Shared.facetime, 60) + "");
        etFaceSize.setText(sharedUtlis.getInt(Shared.faceSize, 100) + "");


        LogUtil.e(TAG, "advMaxVolume " + advMaxVolume);
        LogUtil.e(TAG, "faceMaxVolume " + faceMaxVolume);

        LogUtil.e(TAG, "advVolume " + advVolume);
        LogUtil.e(TAG, "faceVolume " + faceVolume);
 
       
        cbUseface.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
        cbUseface.setChecked(sharedUtlis.getBoolean(Shared.useFaceAuthen, true));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timePagerWindow != null && timePagerWindow.isShowing()) {
            timePagerWindow.dismiss();
        }
    }

    @Override
    protected void destory() {
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                if (advVolume >= 1) {
                    int progress = --advVolume;
                    pb_adv_.setProgress(progress);
                    tv_advVloum.setText(progress + "");
                } else {
                    pb_adv_.setProgress(0);
                    tv_advVloum.setText(0 + "");
                }

                LogUtil.e(TAG, "advVolume " + advVolume);
                break;

            case R.id.imageView_face:
                if (faceVolume >= 1) {
                    int progress = --faceVolume;
                    pb_face_.setProgress(progress);
                    tv_faceVloum.setText(progress + "");
                } else {
                    pb_face_.setProgress(0);
                    tv_faceVloum.setText(0 + "");
                }
                LogUtil.e(TAG, "faceVolume " + faceVolume);

                break;

            case R.id.iv_adv_plus:
                if (advVolume < advMaxVolume) {
                    int progress = ++advVolume;
                    pb_adv_.setProgress(progress);
                    tv_advVloum.setText(progress + "");
                } else {
                    pb_adv_.setProgress(advMaxVolume);
                    tv_advVloum.setText(advMaxVolume + "");
                }

                LogUtil.e(TAG, "advVolume " + advVolume);

                break;

            case R.id.iv_adv_plus_face:

                if (faceVolume < faceMaxVolume) {
                    int progress = ++faceVolume;
                    pb_face_.setProgress(progress);
                    tv_faceVloum.setText(progress + "");
                } else {
                    pb_face_.setProgress(faceMaxVolume);
                    tv_faceVloum.setText(faceMaxVolume + "");
                }
                LogUtil.e(TAG, "faceVolume " + faceVolume);

                break;
            case R.id.rl_time_start:
                cbTimeStart.setChecked(!cbTimeStart.isChecked());
                break;

            case R.id.rl_time_end:
                cbTimeEnd.setChecked(!cbTimeEnd.isChecked());
                break;
            case R.id.rl_time_pager:
                cbPager.setChecked(!cbPager.isChecked());
                break;

            case R.id.rl_time_screen_pager:
                cbScreen.setChecked(!cbScreen.isChecked());
                break;
            case R.id.bt_reset_password:
                startActivity(new Intent(context, ChangPasswordActivity.class));
                break;

            case R.id.bt_confirm:

                int advProgress = pb_adv_.getProgress();
                int facProgress = pb_face_.getProgress();
                String facesize = etFaceSize.getText().toString();
                String facetime = etFaceUITime.getText().toString();
                String faceThreshold = etThreshold.getText().toString();

                if (cbUseface.isChecked()) {
                    if (facesize != null && !facesize.isEmpty()) {
                        sharedUtlis.putInt(Shared.faceSize, Integer.valueOf(facesize));
                    } else {
                        ToastTools.showLong(context, "请输入人脸面部大小！");
                        return;
                    }

                    if (facetime != null && !facetime.isEmpty()) {
                        if (Integer.valueOf(facetime) < 15) {
                            ToastTools.showLong(context, "你设置的人脸抓取展示时长过小，请重设！");
                            return;
                        } else {
                            sharedUtlis.putInt(Shared.facetime, Integer.valueOf(facetime));
                        }
                    } else {
                        ToastTools.showLong(context, "请输入人脸抓取界面展示时间");
                        return;
                    }

                    if (faceThreshold != null && !faceThreshold.isEmpty()) {
                        sharedUtlis.putInt(Shared.faceThreshold, Integer.valueOf(faceThreshold));
                    } else {
                        ToastTools.showLong(context, "请输入人脸面识别阀值");
                        return;
                    }
                    sharedUtlis.putBoolean(Shared.useFaceAuthen, true);
                } else {
                    sharedUtlis.putBoolean(Shared.useFaceAuthen, false);
                }
                sharedUtlis.putInt(Shared.advVideoVolume, advProgress);
                sharedUtlis.putInt(Shared.advFaceVolume, facProgress);

//                sharedUtlis.putString(Shared.advVideoAudioStart, tv_timeStart.getText().toString());
//                sharedUtlis.putString(Shared.advVideoAudioEnd, tv_timeSEnd.getText().toString());
                finish();
                break;
        }

    }

    @Override
    public void onDismiss() {
//        cbPager.setChecked(false);
//        cbScreen.setChecked(false);
    }


    public String getStartTimeText() {
        return tv_timeStart.getText().toString();
    }

    public String getEndTimeText() {
        return tv_timeSEnd.getText().toString();
    }

    @Override
    public void ui() {
        
    }

    @Override
    public void data() {

    }
}
