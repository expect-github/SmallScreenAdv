package com.hyt.advsmallscreen.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hyt.advsmallscreen.R;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.ui.base.BaseActivity;
import com.hyt.advsmallscreen.utils.SharedUtlis;
import com.hyt.advsmallscreen.utils.ToastTools;


public class ChangPasswordActivity extends BaseActivity {


    Context context;

    private String TAG = getClass().getName();
    private CheckBox cbUseface;
    private Button btChange;
    private Button btCancle;
    private EditText etOld;
    private EditText etNew;
    private EditText etConfirm;
    private SharedUtlis sharedUtlis;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_password_change);

        context = this;
        initview();
        initdata();
    }

    @Override
    protected void destory() {
        
    }


    private void initview() {


        btChange = (Button) findViewById(R.id.bt_confirm_change);
        btCancle = (Button) findViewById(R.id.bt_back_of);

        etOld = (EditText) findViewById(R.id.et_old_pas);
        etNew = (EditText) findViewById(R.id.et_new_pas);
        etConfirm = (EditText) findViewById(R.id.et_reInput_pas);


    }

    private void initdata() {

        sharedUtlis = new SharedUtlis(context, Shared.config);

        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String old = etOld.getText().toString().trim();
                String newPas = etNew.getText().toString().trim();
                String rePass = etConfirm.getText().toString().trim();

                if (old == null || old.isEmpty()) {
                    ToastTools.showShort(context, "请输入原始密码");
                    return;

                }
                if (newPas == null || newPas.isEmpty()) {

                    ToastTools.showShort(context, "请输入新密码");
                    return;
                }
                if (rePass == null || rePass.isEmpty()) {
                    ToastTools.showShort(context, "请重复输入新密码");
                    return;
                }

                if (old.equals(sharedUtlis.getString(Shared.moreSettingPass,""))){

                        if(newPas.equals(rePass)){

                            ToastTools.showShort(context, "密码修改成功！");
                            sharedUtlis.putString(Shared.moreSettingPass,rePass);

                        }else {
                            ToastTools.showShort(context, "两次输入的新密码不同");
                            return;

                        }

                }else {

                    ToastTools.showShort(context, "输入的原始密码有误");
                    return;
                }

            }
        });


    }

    @Override
    public void ui() {
        
    }

    @Override
    public void data() {

    }
}
