
package com.roger.dropus;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.roger.dropus.BuildConfig;
import com.roger.dropus.Utils;


public class ContactDetailActivity extends FragmentActivity {
    private static final String TAG = "ContactDetailActivity";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            if (Utils.hasHoneycomb()) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }
            final Uri uri = getIntent().getData();
            if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(android.R.id.content, ContactDetailFragment.newInstance(uri), TAG);
                ft.commit();
            }
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
