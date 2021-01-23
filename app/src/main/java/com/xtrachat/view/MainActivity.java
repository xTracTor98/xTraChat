package com.xtrachat.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xtrachat.R;
import com.xtrachat.databinding.ActivityMainBinding;
import com.xtrachat.menu.CallsFragment;
import com.xtrachat.menu.ChatFragment;
import com.xtrachat.menu.StatusFragment;
import com.xtrachat.view.contact.ContactsActivity;
import com.xtrachat.view.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpWithViewPager(binding.viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        setSupportActionBar(binding.toolbar);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeFabIcon(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setUpWithViewPager(ViewPager viewPager){
        MainActivity.SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), "Chats");
        adapter.addFragment(new StatusFragment(), "Status");
        adapter.addFragment(new CallsFragment(), "Calls");
        viewPager.setAdapter(adapter);
    }
    private static class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {super(manager);}

        @Override
        public Fragment getItem(int position) {return mFragmentList.get(position);}

        @Override
        public int getCount(){ return mFragmentList.size();}

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){return mFragmentTitleList.get(position);}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                Toast.makeText(MainActivity.this, "Action Search", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_new_group:
                Toast.makeText(MainActivity.this, "Action New Group", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_broadcast:
                Toast.makeText(MainActivity.this, "Action Broadcast", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_imp:
                Toast.makeText(MainActivity.this, "Action Marked Important", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeFabIcon(final int index){
        binding.fabAction.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index){
                    case 0 :
                        binding.fabAction.show();
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.chat));
                        binding.fabAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this, ContactsActivity.class));
                            }
                        });

                        break;
                    case 1 :
                        binding.fabAction.show(); binding.fabAction.setImageDrawable(getDrawable(R.drawable.addstatus)); break;
                    case 2 :   binding.fabAction.show();  binding.fabAction.setImageDrawable(getDrawable(R.drawable.call)); break;
                }

            }
        },400);


    }

}
