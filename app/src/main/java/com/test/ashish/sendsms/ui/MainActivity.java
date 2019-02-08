package com.test.ashish.sendsms.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.test.ashish.sendsms.MyApplication;
import com.test.ashish.sendsms.R;
import com.test.ashish.sendsms.di.component.DaggerMainActivityComponent;
import com.test.ashish.sendsms.di.component.MainActivityComponent;
import com.test.ashish.sendsms.di.component.MyApplicationComponent;
import com.test.ashish.sendsms.di.module.MainActivityContextModule;
import com.test.ashish.sendsms.di.qualifier.MainActivityContext;
import com.test.ashish.sendsms.di.qualifier.ApplicationContext;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    MainActivityComponent activityComponent;
    Fragment fragment;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @MainActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplicationComponent myApplicationComponent = MyApplication.get(this).getMyApplicationComponent();
        activityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .myApplicationComponent(myApplicationComponent)
                .build();

        activityComponent.injectActivity(this);

        loadFragment(new MessageFragment());

        BottomNavigationView navigation = findViewById(R.id.activity_sales_bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle bundle = new Bundle();

                switch (item.getItemId()) {
                    case R.id.nav_visits:
                        fragment = new ContactFragment();
                        break;

                    case R.id.nav_followup:
                        fragment = new MessageFragment();
                        break;

                    default:
                        break;
                }

                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_sales_frame_layout, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    public static MainActivity get(Fragment fragment){
        return (MainActivity) fragment.getActivity();
    }

    public MainActivityComponent getActivityComponent() {
        return activityComponent;
    }
}