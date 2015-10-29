package model;

public class Equipment {
    private final EquipmentType type;

    public Equipment(EquipmentType block) {
        type = block;
    }

    public EquipmentType getType() {
        return type;
    }
    public enum EquipmentType{
        BLOCK
    }
}
