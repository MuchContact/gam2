package model;

public class RealEstimate {
    private Equipment equipment;
    private int basePrice;
    private final EstimateType type;
    private Player owner;

    public RealEstimate(int basePrice) {
        this.type = EstimateType.VaccantLand;
        this.basePrice = basePrice;
    }

    public RealEstimate() {
        this(0);
    }

    public RealEstimate(int basePrice, Player owner) {
        this(basePrice);
        this.owner = owner;
    }

    public void putBlock() {
        equipment = new Equipment(Equipment.EquipmentType.BLOCK);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Player getOwner() {
        return owner;
    }

    public EstimateType getType() {
        return type;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void belongTo(Player player) {
        this.owner = player;
    }
}
