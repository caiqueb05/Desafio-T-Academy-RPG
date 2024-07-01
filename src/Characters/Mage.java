package Characters;

import Characters.AdvantagesAndWeakness.Skill;

import java.util.ArrayList;

public class Mage extends Character {
    private int magicPoints;
    private static ArrayList<Mage> mages = new ArrayList<>();

    static {
        Skill fireball = new Skill("Fireball", "Fire", 20);

        Mage gandalf = new Mage("Gandalf", 120, 15, 12, 110);
        gandalf.addSkill(fireball);
        mages.add(gandalf);

        Mage saruman = new Mage("Saruman", 110, 14, 13, 105);
        saruman.addSkill(fireball);
        mages.add(saruman);

        Mage dumbledore = new Mage("Dumbledore", 115, 16, 14, 120);
        dumbledore.addSkill(fireball);
        mages.add(dumbledore);
    }

    public Mage() {
        this.skillsList = new ArrayList<>();
    }

    public Mage(String name, int lifePoints, int strenght, int defense, int magicPoints) {
        super(name, lifePoints, strenght, defense);
        this.magicPoints = magicPoints;
    }

    public static void displayMages() {
        System.out.println("=============================================================================================");
        for (int i = 0; i < mages.size(); i++) {
            Mage mage = mages.get(i);

            StringBuilder skills = new StringBuilder();
            for (Skill skill : mage.getSkillsList()) {
                skills.append(skill.toString()).append(", ");
            }
            if (skills.length() > 0) {
                skills.setLength(skills.length() - 2); // Remove the trailing comma and space
            }

            System.out.print("Index: " + (i + 1) + ", " +
                    "Name: " + mage.getName() + ", " +
                    "Life Points: " + mage.getLifePoints() + ", " +
                    "Strength: " + mage.getStrength() + ", " +
                    "Defense: " + mage.getDefense() + ", " +
                    "Magic Points: " + mage.getMagicPoints() + ", " +
                    "Skills: " + skills + "\n" +
                    "---------------------------------------------------------------------------------------------\n");
        }
    }

    public static Mage selectMage(int index) {
        if (index >= 0 && index <= mages.size()) {
            return mages.get(index - 1);
        } else {
            System.out.println("Invalid index. Please select a valid index.");
            return null;
        }
    }

    public static void addMage(Mage mage) {
        mages.add(mage);
    }

    public static ArrayList<Mage> getMages() {
        return mages;
    }

    public static void setMages(ArrayList<Mage> mages) {
        Mage.mages = mages;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }
}
