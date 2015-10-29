package model;

import java.util.List;

public class Equipment {
    private final EquipmentType type;

    public Equipment(EquipmentType block) {
        type = block;
    }

    public EquipmentType getType() {
        return type;
    }

    public int act(Player currentPlayer, List<RealEstimate> realEstimates, int position) {
        int target = position;
        switch (type){
            case BLOCK:
                realEstimates.get(position).consumeEquipment();
                break;
            case BOMB:
                currentPlayer.skipForTimes(3);
                RealEstimate realEstimate1 = realEstimates.stream().filter(realEstimate -> {
                    EstimateType type = realEstimate.getType();
                    return type != null && type.equals(EstimateType.Hospital);
                }).findFirst().get();
                target = realEstimates.indexOf(realEstimate1);
                realEstimate1.consumeEquipment();
                break;
        }
        return target;
    }

    public enum EquipmentType{
        BOMB, BLOCK
    }
}
