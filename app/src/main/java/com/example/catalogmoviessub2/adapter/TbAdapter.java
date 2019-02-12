package com.example.catalogmoviessub2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TbAdapter extends FragmentPagerAdapter {
    private final List<Fragment> ListFragment = new ArrayList<>();
    private final List<String> ListTitle = new ArrayList<>();

    public TbAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ListFragment.get(i);
    }

    @Override
    public int getCount() {
        return ListTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ListTitle.get(position);
    }

    public void AddFragment(Fragment fragment, String Title) {
        ListFragment.add(fragment);
        ListTitle.add(Title);
    }
}
