package com.example.landing;

import java.util.List;

public class GamerForm {


        private long ID;
        private String name;
        private int stepCount;
        private int stepNumber;


        public GamerForm() {

        }
        public GamerForm(long ID,String name,int stepCount, int stepNumber) {
               this.ID = ID;
               this.name=name;
               this.stepCount=stepCount;
               this.stepNumber=stepNumber;
        }
        public long getID() {
            return ID;
        }

        public void setID(long ID) {
            this.ID = ID;
        }
        public int getStepCount() {
            return stepCount;
        }


        public void setStepCount(int ID) {
            this.stepCount = stepCount;
        }

        public String getName() {
        return name;
        }

        public void setName(String name) {
        this.name = name;
        }

        public int getStepNumber() {
            return stepNumber;
        }
        public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
