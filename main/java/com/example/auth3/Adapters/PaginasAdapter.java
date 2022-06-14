package com.example.auth3.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.auth3.Ayudas;
import com.example.auth3.Sonido;

public class PaginasAdapter extends FragmentStateAdapter {
    public PaginasAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0 :
                return new Sonido();
            case 1 :
                return new Ayudas();
            default:
                return new Ayudas();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
