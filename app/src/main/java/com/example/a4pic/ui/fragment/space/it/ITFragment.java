package com.example.a4pic.ui.fragment.space.it;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a4pic.R;
import com.example.a4pic.databinding.FragmentITBinding;
import com.example.a4pic.ui.Injector;
import com.example.a4pic.ui.fragment.space.base.BaseFragment;
import com.example.a4pic.ui.presenter.Presenter;

import es.dmoral.toasty.Toasty;


public class ITFragment extends BaseFragment<FragmentITBinding> implements Presenter.View{

    private Presenter presenter;
    private String it;

    @Override
    protected FragmentITBinding getBinding() {
        return FragmentITBinding.inflate(getLayoutInflater());
    }
    @Override
    public void setupUI() {
        initPresenter();

    }
    private void initPresenter() {
        presenter = Injector.getPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void setupObservers() {
        clickButton();
    }
    private void initEd() {
        it = binding.edText.getText().toString().trim();
    }

    private void clickButton() {
        binding.btnNext.setOnClickListener(v ->{
            initEd();
            presenter.it(it);
        });
    }

    @Override
    public void toast() {
        Toasty.custom(requireContext(), "Поздравляю, вы прошли игру",
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_check_24),
                ContextCompat.getColor(requireContext(), android.R.color.black),
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark),
                Toasty.LENGTH_SHORT, true, true).show();
        Toasty.Config.reset();
    }

    @Override
    public void wrongToast(int red) {
        Toasty.custom(requireContext(), "Поздравляю, вы обосрались",
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_perm_scan_wifi_24),
                ContextCompat.getColor(requireContext(), android.R.color.black),
                ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark),
                Toasty.LENGTH_SHORT, true, true).show();
        Toasty.Config.reset();
        binding.edText.setTextColor(red);
    }
}