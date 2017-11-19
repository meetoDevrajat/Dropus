package com.roger.dropus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.reflect.Field;


public class mai extends AppCompatActivity{
    static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);

                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.pool:
                    transaction.replace(R.id.content, new pool()).commit();
                    return true;
                case R.id.navigation_home:
                    transaction.replace(R.id.content, new home()).commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.content,new Notifications()).commit();
                    return true;


                case R.id.rewards:
                    transaction.replace(R.id.content, new rewards()).commit();
                    return true;

                case R.id.profil:
                    transaction.replace(R.id.content, new Mainprofile()).commit();
                    return true;

            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai);

        getSupportFragmentManager().beginTransaction().replace(R.id.content, new pool()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        mai.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dropus, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.profile) {
            startActivity(new Intent(this, ContactsListActivity.class));
        }
        else if (id == R.id.settings) {

            startActivity(new Intent(this, SettingsActivity.class));
        }
        else if (id == R.id.aboutus) {
            startActivity(new Intent(this, aboutus.class));
        }
        else if (id == R.id.rep) {
            startActivity(new Intent(this, report.class));
        } return super.onOptionsItemSelected(item);
}}
