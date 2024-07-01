package Characters;

import Characters.AdvantagesAndWeakness.Skill;

import java.util.ArrayList;

public class Archer extends Character {
    private int dexterity;
    private static ArrayList<Archer> archers = new ArrayList<>();

    static {
        Skill arrowRain = new Skill("Arrow Rain", "Electric", 14);

        Archer legolas = new Archer("Legolas", 120, 15, 7, 20);
        legolas.addSkill(arrowRain);
        archers.add(legolas);

        Archer hawkeye = new Archer("Hawkeye", 110, 12, 6, 18);
        hawkeye.addSkill(arrowRain);
        archers.add(hawkeye);

        Archer robinWood = new Archer("Robin Wood", 130, 17, 8, 22);
        robinWood.addSkill(arrowRain);
        archers.add(robinWood);
    }

    public Archer() {
        this.skillsList = new ArrayList<>();
    }

    public Archer(String name, int lifePoints, int strength, int defense, int dexterity) {
        super(name, lifePoints, strength, defense);
        this.dexterity = dexterity;
    }

    public static void displayArchers() {
        System.out.println("=============================================================================================");
        for (int i = 0; i < archers.size(); i++) {
            Archer archer = archers.get(i);

            StringBuilder skills = new StringBuilder();
            for (Skill skill : archer.getSkillsList()) {
                skills.append(skill.toString()).append(", ");
            }
            if (skills.length() > 0) {
                skills.setLength(skills.length() - 2); // Remove the trailing comma and space
            }

            System.out.print("Index: " + (i + 1) + ", " +
                    "Name: " + archer.getName() + ", " +
                    "Life Points: " + archer.getLifePoints() + ", " +
                    "Strength: " + archer.getStrength() + ", " +
                    "Defense: " + archer.getDefense() + ", " +
                    "Dexterity: " + archer.getDexterity() + ", " +
                    "Skills: " + skills + "\n" +
                    "---------------------------------------------------------------------------------------------\n");
        }
    }

    public static Archer selectArcher(int index) {
        if (index > 0 && index <= archers.size()) {
            return archers.get(index - 1);
        } else {
            System.out.println("Invalid index. Please select a valid index.");
            return null;
        }
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public static void addArcher(Archer archer) {
        archers.add(archer);
    }

    public ArrayList<Archer> getArchers() {
        return archers;
    }

    public void setArchers(ArrayList<Archer> archers) {
        this.archers = archers;
    }



}
