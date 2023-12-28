package com.jmarser.weatherapp_java.main.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    public FragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList != null ? fragmentList.size() : 0;
    }

    public void updateList(List<Fragment> fragments) {
        fragmentList = fragments;
        notifyDataSetChanged();
    }

    public void deleteList(){
        fragmentList.clear();
        notifyDataSetChanged();
    }
}
