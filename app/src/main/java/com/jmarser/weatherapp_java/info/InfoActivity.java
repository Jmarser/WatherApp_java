package com.jmarser.weatherapp_java.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.jmarser.weatherapp_java.R;
import com.jmarser.weatherapp_java.databinding.ActivityInfoBinding;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListeners();
        getVersionApp();
    }

    private void initListeners(){
        binding.btnInfoBack.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
    }

    private void getVersionApp() {
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;

            String versionComplete = "Versión: " + versionCode + " (" + versionName + ")";
            binding.tvVersionApp.setText(versionComplete);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}