package com.example.landing;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
public class MainController {
    @Autowired
    GamerRepository repository;

    private static List<Step> steps = new ArrayList<Step>();
    private boolean dontShow=false;
    private boolean loadGame=false;
    private boolean update=false;
    private long curID;
    private int stepNumber;

    static {
        steps.add(new Step("0",100,140,0,0));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Value("${dont.message}")
    private String dontMessage;

    //стартовая страница
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("ID", toString().valueOf(repository.count()+1));
        return "index";
    }

    //новая игра, берем с формы fuelConsump и делаем ход, проверяем посажен ли корабль
    @RequestMapping(value = {"/newStep"}, method = RequestMethod.POST)
    public String addStep(Model model, //
                           @ModelAttribute("stepForm") StepForm stepForm) {

        double fuelConsump = stepForm.getFuelConsump();

        String ID = String.valueOf(steps.size());

        if (fuelConsump >= 0) {
            double fuelBalance=steps.get(steps.size()-1).getFuelBalance();
            fuelBalance-=fuelConsump;
            double speed=steps.get(steps.size()-1).getSpeed()+(9.8-fuelConsump);
            double altitude=steps.get(steps.size()-1).getAltitude()-speed*1;
            Step newStep = new Step(ID, altitude,fuelBalance,speed,fuelConsump);
            steps.add(newStep);
            if(altitude <=0 && speed<=5 && fuelBalance>=0) {

                return "redirect:/success";
            }
            if((altitude<=0 && speed>5) || fuelBalance<0)
                return "redirect:/fail";


            return "redirect:/newStep";

        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("steps", steps);
        return "/newStep";
    }

    //здесь отправляем в модель steps
    @RequestMapping(value = { "/newStep" }, method = RequestMethod.GET)
    public String showAddStepPage(Model model) {
        dontShow=false;

        if(loadGame) {
            update=true;
            steps.clear();
            double speed=0;
            double fuelConsump=0;
            double altitude=100;
            double fuelBalance=140;
            steps.add(new Step("0",altitude,fuelBalance,speed,fuelConsump));

            Gamer g=repository.findOne(curID);

            double[] s=g.getSteps();

            for(int i=1;i<stepNumber;i++) {
                fuelBalance-=s[i];
                speed=steps.get(steps.size()-1).getSpeed()+(9.8-s[i]);
                steps.add(new Step(String.valueOf(i),steps.get(steps.size()-1).getAltitude()-speed*1,fuelBalance,speed,s[i]));
            }
        }
        String ID1 = String.valueOf(steps.size());
        StepForm stepForm = new StepForm(ID1,0,0,0,0);
        model.addAttribute("stepForm", stepForm);
        model.addAttribute("steps", steps);
        loadGame=false;
        return "/newStep";
    }
    //говорим что проиграл
    @RequestMapping(value = {"/fail"}, method = RequestMethod.GET)
    public String showFail(){
        steps.clear();
        steps.add(new Step("0",100,140,0,0));
        return "fail";
    }

    //сообщаем что выйграл, таблица рейтинга, и добавляем свой результат
    @RequestMapping(value = { "/success" }, method = RequestMethod.GET)
    public String showAddGamerPage(Model model) {
        long ID =repository.count()+1;
        List<Gamer> gamers = new ArrayList<Gamer>();
        for(Gamer g : repository.findAll()){
            gamers.add(g);
        }
        gamers=Gamer.Sort(gamers);
        GamerForm gamerForm = new GamerForm(ID,"",0,0);
        if(dontShow)
            model.addAttribute("dontMessage", dontMessage);
        model.addAttribute("gamerForm", gamerForm);
        model.addAttribute("gamers",gamers);
                return "success";
    }

    @RequestMapping(value = {"/success"}, method = RequestMethod.POST)
    public String addGamer(Model model, //
                           @ModelAttribute("gamerForm") GamerForm gamerForm) {
        String name = gamerForm.getName();
        long ID1;
        if(update){
            ID1=curID;
        }
        else{
            ID1 = repository.count()+1;
        }

        if (name != null && name.length() > 0) {
            String allSteps="";
            for(Step s : steps) {
                allSteps += s.toString()+",";
            }
            Gamer newGamer = new Gamer(ID1, name,steps.size()-1,allSteps);
            repository.save(newGamer);
            steps.clear();
            steps.add(new Step("0",100,140,0,0));
            dontShow=true;
            model.addAttribute("dontMessage", dontMessage);
            model.addAttribute("update", update);
            update=false;
                        return "redirect:/success";
        }
        steps.clear();
        steps.add(new Step("0",100,140,0,0));
        model.addAttribute("errorMessage", errorMessage);
        return "success";
    }

    @RequestMapping(value = { "/loadGame" }, method = RequestMethod.GET)
    public String showLoadPage(Model model) {
        GamerForm gamerForm = new GamerForm(curID,"",0,0);
        model.addAttribute("gamerForm", gamerForm);
        return "/loadGame";
    }

    @RequestMapping(value = {"/loadGame"}, method = RequestMethod.POST)
    public String loadGame(Model model, //
                          @ModelAttribute("gamerForm") GamerForm gamerForm) {
        curID= gamerForm.getID();
        stepNumber=gamerForm.getStepNumber();
        loadGame=true;
        update=true;
        return "redirect:/newStep";
    }
}