package Characters.AdvantagesAndWeakness;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Effect {
    private String name;
    private int damagePerTurn;
    private int turnsWithoutAttack;
    private boolean endsOnAttack;

    // Constructor
    public Effect(String name, int damagePerTurn, int turnsWithoutAttack, boolean endsOnAttack) {
        this.name = name;
        this.damagePerTurn = damagePerTurn;
        this.turnsWithoutAttack = turnsWithoutAttack;
        this.endsOnAttack = endsOnAttack;
    }

    public void setTurnsWithoutAttack(int turnsWithoutAttack) {
        this.turnsWithoutAttack = turnsWithoutAttack;
    }

    public int getDamagePerTurn() {
        return this.damagePerTurn;
    }

    public int getTurnsWithoutAttack() {
        return this.turnsWithoutAttack;
    }

    public boolean isEndsOnAttack() {
        return this.endsOnAttack;
    }

    public String getName() {
        return this.name;
    }

    public static Effect getRandomEffect() {
        List<Effect> allEffects = Arrays.asList(
                new Effect("Poisoned", 10, 0, false),
                new Effect("Stunned", 0, 2, false),
                new Effect("Burned", 10, 0, false),
                new Effect("Sleeping", 0, 3, true)
        );

        Random random = new Random();
        return allEffects.get(random.nextInt(allEffects.size()));
    }
}