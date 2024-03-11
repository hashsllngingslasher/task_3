package com.example.robotsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.robotsproject.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Robot robot1;
    private Robot robot2;
    private boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        robot1 = new Robot("Gurren", getRandomValue(500, 1000), getRandomValue(100, 300));
        robot2 = new Robot("Kittan", getRandomValue(500, 1000), getRandomValue(100, 300));

        init();
        startGame();
    }

    private void startGame() {
        binding.btnBattle.setOnClickListener(view -> {
            if (gameOver) {
                recreate();
            }
            makeBattle();
        });
    }

    private void init() {
        binding.animationConfetti.setAnimation(R.raw.confetti);
        binding.powerGurren.setText("Power " + robot1.getPower());
        binding.powerKittan.setText("Power " + robot2.getPower());
        binding.energyGurren.setText("Energy " + robot1.getEnergy());
        binding.energyKittan.setText("Energy " + robot2.getEnergy());
    }

    private void makeBattle() {
        if (robot1.getEnergy() > 0 && robot2.getEnergy() > 0) {

            robot2.setEnergy(robot2.getEnergy() - robot1.getPower());
            robot1.setEnergy(robot1.getEnergy() - robot2.getPower());

            binding.energyGurren.setText("Energy " + robot1.getEnergy());
            binding.energyKittan.setText("Energy " + robot2.getEnergy());

            if (robot1.getEnergy() <= 0) {
                announceWinner(robot1);
                binding.tvWhoWin.setTextColor(Color.RED);
            } else if (robot2.getEnergy() <= 0) {
                announceWinner(robot2);
                binding.tvWhoWin.setTextColor(Color.YELLOW);
            }
        }
    }

    private void announceWinner(Robot winner) {
        binding.tvWhoWin.setText(winner.getName() + " WIN!");
        binding.tvWhoWin.setVisibility(View.VISIBLE);
        binding.imgVersus.setVisibility(View.INVISIBLE);
        binding.animationConfetti.setVisibility(View.VISIBLE);
        binding.btnBattle.setText("Play again");
        binding.energyGurren.setText("Energy 0");
        binding.energyKittan.setText("Energy 0");
        gameOver = true;
    }

    private int getRandomValue(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}