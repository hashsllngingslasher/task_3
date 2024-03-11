package com.example.robotsproject;

public class Robot {

    private String name;
    private int energy;
    private int power;

    public Robot(String name, int energy, int power) {
        this.name = name;
        this.energy = energy;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

}
