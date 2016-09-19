package com.example.rxpermisson;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by qqq34 on 2016/9/19.
 */

public class PermissionAppCompatActivity extends AppCompatActivity implements PermissionsUtils.PermissionCallbacks{
    private Subscriber<?super Boolean> mSubscriber;
    protected static final int RC_PERM = 64673;
    private String[] mPerms;

    public Observable<Boolean> checkPermission(int resString, String... mPerms) {
        Observable<Boolean> observable = Observable.create(subscriber -> {
            mSubscriber = subscriber;
            this.mPerms = mPerms;
            if (PermissionsUtils.hasPermissions(this, mPerms)) {
                mSubscriber.onNext(true);
            } else {
                PermissionsUtils.requestPermissions(this, getString(resString),
                        RC_PERM, mPerms);
            }
        });

        return observable;
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionsUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PermissionsUtils.SETTINGS_REQ_CODE) {
            //设置返回
            if (mPerms != null) {
                if (PermissionsUtils.hasPermissions(this, mPerms)) {
                    mSubscriber.onNext(true);
                } else {
                    mSubscriber.onNext(false);
                }
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //同意了某些权限可能不是全部
    }

    @Override
    public void onPermissionsAllGranted() {
        mSubscriber.onNext(true);//同意了全部权限的回调
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        boolean isCancel = PermissionsUtils.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.perm_tip),
                R.string.str_setting, R.string.cancel, (dialog, which) ->            mSubscriber.onNext(false), perms);
        if (!isCancel) {
            mSubscriber.onNext(false);
        }

    }
}
