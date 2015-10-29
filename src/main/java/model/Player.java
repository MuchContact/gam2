package model;

import static model.EstimateType.*;

public class Player {
    private int position;
    private int money;

    public Player(int money) {

        this.money = money;
    }

    public Player() {
        this(0);
    }

    public Player(int money, int position) {
        this.money = money;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void moveTo(int newPos) {
        position = newPos;
    }

    public int getMoney() {
        return money;
    }

    public void buy(RealEstimate realEstimate) {
        money -= realEstimate.getBasePrice();
        realEstimate.belongTo(this);
    }

    public void payForRent(Player owner, RealEstimate realEstimate) {
        int rent = realEstimate.getBasePrice() / 2;
        money -= rent;
        owner.ownRent(rent);
    }

    private void ownRent(int rent) {
        money += rent;
    }

    public void upgrade(RealEstimate realEstimate) {
        EstimateType type = realEstimate.getType();
        if (type.equals(VaccantLand)) {
            money -= realEstimate.getBasePrice();
            realEstimate.upgradeTo(L2);
        } else if (type.equals(L2)) {
            money -= realEstimate.getBasePrice();
            realEstimate.upgradeTo(L3);
        } else if (type.equals(L3)) {
            money -= realEstimate.getBasePrice();
            realEstimate.upgradeTo(L4);
        }
    }

    public boolean isBroken() {
        return money < 0;
    }
}
