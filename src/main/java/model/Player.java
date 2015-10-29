package model;

public class Player {
    private int position;
    private int money;

    public Player(int money) {

        this.money = money;
    }

    public Player() {
        this(0);
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
        money-=realEstimate.getBasePrice();
        realEstimate.belongTo(this);
    }
}
