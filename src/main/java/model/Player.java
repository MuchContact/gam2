package model;

public class Player {
    private int position;

    public int getPosition() {
        return position;
    }

    public void moveTo(int newPos) {
        position = newPos;
    }
}
