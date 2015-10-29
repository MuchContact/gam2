package model;

public class RealEstimate {
    private Equipment equipment;

    public void putBlock() {
        equipment = new Equipment(Equipment.EquipmentType.BLOCK);
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
