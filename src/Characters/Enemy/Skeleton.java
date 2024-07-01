package Characters.Enemy;

import Characters.AdvantagesAndWeakness.Skill;

import java.util.ArrayList;

public class Skeleton extends Enemy {
    private static ArrayList<Skeleton> skeletons = new ArrayList<>();

    static {
        Skill icyTouch = new Skill("Icy Touch", "Ice", 3);

        Skeleton jack = new Skeleton("Jack Skellington", 80, 10, 3, new Type("Undead"), 20);
        jack.addSkill(icyTouch);
        skeletons.add(jack);

        Skeleton sans = new Skeleton("Sans", 90, 11, 4, new Type("Undead"), 25);
        sans.addSkill(icyTouch);
        skeletons.add(sans);

        Skeleton papyrus = new Skeleton("Papyrus", 95, 12, 5, new Type("Undead"), 30);
        papyrus.addSkill(icyTouch);
        skeletons.add(papyrus);

        Skeleton skulduggery = new Skeleton("Skulduggery Pleasant", 100, 13, 6, new Type("Undead"), 35);
        skulduggery.addSkill(icyTouch);
        skeletons.add(skulduggery);

        Skeleton bonejangles = new Skeleton("Bonejangles", 105, 14, 7, new Type("Undead"), 40);
        bonejangles.addSkill(icyTouch);
        skeletons.add(bonejangles);

        Enemy.addEnemy(jack);
        Enemy.addEnemy(sans);
        Enemy.addEnemy(papyrus);
        Enemy.addEnemy(skulduggery);
        Enemy.addEnemy(bonejangles);
    }

    public Skeleton() {
        this.skillsList = new ArrayList<>();
    }

    public Skeleton(String name, int lifePoints, int strength, int defense, Type type, int xpPoints) {
        super(name, lifePoints, strength, defense, type, xpPoints);
    }

    public void addSkill(Skill skill) {
        this.setSkill(skill);
    }
}
