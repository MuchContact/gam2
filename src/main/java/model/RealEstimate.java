package model;

public class RealEstimate {
    private Equipment equipment;
    private int basePrice;
    private EstimateType type;
    private Player owner;
    private int totalPrice;

    public RealEstimate(int basePrice) {
        this.type = EstimateType.VaccantLand;
        this.basePrice = basePrice;
        this.totalPrice = basePrice;
    }

    public RealEstimate() {
        this(0);
    }

    public RealEstimate(int basePrice, Player owner) {
        this(basePrice);
        this.owner = owner;
    }

    public RealEstimate(int basePrice, Player owner, EstimateType type, int totalPrice) {
        this(basePrice, owner);
        this.type = type;
        this.totalPrice = totalPrice;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void upgradeTo(EstimateType type) {
        this.totalPrice += basePrice;
        this.type = type;
    }

    public void reset() {
        totalPrice = basePrice;
        type = EstimateType.VaccantLand;
        owner = null;
    }
}
