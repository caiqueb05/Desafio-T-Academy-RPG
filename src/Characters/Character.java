package Characters;

import Characters.AdvantagesAndWeakness.Effect;
import Characters.AdvantagesAndWeakness.Skill;
import Characters.Enemy.Enemy;
import Item.Item;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Character {
    static Scanner scanner = new Scanner(System.in);

    protected String name;
    protected int lifePoints;
    protected int strength;
    protected int defense;
    protected int xpPoints = 0;
    protected boolean defending;
    private int level = 1;
    private int xpThreshold = 30;
    private Item equippedItem;
    private List<Effect> activeEffects = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();
    protected ArrayList<Skill> skillsList;
    protected static Character selectedCharacter;

    public Character() {
        // Initialize other attributes...
    }

    public Character(String name, int lifePoints, int strength, int defense) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.strength = strength;
        this.defense = defense;
        this.xpPoints = 0;
        this.skillsList = new ArrayList<>();
    }

    public static String displayCharacters() {
        System.out.println("=============================================================================================");
        System.out.println("===================================Choose your character!====================================");
        System.out.println("=============================================================================================");
        System.out.println("1 - Archer");
        System.out.println("2 - Warrior");
        System.out.println("3 - Mage");
        System.out.println("=============================================================================================");
        return "";
    }

    public static String selectCharacter() {
        String result = "";
        while (true) {

            displayCharacters();

            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        Archer.displayArchers();
                        System.out.println("Select an archer by the index above: ");
                        int archerIndex = scanner.nextInt();
                        Archer archer = Archer.selectArcher(archerIndex);
                        if (archer != null) {
                            result = "You've selected the archer: " + archer.getName();
                            System.out.println("=============================================================================================");
                            System.out.println(result);
                            setSelectedCharacter(archer);
                            return result;
                        }
                        System.out.println("Invalid selection. Please try again.");
                        continue;
                    case 2:
                        Warrior.displayWarriors();
                        System.out.println("Select a warrior by the index above: ");
                        int warriorIndex = scanner.nextInt();
                        Warrior warrior = Warrior.selectWarrior(warriorIndex);
                        if (warrior != null) {
                            result = "You've selected the warrior: " + warrior.getName();
                            System.out.println("=============================================================================================");
                            System.out.println(result);
                            setSelectedCharacter(warrior);
                            return result;
                        }
                        System.out.println("Invalid selection. Please try again.");
                        continue;
                    case 3:
                        Mage.displayMages();
                        System.out.println("Select a mage by the index above: ");
                        int mageIndex = scanner.nextInt();
                        Mage mage = Mage.selectMage(mageIndex);
                        if (mage != null) {
                            result = "You've selected the mage: " + mage.getName();
                            System.out.println("=============================================================================================");
                            System.out.println(result);
                            setSelectedCharacter(mage);
                            return result;
                        }
                        System.out.println("Invalid selection. Please try again.");
                        continue;
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // discard the invalid input
                continue;
            }
        }
    }

    public boolean beats(String skill1, String skill2) {
        return (skill1.equals("Fire") && skill2.equals("Ice")) ||
                (skill1.equals("Ice") && skill2.equals("Electric")) ||
                (skill1.equals("Electric") && skill2.equals("Fire"));
    }

    public static String createCharacter() {
        String result = "";
        while (true) {
            displayCharacters();
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        Archer archer = new Archer();
                        System.out.println("Type the name of your archer: ");
                        String archerName = scanner.next();
                        archer.setName(archerName);

                        while (true) {
                            try {
                                System.out.println("Type the life points of your archer: ");
                                int archerLifePoints = scanner.nextInt();
                                archer.setLifePoints(archerLifePoints);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the strength of your archer: ");
                                int archerStrength = scanner.nextInt();
                                archer.setStrength(archerStrength);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the defense of your archer: ");
                                int archerDefense = scanner.nextInt();
                                archer.setDefense(archerDefense);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the dexterity of your archer: ");
                                int archerDexterity = scanner.nextInt();
                                archer.setDexterity(archerDexterity);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        archer.addSkill(new Skill("Arrow Rain", "Electric", 14));

                        Archer.addArcher(archer);
                        setSelectedCharacter(archer);
                        result = "\nYou've created an archer with the following attributes: " +
                                "Name: " + archer.getName() +
                                ", Life Points: " + archer.getLifePoints() +
                                ", Strength: " + archer.getStrength() +
                                ", Defense: " + archer.getDefense() +
                                ", Dexterity: " + archer.getDexterity() + "\n" +
//                                ", Skills: " + archer.getSkillsList() + "\n" +
                                "---------------------------------------------------------------------------------------------\n";
                        System.out.println(result);
                        Archer.displayArchers();
                        return result;
                    case 2:
                        // Warrior case
                        Warrior warrior = new Warrior();
                        System.out.println("Type the name of your warrior: ");
                        String warriorName = scanner.next();
                        warrior.setName(warriorName);

                        while (true) {
                            try {
                                System.out.println("Type the life points of your warrior: ");
                                int warriorLifePoints = scanner.nextInt();
                                warrior.setLifePoints(warriorLifePoints);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the strength of your warrior: ");
                                int warriorStrength = scanner.nextInt();
                                warrior.setStrength(warriorStrength);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the defense of your warrior: ");
                                int warriorDefense = scanner.nextInt();
                                warrior.setDefense(warriorDefense);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the melee points of your warrior: ");
                                int warriorMeleePoints = scanner.nextInt();
                                warrior.setMeleePoints(warriorMeleePoints);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        warrior.addSkill(new Skill("Sword Slash", "Ice", 10));

                        Warrior.addWarrior(warrior);
                        setSelectedCharacter(warrior);
                        result = "\nYou've created a warrior with the following attributes: " +
                                " Name: " + warrior.getName() +
                                ", Life Points: " + warrior.getLifePoints() +
                                ", Strength: " + warrior.getStrength() +
                                ", Defense: " + warrior.getDefense() +
                                ", Meele Points: " + warrior.getMeleePoints() + "\n";
                        System.out.println(result);
                        Warrior.displayWarriors();
                        return result;
                    case 3:
                        Mage mage = new Mage();
                        System.out.println("Type the name of your mage: ");
                        String mageName = scanner.next();
                        mage.setName(mageName);

                        while (true) {
                            try {
                                System.out.println("Type the life points of your mage: ");
                                int mageLifePoints = scanner.nextInt();
                                mage.setLifePoints(mageLifePoints);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the strength of your mage: ");
                                int mageStrength = scanner.nextInt();
                                mage.setStrength(mageStrength);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the defense of your mage: ");
                                int mageDefense = scanner.nextInt();
                                mage.setDefense(mageDefense);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Type the magic points of your mage: ");
                                int mageMagicPoints = scanner.nextInt();
                                mage.setMagicPoints(mageMagicPoints);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // discard the invalid input
                            }
                        }

                        mage.addSkill(new Skill("Fireball", "Fire", 20));
                        Mage.addMage(mage);
                        setSelectedCharacter(mage);
                        result = "\nYou've created a mage with the following attributes: " +
                                "Name: " + mage.getName() +
                                ", Life Points: " + mage.getLifePoints() +
                                ", Strength: " + mage.getStrength() +
                                ", Defense: " + mage.getDefense() + ", Magic Points: " + mage.getMagicPoints() + "\n";
                        System.out.println(result);
                        Mage.displayMages();
                        return result;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // discard the invalid input
            }
            return result;
        }
    }

    public void useSkill(Enemy enemy, Skill skill) {
        System.out.println(this.getName() + " uses " + skill.getName() + "!");
        int baseDamage = skill.getBaseDamage();

        int strengthDamage = this.strength;

        int additionalDamage = 0;
        if (beats(skill.getType(), enemy.getSkill().getType())) {
            additionalDamage = 10;
            System.out.println("Type advantage: " + skill.getType() + " beats " + enemy.getSkill().getType());
        }

        int totalDamage = baseDamage + strengthDamage + additionalDamage;
        System.out.println("Total Damage: " + totalDamage + " (Base Damage: " + baseDamage + " + Strength Damage: " + strengthDamage + " + Additional Damage due to advantage of skill: " + additionalDamage + ")");

        int oldLifePoints = enemy.getLifePoints();
        enemy.setLifePoints(oldLifePoints - totalDamage);

        System.out.println("Enemy's Life Points after skill use (" + oldLifePoints + "-" + totalDamage + ") = " + enemy.getLifePoints());
    }

    public int calculateDamage(Skill skill, Character character, Enemy enemy) {
        int damage = skill.getBaseDamage() + this.strength;

        for (Skill charSkill : character.getSkillsList()) {
            for (Skill enemySkill : enemy.getSkillsList()) {
                if (beats(charSkill.getType(), enemySkill.getType())) {
                    return damage + 10;
                }
            }
        }

        return damage;
    }

    public void printStats() {
        System.out.print("Character's current stats: \nName: " + this.name + ", Level: " + this.level + ", Life Points: " + this.lifePoints + ", Strength: " + this.strength + ", Defense: " + this.defense + ", Xp Points: " + this.xpPoints);
        System.out.print(", Skills: ");
        for (int i = 0; i < this.skillsList.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            Skill skill = this.skillsList.get(i);
            System.out.print(skill.getName() + ", Type: " + skill.getType() + ", Base Damage: " + skill.getBaseDamage());
        }
        System.out.println("\n");
    }

    public void clearEffects() {
        this.activeEffects.clear();
    }

    public void updateXpPoints(int xpPoints) {
        this.xpPoints += xpPoints;
        while (this.xpPoints >= this.xpThreshold) {
            this.levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.xpThreshold += this.xpThreshold * 0.20; // Double the XP threshold for the next level
        System.out.println("Congratulations! You've leveled up to level " + this.level + "!");
    }

    public void useItem(Item item) {
        if (inventory.remove(item)) {
            this.strength += item.getStrengthBoost();
            this.defense += item.getDefenseBoost();
            System.out.println("Used " + item.getName() + "! Strength and defense have been increased.");
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    public void equipItem(Item item) {
        if (equippedItem != null && equippedItem.getName().equals(item.getName())) {
            System.out.println("You have already equipped this item.");
            return;
        }

        if (inventory.contains(item)) {
            equippedItem = item; // Equip the item
            setStrength(getStrength() + item.getStrengthBoost());
            setDefense(getDefense() + item.getDefenseBoost());
            System.out.println("You have equipped " + item.getName() + "!");
        } else {
            System.out.println("You don't have this item in your inventory.");
        }
    }

    public void addEffect(Effect effect) {
        this.activeEffects.add(effect);
    }

    public List<Effect> getActiveEffects() {
        return this.activeEffects;
    }

    public void updateLifePoints(int damage) {
        this.lifePoints -= damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Skill getSkill() {
        if (!skillsList.isEmpty()) {
            return skillsList.get(0); // return the first skill in the list
        }
        return null; // return null if the list is empty
    }

    public ArrayList<Skill> getSkillsList() {
        return skillsList;
    }

    public void addSkill(Skill skill) {
        this.skillsList.add(skill);
    }

    public static Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public static void setSelectedCharacter(Character selectedCharacter) {
        Character.selectedCharacter = selectedCharacter;
    }

    public int getXpPoints() {
        return xpPoints;
    }

    public void setXpPoints(int xpPoints) {
        this.xpPoints = xpPoints;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setSkillsList(ArrayList<Skill> skillsList) {
        this.skillsList = skillsList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item item) {
        this.equippedItem = item;
    }
}
