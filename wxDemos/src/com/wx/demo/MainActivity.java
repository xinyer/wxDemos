/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wx.demo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wx.demo.R;
import com.wx.demo.aidl.AIDLFragment;
import com.wx.demo.animation.AnimationFragment;
import com.wx.demo.blur.BlurFragment;
import com.wx.demo.drag.DragFragment;
import com.wx.demo.fallswall.FallsWallFragment;
import com.wx.demo.fallswall.TwoListFragment;
import com.wx.demo.feedback.FeedbackFragment;
import com.wx.demo.highlight.HighLightFragment;
import com.wx.demo.notify.NotifyFragment;
import com.wx.demo.scaleanimation.ScaleFragment;
import com.wx.demo.scrollview.ScrollViewFallWallFragment;
import com.wx.demo.t9.T9Fragment;
import com.wx.demo.test.TestFragment;
import com.wx.demo.wave.WaveFragment;


public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mTitles = getResources().getStringArray(R.array.title_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    Fragment testFragment, waveFragment, feedbackFragment, blurFragment, animFragment, aidlFragment,
    	scaleFragment, notifyFragment, dragFragment, t9Fragment, highLightFragment, fallsWallFragment,
    	twoFragment, scrollFallWallFragment;
    private void selectItem(int position) {
        // update the main content by replacing fragments
    	
    	switch (position) {
    	case 0:{
    		if (testFragment==null) {
				testFragment = new TestFragment();
			}
    		go2Fragment(testFragment);
    	}
    		break;
    	case 1:{
			if (feedbackFragment==null) {
				feedbackFragment = new FeedbackFragment();	
			}
			go2Fragment(feedbackFragment);
		}
			break;
			
		case 2:
			if (waveFragment==null) {
				waveFragment = new WaveFragment();
			}
			go2Fragment(waveFragment);
		
			break;
		case 3:
			if (blurFragment==null) {
				blurFragment = new BlurFragment();
			}
			go2Fragment(blurFragment);
			break;
		
		case 4:
			if (animFragment==null) {
				animFragment = new AnimationFragment();
			}
			go2Fragment(animFragment);
			break;
    	case 5:
    		if (aidlFragment==null) {
				aidlFragment = new AIDLFragment();
			}
    		go2Fragment(aidlFragment);
    		break;
    	case 6:
    		if (scaleFragment==null) {
    			scaleFragment = new ScaleFragment();
    		}
    		go2Fragment(scaleFragment);
    		break;
    	case 7:
    		if (notifyFragment==null) {
				notifyFragment = new NotifyFragment();
			}
    		go2Fragment(notifyFragment);
    		break;
    	case 8:
    		if (dragFragment==null) {
				dragFragment = new DragFragment();
			}
    		go2Fragment(dragFragment);
    		break;
    	case 9:
    		if (t9Fragment==null) {
				t9Fragment = new T9Fragment();
			}
    		go2Fragment(t9Fragment);
    		break;
    	case 10:
    		if (highLightFragment==null) {
				highLightFragment = new HighLightFragment();
			}
    		go2Fragment(highLightFragment);
    		break;
    	case 11:
    		if (fallsWallFragment==null) {
				fallsWallFragment = new FallsWallFragment();
			}
    		go2Fragment(fallsWallFragment);
    		break;
    	case 12:
    		if (twoFragment==null) {
				twoFragment = new TwoListFragment();
			}
    		go2Fragment(twoFragment);
    		break;
    	case 13:
    		if (scrollFallWallFragment==null) {
    			scrollFallWallFragment = new ScrollViewFallWallFragment();
    		}
    		go2Fragment(scrollFallWallFragment);
    		break;
    	}
        

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
    
    void go2Fragment(Fragment fragment) {
    	FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}