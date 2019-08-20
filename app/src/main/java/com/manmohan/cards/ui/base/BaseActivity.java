package com.manmohan.cards.ui.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.manmohan.cards.MainApplication;
import com.manmohan.cards.R;
import com.manmohan.cards.di.component.ActivityComponent;
import com.manmohan.cards.di.component.DaggerActivityComponent;
import com.manmohan.cards.di.module.ActivityModule;
import com.manmohan.cards.utils.CommonTools;

/**
 * Created by manmohan.
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MainApplication) getApplication()).getComponent())
                .build();
    }

    abstract int getLayoutId();

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

}