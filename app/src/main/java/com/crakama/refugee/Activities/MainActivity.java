package com.crakama.refugee.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.crakama.refugee.Auth.LoginActivity;
import com.crakama.refugee.Auth.RegisterActivity;
import com.crakama.refugee.Auth.ResetPasswordActivity;
import com.crakama.refugee.Fragments.NoticeBoardFrag;
import com.crakama.refugee.Fragments.LiveNewsFrag;
import com.crakama.refugee.Fragments.RepatriationChildFrag;
import com.crakama.refugee.Fragments.RepatriationRootFrag;
import com.crakama.refugee.Fragments.TabsFragment;
import com.crakama.refugee.R;

public class MainActivity extends AppCompatActivity implements
        RepatriationRootFrag.OnRepartButtonClickedListener,
        LiveNewsFrag.OnHomeTabFragListener, NoticeBoardFrag.OnDashBoardFragListener ,
        RepatriationChildFrag.RepatriationChildFragListener{


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    private TabsFragment tabsFragment;
//    private PagerSlidingTabStrip tabs;
//    private ViewPager pager;
//    private ClassPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            // withholding the previously created fragment from being created again
//            // On orientation change, it will prevent fragment recreation
//            // its necessary to reserving the fragment stack inside each tab
//            initScreen();
//
//        } else {
//            // restoring the previously created fragment
//            // and getting the reference
//            tabsFragment = (TabsFragment) getSupportFragmentManager().getFragments().get(0);
//        }
//
//
//
//        /** CALL    METHOD */
//        initInstances();



        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            tabsFragment = new TabsFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            tabsFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, tabsFragment).commit();
        }

        initInstances();

    }
//
//    private void initScreen() {
//
//        // Creating the ViewPager container fragment once
//        tabsFragment = new TabsFragment();
//
//        final FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, tabsFragment)
//                .commit();
//    }

    /**
     * Retrieve the currently visible Tab Fragment and propagate the onBackPressed callback
     *
     * @return true = if this fragment and/or one of its associates Fragment can handle the backPress
     */
    /**
     * Only Activity has this special callback method
     * Fragment doesn't have any onBackPressed callback
     *
     * Logic:
     * Each time when the back button is pressed, this Activity will propagate the call to the
     * container Fragment and that Fragment will propagate the call to its each tab Fragment
     * those Fragments will propagate this method call to their child Fragments and
     * eventually all the propagated calls will get back to this initial method
     *
     * If the container Fragment or any of its Tab Fragments and/or Tab child Fragments couldn't
     * handle the onBackPressed propagated call then this Activity will handle the callback itself
     */
    @Override
    public void onBackPressed() {

        if (!tabsFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
            super.onBackPressed();

        } else {
            // tabsFragment handled the back pressed task
            // do not call super
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void itemClicked(int pos, long id) {


//
//        switch (pos) {
//            case 0:
//                //do
//                Toast.makeText(this, "GridView Item NO: ", Toast.LENGTH_LONG).show();
//                break;
//            case 1:
//                AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
//                builderSingle.setIcon(R.drawable.ic_listcamps);
//                builderSingle.setTitle("Select One Name:-");
//                //Create a String array of the course names
//                String[] names = new String[DadaabCamp.camps.length];
//                for (int i = 0; i < names.length; i++) {
//                    names[i] = DadaabCamp.camps[i].getName();
//                }
//
//                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
//                builderSingle.setAdapter(
//                        arrayAdapter,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String strName = arrayAdapter.getItem(which);
//                                AlertDialog.Builder builderInner = new AlertDialog.Builder(
//                                        MainActivity.this);
//                                builderInner.setMessage(strName);
//                                builderInner.setTitle("Your Selected Item is");
//                                builderInner.setPositiveButton(
//                                        "Ok",
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                builderInner.show();
//                            }
//                        });
//                builderSingle.show();
//                break;
//
//
//            case 3:
//                //
//                break;
//            case 4:
//                //
//                break;
//            case 5:
//                //
//                break;
//            case 6:
//                //
//                break;
//        }
    }

    /**
     * CODE FOR NAVIGATION DRAWER
     */

    private void initInstances() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.welcome, R.string.welcome);
        drawerLayout.addDrawerListener(drawerToggle);


 //       NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//
//        if(islogin)
//        {
//            navigationView.getMenu().clear();
//            navigationView.inflateMenu(R.menu.navigation_with_login);
//        } else
//        {
//            navigationView.getMenu().clear();
//            navigationView.inflateMenu(R.menu.navigation_with_logout);
//        }



        //Initializing NavigationView
        navigation = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.navigation_item_1:
                            //Do some thing here
                            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            break;
                        case R.id.navigation_item_5:
                            //Do some thing here
                            break;
                        case R.id.navigation_item_6:
                            //Do some thing here
                            Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                            startActivity(regIntent);
                            break;
                        case R.id.navigation_item_3:
                            //Do some thing here
                            break;
                        case R.id.navigation_item_4:
                            //Do some thing here
                            break;
                        case R.id.navigation_item_7:
                            //Do some thing here
                            Intent resetIntent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                            startActivity(resetIntent);
                            break;
                        case R.id.nv_updatenoticeboard:
                            //Do some thing here
                            Intent noticeBoardIntent = new Intent(MainActivity.this, UpdateNoticeBoard.class);
                            startActivity(noticeBoardIntent);
                            break;
                        case R.id.navigation_item_8:
                            //Do some thing here
                            Intent newsIntent = new Intent(MainActivity.this, UpdateNews.class);
                            startActivity(newsIntent);
                            break;

                    }
                    return false;
                }
            });//end oclick listener



//        if (islogin) {
//            navigation.getMenu().clear();
//            navigation.inflateMenu(R.menu.navigation_with_login);
//            navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(MenuItem menuItem) {
//                    int id = menuItem.getItemId();
//                    switch (id) {
//                        case R.id.navigation_item_1:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_5:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_3:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_4:
//                            //Do some thing here
//                            break;
//
//                    }
//                    return false;
//                }
//            });//end oclick listener
//        } else {
//            navigation.getMenu().clear();
//            navigation.inflateMenu(R.menu.navigation_with_logout);
//            navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(MenuItem menuItem) {
//                    int id = menuItem.getItemId();
//                    switch (id) {
//                        case R.id.navigation_item_1:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_5:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_3:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_4:
//                            //Do some thing here
//                            break;
//
//                    }
//                    return false;
//                }
//            });//end oclick listener
//        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_addcamp) {
            Intent addcampIntent = new Intent(MainActivity.this, UpdateNews.class);
            startActivity(addcampIntent);
            return true;

        }else if(id == R.id.action_followup){
            startActivity(new Intent(this, IssueFollowUp.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public void onRepartBtnClick(int position) {
        // The user selected the headline of an article from the HeadlinesFragment

        // Capture the article fragment from the activity layout
        RepatriationChildFrag repatriationChildFrag = (RepatriationChildFrag)
                getSupportFragmentManager().findFragmentById(R.id.repartDetails_fragment);

        if (repatriationChildFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            repatriationChildFrag.updateArticleView(position);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            RepatriationChildFrag repatriationChildFrag1 = new RepatriationChildFrag();
            Bundle args = new Bundle();
            args.putInt(RepatriationChildFrag.ARG_POSITION, position);
            repatriationChildFrag1.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.container, repatriationChildFrag1);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }


    }
}// Ends Main Class
