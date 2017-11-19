

package com.roger.dropus;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;




public class ContactsListActivity extends FragmentActivity implements
        com.roger.dropus.ContactsListFragment.OnContactsInteractionListener {


    private static final String TAG = "ContactsListActivity";

    private ContactDetailFragment mContactDetailFragment;


    private boolean isTwoPaneLayout;


    private boolean isSearchResultView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_mainc);


        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {


            String searchQuery = getIntent().getStringExtra(SearchManager.QUERY);
            ContactsListFragment mContactsListFragment = (ContactsListFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_list);

            isSearchResultView = true;
            mContactsListFragment.setSearchQuery(searchQuery);
            String title = getString(R.string.contacts_list_search_results_title, searchQuery);
            setTitle(title);
        }

        if (isTwoPaneLayout) {
            mContactDetailFragment = (ContactDetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_detail);
        }
    }

    @Override
    public void onContactSelected(Uri contactUri) {
        if (isTwoPaneLayout && mContactDetailFragment != null) {
            mContactDetailFragment.setContact(contactUri);
        } else {
            Intent intent = new Intent(this, ContactDetailActivity.class);
            intent.setData(contactUri);
            startActivity(intent);
        }
    }

    @Override
    public void onSelectionCleared() {
        if (isTwoPaneLayout && mContactDetailFragment != null) {
            mContactDetailFragment.setContact(null);
        }
    }

    @Override
    public boolean onSearchRequested() {
        return !isSearchResultView && super.onSearchRequested();
    }
}
