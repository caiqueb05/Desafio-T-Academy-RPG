package Characters;

import Characters.AdvantagesAndWeakness.Skill;

import java.util.ArrayList;

public class Warrior extends Character {
    private int meleePoints;
    private static ArrayList<Warrior> warriors = new ArrayList<>();

    static {

        Skill swordSlash = new Skill("Sword Slash", "Ice", 10);

        Warrior theCelestialWarrior = new Warrior("The Celestial Warrior", 103, 12, 9, 13);
        theCelestialWarrior.addSkill(swordSlash);
        warriors.add(theCelestialWarrior);

        Warrior warriorOfLight = new Warrior("Warrior of Light", 110, 9, 11, 12);
        warriorOfLight.addSkill(swordSlash);
        warriors.add(warriorOfLight);

        Warrior kingLeonidas = new Warrior("King Leonidas", 105, 11, 8, 11);
        kingLeonidas.addSkill(swordSlash);
        warriors.add(kingLeonidas);
    }

    public Warrior() {
        this.skillsList = new ArrayList<>();
    }

    public Warrior(String name, int lifePoints, int strenght, int defense, int meleePoints) {
        super(name, lifePoints, strenght, defense);
        this.meleePoints = meleePoints;
    }

    public static void displayWarriors() {
        System.out.println("=============================================================================================");
        for (int i = 0; i < warriors.size(); i++) {
            Warrior warrior = warriors.get(i);

            StringBuilder skills = new StringBuilder();
            for (Skill skill : warrior.getSkillsList()) {
                skills.append(skill.toString()).append(", ");
            }
            if (skills.length() > 0) {
                skills.setLength(skills.length() - 2); // Remove the trailing comma and space
            }

            System.out.print("Index: " + (i + 1) + ", " +
                    "Name: " + warrior.getName() + ", " +
                    "Life Points: " + warrior.getLifePoints() + ", " +
                    "Strength: " + warrior.getStrength() + ", " +
                    "Defense: " + warrior.getDefense() + ", " +
                    "Melee Points: " + warrior.getMeleePoints() + ", " +
                    "Skills: " + skills + "\n" +
                    "---------------------------------------------------------------------------------------------\n");
        }
    }

    public static Warrior selectWarrior(int index) {
        if (index > 0 && index <= warriors.size()) {
            return warriors.get(index - 1);
        } else {
            System.out.println("Invalid index. Please select a valid index.");
            return null;
        }
    }

    public int getMeleePoints() {
        return meleePoints;
    }

    public void setMeleePoints(int meleePoints) {
        this.meleePoints = meleePoints;
    }

    public static void addWarrior(Warrior warrior) {
        warriors.add(warrior);
    }

    public static ArrayList<Warrior> getWarriors() {
        return warriors;
    }

    public static void setWarriors(ArrayList<Warrior> warriors) {
        Warrior.warriors = warriors;
    }
}
