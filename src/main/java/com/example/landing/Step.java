package com.example.landing;

public class Step {
    private String ID;
    private double altitude;
    private double fuelBalance;
    private double speed;
    private double fuelConsump;

    public Step(){

    }
    public Step(String ID,double altitude,double fuelBalance,double speed,double fuelConsump)
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

    public void setID(String stepID) {
        this.ID = stepID;
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
    @Override
    public String toString() {

        return String.format("%s", String.valueOf(fuelConsump));
    }

}
