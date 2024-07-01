package Characters.Enemy;

import Characters.AdvantagesAndWeakness.Skill;

import java.util.ArrayList;

public class Goblin extends Enemy {
    private static ArrayList<Goblin> goblins = new ArrayList<>();

    static {
        Skill goblinPunch = new Skill("Goblin Punch", "Fire", 2);

        Goblin gollun = new Goblin("Gollun", 60, 9, 1, new Type("Monster"), 10);
        gollun.addSkill(goblinPunch);
        goblins.add(gollun);

        Goblin grinn = new Goblin("Grinn", 72, 9, 2, new Type("Monster"), 12);
        grinn.addSkill(goblinPunch);
        goblins.add(grinn);

        Goblin grok = new Goblin("Grok", 65, 9, 2, new Type("Monster"), 15);
        grok.addSkill(goblinPunch);
        goblins.add(grok);

        Goblin smeagol = new Goblin("Smeagol", 70, 10, 3, new Type("Monster"), 16);
        smeagol.addSkill(goblinPunch);
        goblins.add(smeagol);

        Goblin dobby = new Goblin("Dobby", 75, 11, 4, new Type("Monster"), 18);
        dobby.addSkill(goblinPunch);
        goblins.add(dobby);

        Enemy.addEnemy(gollun);
        Enemy.addEnemy(grinn);
        Enemy.addEnemy(grok);
        Enemy.addEnemy(smeagol);
        Enemy.addEnemy(dobby);
    }

    public Goblin() {
        this.skillsList = new ArrayList<>();
    }

    public Goblin(String name, int lifePoints, int strength, int defense, Type type, int xpPoints) {
        super(name, lifePoints, strength, defense, type, xpPoints);
    }

    public static void displayGoblins() {
        for (Goblin goblin : goblins) {
            System.out.println(goblin);
        }
    }

    public static ArrayList<Goblin> getGoblins() {
        return goblins;
    }

    public void setGoblins(ArrayList<Goblin> goblins) {
        this.goblins = goblins;
    }

    public void addSkill(Skill skill) {
        this.setSkill(skill);
    }
}
