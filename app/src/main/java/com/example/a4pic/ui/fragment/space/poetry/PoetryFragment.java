package com.example.a4pic.ui.fragment.space.poetry;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a4pic.R;
import com.example.a4pic.databinding.FragmentPoetryBinding;
import com.example.a4pic.ui.Injector;
import com.example.a4pic.ui.fragment.space.base.BaseFragment;
import com.example.a4pic.ui.presenter.Presenter;

import es.dmoral.toasty.Toasty;

public class PoetryFragment extends BaseFragment<FragmentPoetryBinding> implements Presenter.View {

    private NavController controller;
    private Presenter presenter;
    private String poetry;

    @Override
    protected FragmentPoetryBinding getBinding() {
        return FragmentPoetryBinding.inflate(getLayoutInflater());
    }

    @Override
    public void setupUI() {
        initController();
        initPresenter();
    }

    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
        if (navHostController != null) {

            controller = navHostController.getNavController();
        }
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
        poetry = binding.edText.getText().toString().trim();
    }

    private void clickButton() {
        binding.btnNext.setOnClickListener(v ->{
            initEd();
            presenter.poetry(poetry);
        });
    }
    private void nextFragment(){
        controller.navigate(R.id.ITFragment);
    }

    @Override
    public void toast() {
        nextFragment();
        Toasty.custom(requireContext(), "Поздравляю, вы победили",
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_check_24),
                ContextCompat.getColor(requireContext(), android.R.color.black),
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark),
                Toasty.LENGTH_SHORT, true, true).show();
        Toasty.Config.reset();
    }

    @Override
    public void wrongToast(int red) {
        Toasty.custom(requireContext(), "Поздравляю, вы обосрались",
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_check_24),
                ContextCompat.getColor(requireContext(), android.R.color.black),
                ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark),
                Toasty.LENGTH_SHORT, true, true).show();
        Toasty.Config.reset();
        binding.edText.setTextColor(red);
    }
}