package com.alocar.frontend.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.alocar.frontend.R;
import com.alocar.frontend.listeners.RetrofitListener;
import com.alocar.frontend.models.ErrorObject;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.recycleview.MyDividerItemDecoration;
import com.alocar.frontend.recycleview.adapter.ContactsAdapter;
import com.alocar.frontend.retrofit.ApiServiceProvider;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.util.YouTubeUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

public class Player extends AppCompatActivity
        implements ContactsAdapter.ContactsAdapterListener, RetrofitListener {

    private Toolbar toolbar;
    private YouTubePlayerView mYouTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private YouTubePlayer mYouTubePlayer;

    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;
    private List<Contact> contactList;

    private ListView playListsView;

    private Contact mContactInfo;

    private ApiServiceProvider apiServiceProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        toolbar = (Toolbar) findViewById(R.id.playerToolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Contact contactInfo = (Contact) getIntent().getSerializableExtra("user");
        mContactInfo = contactInfo;

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(contactInfo.getLicencePlate());
                mYouTubePlayer = youTubePlayer;
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        frag.initialize(YouTubeUtil.API_KEY, onInitializedListener);

        recyclerView = findViewById(R.id.recycler_view_player);

        contactList = (List<Contact>) getIntent().getSerializableExtra("videoList");
        mAdapter = new ContactsAdapter(getApplicationContext(), contactList, this);
        mAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        apiServiceProvider = ApiServiceProvider.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        if (item.getItemId() == R.id.player_action_more) {
            apiServiceProvider.saveToFavorite(mContactInfo, this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContactSelected(final Contact contact) {
        mContactInfo.setImage(contact.getImage());
        mContactInfo.setLicencePlate(contact.getLicencePlate());
        mContactInfo.setName(contact.getName());
        mYouTubePlayer.loadVideo(contact.getLicencePlate());
    }

    @Override
    public void onResponseSuccess(GenericResponse responseBody, int apiFlag) {

    }

    @Override
    public void onResponseSuccess(List<Contact> responseBody, int apiFlag) {

    }

    @Override
    public void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag) {

    }
}
