package com.example.whatsappmy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.whatsappmy.Fragments.ChatsFragment;
import com.example.whatsappmy.Fragments.CollsFragments;
import com.example.whatsappmy.Fragments.StatusfFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {

    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return  new ChatsFragment();
            case 1:return  new StatusfFragment();
            case 2 : return new CollsFragments();
            // DEAFULT FRAGMENT
            default: return new ChatsFragment();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = " CHATS";
        }
        if(position == 1){
            title = " STATUS";
        }
        if(position == 2){
            title = " CALLS";
        }


        return title;
    }
}
