package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;
import static model.EstimateType.*;

public class Player {
    private int position;
    private int money;
    private boolean alive;
    private int skipTimes;
    private boolean notchoosable;
    private int points;
    private boolean hasGod;
    private List<Equipment> equipments;

    public Player(int money) {

        this.money = money;
        alive = true;
        equipments = new ArrayList<>();
    }

    public Player() {
        this(0);
    }

    public Player(int money, int position) {
        this.money = money;
        this.position = position;
        alive = true;
        equipments = new ArrayList<>();
    }

    public Player(int money, int position, int points) {
        this(money, position);
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void moveTo(int newPos) {
        position = newPos;
        notchoosable = false;
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

    public boolean isAlive() {
        return alive;
    }

    public void markToDie() {
        alive = false;
    }

    public void skipForTimes(int times) {
        skipTimes = times;
    }

    public boolean needToSkip() {
        return skipTimes > 0;
    }

    public void skip() {
        skipTimes--;
    }

    public void own(int money) {
        this.money += money;
    }

    public void notChoosable(boolean notChoosable) {
        this.notchoosable = notChoosable;
    }

    public boolean notChoosable() {
        return notchoosable;
    }

    public int getPoints() {
        return points;
    }

    public void gainPoints(int points) {
        this.points += points;
    }

    public boolean hasGodProtection() {
        return hasGod;
    }

    public void gainGodProtection() {
        hasGod = true;
    }

    public List<Equipment> getEquipments(Equipment.EquipmentType type) {
        return equipments
                .stream()
                .filter(equipment -> equipment.getType().equals(type))
                .collect(toCollection(ArrayList::new));
    }

    public void gain(Equipment equipment) {
        equipments.add(equipment);
        points -= equipment.getPoint();
    }
}
