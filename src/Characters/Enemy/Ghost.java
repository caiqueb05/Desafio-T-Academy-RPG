package Characters.Enemy;

import Characters.AdvantagesAndWeakness.Skill;

import java.util.ArrayList;

public class Ghost extends Enemy {
    private static ArrayList<Ghost> ghosts = new ArrayList<>();

    static {
        Skill electricShock = new Skill("Electric Shock", "Electric", 4);

        Ghost casper = new Ghost("Casper", 100, 12, 5, new Type("Undead"), 30);
        casper.addSkill(electricShock);
        ghosts.add(casper);

        Ghost moaningMyrtle = new Ghost("Moaning Myrtle", 110, 13, 6, new Type("Undead"), 35);
        moaningMyrtle.addSkill(electricShock);
        ghosts.add(moaningMyrtle);

        Ghost nearlyHeadlessNick = new Ghost("Nearly Headless Nick", 115, 14, 7, new Type("Undead"), 40);
        nearlyHeadlessNick.addSkill(electricShock);
        ghosts.add(nearlyHeadlessNick);

        Ghost banshee = new Ghost("Banshee", 120, 15, 8, new Type("Undead"), 45);
        banshee.addSkill(electricShock);
        ghosts.add(banshee);

        Ghost poltergeist = new Ghost("Poltergeist", 130, 16, 9, new Type("Undead"), 50);
        poltergeist.addSkill(electricShock);
        ghosts.add(poltergeist);

        Enemy.addEnemy(casper);
        Enemy.addEnemy(moaningMyrtle);
        Enemy.addEnemy(nearlyHeadlessNick);
        Enemy.addEnemy(banshee);
        Enemy.addEnemy(poltergeist);
    }

    public Ghost() {
        this.skillsList = new ArrayList<>();
    }

    public Ghost(String name, int lifePoints, int strength, int defense, Type type, int xpPoints) {
        super(name, lifePoints, strength, defense, type, xpPoints);
    }

    public void addSkill(Skill skill) {
        this.setSkill(skill);
    }
}
