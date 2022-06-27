package com.example.a4pic.ui.fragment.space.space;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.a4pic.R;
import com.example.a4pic.databinding.FragmentSpaceBinding;
import com.example.a4pic.ui.Injector;
import com.example.a4pic.ui.fragment.space.base.BaseFragment;
import com.example.a4pic.ui.fragment.space.poetry.PoetryFragment;
import com.example.a4pic.ui.presenter.Presenter;

import es.dmoral.toasty.Toasty;

public class SpaceFragment extends BaseFragment<FragmentSpaceBinding> implements Presenter.View {
    private NavController controller;
    private Presenter presenter;
    private String space;

    @Override
    protected FragmentSpaceBinding getBinding() {
        return FragmentSpaceBinding.inflate(getLayoutInflater());
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
        space = binding.edText.getText().toString().trim();
    }

    private void clickButton() {
        binding.btnNext.setOnClickListener(v ->{
            initEd();
            presenter.space(space);
        });
    }

    private void nextFragment(){
        controller.navigate(R.id.poetryFragment);
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
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_perm_scan_wifi_24),
                ContextCompat.getColor(requireContext(), android.R.color.black),
                ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark),
                Toasty.LENGTH_SHORT, true, true).show();
        Toasty.Config.reset();
        binding.edText.setTextColor(red);
    }

}