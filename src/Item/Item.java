package Item;

public class Item {
    private String name;
    private int strengthBoost;
    private int defenseBoost;

    public Item(String name, int strengthBoost, int defenseBoost) {
        this.name = name;
        this.strengthBoost = strengthBoost;
        this.defenseBoost = defenseBoost;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrengthBoost() {
        return strengthBoost;
    }

    public void setStrengthBoost(int strengthBoost) {
        this.strengthBoost = strengthBoost;
    }

    public int getDefenseBoost() {
        return defenseBoost;
    }

    public void setDefenseBoost(int defenseBoost) {
        this.defenseBoost = defenseBoost;
    }
}
