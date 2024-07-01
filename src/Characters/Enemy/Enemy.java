package Characters.Enemy;

import Characters.AdvantagesAndWeakness.Skill;
import Characters.Character;
import Item.Item;
import Item.ItemPool;

import java.util.ArrayList;
import java.util.Scanner;

public class Enemy extends Character {
    private Type type;
    private int xpPoints;
    private boolean defending;
    private Item loot;
    private Skill skill;
    private static ArrayList<Enemy> enemies = new ArrayList<>();
//    private ArrayList<Item> loot = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public Enemy() {
    }

    public Enemy(String name, int lifePoints, int strength, int defense, Type type, int xpPoints) {
        super(name, lifePoints, strength, defense);
        this.type = type;
        this.xpPoints = xpPoints;
        this.loot = ItemPool.getRandomItem();
    }

    public static String createEnemy() {
        System.out.println("Type the name of your enemy: ");
        Enemy enemy = new Enemy("", 0, 0, 0, new Type(""), 0);
        String name = scanner.nextLine();
        enemy.setName(name);
        System.out.println("Type the life points of your enemy: ");
        int lifePoints = scanner.nextInt();
        enemy.setLifePoints(lifePoints);
        System.out.println("Type the strength of your enemy: ");
        int strength = scanner.nextInt();
        enemy.setStrength(strength);
        System.out.println("Type the defense of your enemy: ");
        int defense = scanner.nextInt();
        enemy.setDefense(defense);
        System.out.println("Type the type of your enemy: ");
        String type = scanner.nextLine();
        enemy.setType(new Type(type));
        System.out.println("Type the xp points of your enemy: ");
        int xpPoints = scanner.nextInt();
        enemy.setXpPoints(xpPoints);
        return "You've created a enemy with the following attributes: \n" +
                "Name: " + enemy.getName() + "\n" +
                "Life Points: " + enemy.getLifePoints() + "\n" +
                "Strength: " + enemy.getStrength() + "\n" +
                "Defense: " + enemy.getDefense() + "\n"
                + "Type: " + enemy.getType() + "\n"
                + "Xp Points: " + enemy.getXpPoints() + "\n";
    }

    public static String displayEnemies() {
        String result = "";
        while (true) {
            System.out.println("=============================================================================================");
            System.out.println(enemies.size());
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
//                if (enemy.getLifePoints() != enemy.originalLifePoints) {
//                    System.out.println("Enemy " + enemy.getName() + " has not had their life points restored. Skipping...");
//                    continue;
//                }
                System.out.print("Index: " + (i + 1) + ", " +
                        "Name: " + enemy.getName() + ", " +
                        "Life Points: " + enemy.getLifePoints() + ", " +
                        "Strength: " + enemy.getStrength() + ", " +
                        "Defense: " + enemy.getDefense() + ", " +
                        "Type: " + enemy.getType() + ", " +
                        "Xp Points: " + enemy.getXpPoints());
                System.out.print(", Skills: ");
                System.out.print(enemy.getSkill().getName());

                System.out.println("\n---------------------------------------------------------------------------------------------");
            }
            break;
        }
        return result;
    }

    public void useSkill(Character character, Skill skill) {
        System.out.println(this.getName() + " uses " + skill.getName() + "!");
        int baseDamage = skill.getBaseDamage();

        int strengthDamage = this.strength;

        int additionalDamage = 0;
        if (beats(skill.getType(), character.getSkill().getType())) {
            additionalDamage = 10;
            System.out.println("Type advantage: " + skill.getType() + " beats " + character.getSkill().getType());
        }

        int totalDamage = baseDamage + strengthDamage + additionalDamage;
        System.out.println("Total Damage: " + totalDamage + " (Base Damage: " + baseDamage + " + Strength Damage: " + strengthDamage + " + Additional Damage due to advantage of skill: " + additionalDamage + ")");

        int oldLifePoints = character.getLifePoints();
        character.setLifePoints(oldLifePoints - totalDamage);

        System.out.println("Character's Life Points after skill use (" + oldLifePoints + "-" + totalDamage + ") = " + character.getLifePoints());
    }

//    public void addItemToLoot(Item item) {
//        this.loot.add(item);
//    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getXpPoints() {
        return xpPoints;
    }

    public void setXpPoints(int xpPoints) {
        this.xpPoints = xpPoints;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public static void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

//    public void restoreLifePoints() {
//        this.lifePoints = this.originalLifePoints;
//    }

//    public ArrayList<Item> getLoot() {
//        return loot;
//    }
//
//    public void setLoot(ArrayList<Item> loot) {
//        this.loot = loot;
//    }


    public Item getLoot() {
        return loot;
    }

    public void setLoot(Item loot) {
        this.loot = loot;
    }
}
