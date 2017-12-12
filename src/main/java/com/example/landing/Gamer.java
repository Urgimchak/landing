package com.example.landing;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
@Table(name = "GAMER")
public class Gamer implements Serializable{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "stepCount")
    private int stepCount;
    @Column(name = "steps")
    private String steps;

    protected Gamer(){}

    protected Gamer(long ID,String name, int stepCount,String steps) {
        this.stepCount=stepCount;
        this.ID = ID;
        this.name = name;
        this.steps = steps;
    }
    public long getID() {
        return ID;
    }

    public void setID(int ID) {
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
    public double[] getSteps()
    {

        String[] tokens = this.steps.split(",");
        double res[]=new double[tokens.length];
        for(int i=0;i<tokens.length;i++)
        {
            res[i]=Double.valueOf(tokens[i]);
        }
        return res;
    }
    public void setSteps(String steps) {
        this.steps=steps;
    }
    public static List<Gamer> Sort( List<Gamer> gamers)
    {
        Collections.sort(gamers, new Comparator<Gamer>() {
            public int compare(Gamer o1, Gamer o2) {
                if(o1.getStepCount()-o2.getStepCount()!=0)
                    return o1.getStepCount()-o2.getStepCount();
                else return o1.getName().compareTo(o2.getName());
            }
        });
        return gamers;
    }



}
