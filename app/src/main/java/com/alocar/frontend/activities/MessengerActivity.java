package com.alocar.frontend.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import com.alocar.frontend.R;
import com.alocar.frontend.listeners.RetrofitListener;
import com.alocar.frontend.models.ErrorObject;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.recycleview.MyDividerItemDecoration;
import com.alocar.frontend.recycleview.adapter.ContactsAdapter;
import com.alocar.frontend.retrofit.ApiServiceProvider;
import com.alocar.frontend.retrofit.MessengerFlags;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.util.SessionUtil;
import com.alocar.frontend.util.Utils;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessengerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RetrofitListener, ContactsAdapter.ContactsAdapterListener {

    private ApiServiceProvider apiServiceProvider;

    private Toolbar searchToolBar;
    private Toolbar toolbar;
    private View searchAppBarLayout;
    private AppBarLayout appBar;
    private EditText searchEditText;
    private float positionFromRight = 1;

    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;
    private List<Contact> contactList;
    private SearchView searchView;

    private RecyclerView recyclerViewFav;
    private ContactsAdapter mAdapterFav;
    private List<Contact> contactListFav;

    // url to fetch contacts json
    private static final String URL = "https://api.androidhive.info/json/contacts.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        apiServiceProvider = ApiServiceProvider.getInstance(this);

        recyclerView = findViewById(R.id.recycler_view);
        contactList = new ArrayList<>();
        mAdapter = new ContactsAdapter(this, contactList, this);

        appBar = findViewById(R.id.appBar);
        searchAppBarLayout = findViewById(R.id.layout_appbar_search);
        searchEditText = findViewById(R.id.editText_search);
        final MessengerActivity messengerActivity = this;
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                apiServiceProvider.search(s.toString(), messengerActivity);
            }
        });

        initSearchBar();



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        recyclerViewFav = findViewById(R.id.recycler_view_fav);
        contactListFav = new ArrayList<>();
        mAdapterFav = new ContactsAdapter(this, contactListFav, this);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerViewFav.setLayoutManager(mLayoutManager2);
        recyclerViewFav.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFav.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerViewFav.setAdapter(mAdapterFav);

        apiServiceProvider.getFavoriteSongs(SessionUtil.getUid(), this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messenger, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.refresh) {
            apiServiceProvider.getFavoriteSongs(SessionUtil.getUid(), this);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            showSearchBar(positionFromRight);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.logout:
                apiServiceProvider.logout(this);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponseSuccess(GenericResponse responseBody, int apiFlag) {
        if (MessengerFlags.LOGOUT.getFlag() == apiFlag) {
            Intent loginIntent = new Intent(this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }

    @Override
    public void onResponseSuccess(List<Contact> responseBody, int apiFlag) {
        if (MessengerFlags.SEARCH.getFlag() == apiFlag && responseBody != null) {
            for (Contact contact : responseBody) {
                contact.setUserId(SessionUtil.getUid());
            }
            // adding contacts to contacts list
            contactList.clear();
            contactList.addAll(responseBody);

            // refreshing recycler view
            mAdapter.notifyDataSetChanged();
        }

        if (MessengerFlags.GET_FAVORITE.getFlag() == apiFlag && responseBody != null) {
            contactListFav.clear();
            contactListFav.addAll(responseBody);

            // refreshing recycler view
            mAdapterFav.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag) {

    }

    private void initSearchBar() {
        searchToolBar = (Toolbar) findViewById(R.id.toolbar_search);
        if (searchToolBar != null) {
            searchToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            searchAppBarLayout.setVisibility(View.GONE);
            searchToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSearchBar(positionFromRight);
                }
            });
        }
    }

    /**
     * to show the searchAppBarLayout and hide the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void showSearchBar(float positionFromRight) {
        recyclerViewFav.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appBar, "alpha", 0)
        );
        set.setDuration(100).addListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                appBar.setVisibility(View.GONE);
                searchEditText.requestFocus();
                Utils.showKeyBoard(searchEditText);
            }

            @Override
            public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

            }
        });
        set.start();

        // start x-index for circular animation
        int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48)* (0.5f + positionFromRight));
        // start y-index for circular animation
        int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, toolbar.getWidth() - cx);
        int dy = Math.max(cy, toolbar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        final Animator animator;
        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(searchAppBarLayout, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        searchAppBarLayout.setVisibility(View.VISIBLE);
        animator.start();
        // Circular animation declaration end
    }


    /**
     * to hide the searchAppBarLayout and show the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void hideSearchBar(float positionFromRight) {
        recyclerView.setVisibility(View.GONE);
        recyclerViewFav.setVisibility(View.VISIBLE);
        // start x-index for circular animation
        int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48) * (0.5f + positionFromRight));
        // start  y-index for circular animation
        int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, toolbar.getWidth() - cx);
        int dy = Math.max(cy, toolbar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        Animator animator;
        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(searchAppBarLayout, cx, cy, finalRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                searchAppBarLayout.setVisibility(View.GONE);
                Utils.hideKeyBoard(searchEditText);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        animator.start();
        // Circular animation declaration end

        appBar.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appBar, "translationY", 0),
                ObjectAnimator.ofFloat(appBar, "alpha", 1)
        );
        set.setDuration(100).start();

    }

    @Override
    public void onContactSelected(Contact contact) {
        Intent messageIntent = new Intent(this, Player.class);
        messageIntent.putExtra("user", contact);
        messageIntent.putExtra("videoList", (Serializable) contactList);
        startActivity(messageIntent);
        hideSearchBar(positionFromRight);
    }
}
