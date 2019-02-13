package es.iti.tecnogochi.core.entity;

import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.model.MyGochis;



public class GochiSatisfaction {

    private int totalFood;
    private int totalDrink;
    private int totalFun;
    private int totalSociety;

    private int satisfaction;

    private MyGochis myGochi;

    public GochiSatisfaction() {
    }

    public void addSatisfaction(Item item) {
        String type = item.getItemType().getName();
        int effect = item.getEffect();
        switch (type) {
            case "COMER":
                totalFood = totalFood + effect;
                break;
            case "BEBER":
                totalDrink = totalDrink + effect;
                break;
            case "DIVERTIR":
                totalFun = totalFun + effect;
                break;
            case "SOCIAL":
                totalSociety = totalSociety + effect;
                break;
            default:
                this.calculateSatisfaction();   
        }
    }

    private void calculateSatisfaction() {
        int sum = totalFood + totalDrink + totalFun + totalSociety;
        satisfaction = sum / 4;
    }

    public int getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(int totalFood) {
        this.totalFood = totalFood;
    }

    public int getTotalDrink() {
        return totalDrink;
    }

    public void setTotalDrink(int totalDrink) {
        this.totalDrink = totalDrink;
    }

    public int getTotalFun() {
        return totalFun;
    }

    public void setTotalFun(int totalFun) {
        this.totalFun = totalFun;
    }

    public int getTotalSociety() {
        return totalSociety;
    }

    public void setTotalSociety(int totalSociety) {
        this.totalSociety = totalSociety;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public MyGochis getMyGochi() {
        return myGochi;
    }

    public void setMyGochi(MyGochis myGochi) {
        this.myGochi = myGochi;
    }

}
