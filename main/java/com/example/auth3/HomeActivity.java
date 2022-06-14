package com.example.auth3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.auth3.Adapters.PaginasAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new PaginasAdapter(this));

        TabLayout tabLayout = findViewById(R.id.TabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 : {
                        tab.setText("Voz");
                        tab.setIcon(R.drawable.ic_sonido);
                        break;
                    }
                    case 1 :{
                        tab.setText("Ayudas");
                        tab.setIcon(R.drawable.ic_ayudas);
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();

    }
}