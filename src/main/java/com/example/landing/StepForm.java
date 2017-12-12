package com.example.landing;

public class StepForm {
    private String ID;
    private double altitude;
    private double fuelBalance;
    private double speed;
    private double fuelConsump;

    public StepForm() {

    }
    public StepForm(String ID,double altitude,double fuelBalance,double speed,double fuelConsump)
    {
        this.ID = ID;
        this.altitude = altitude;
        this.fuelBalance=fuelBalance;
        this.speed=speed;
        this.fuelConsump=fuelConsump;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public double getFuelBalance() {
        return fuelBalance;
    }

    public void setFuelBalance(double fuelBalance) {
        this.fuelBalance = fuelBalance;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getFuelConsump() {
        return fuelConsump;
    }

    public void setFuelConsump(double fuelConsump) {
        this.fuelConsump = fuelConsump;
    }
}
