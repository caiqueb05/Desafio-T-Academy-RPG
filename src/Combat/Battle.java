package Combat;

import Characters.AdvantagesAndWeakness.Effect;
import Characters.AdvantagesAndWeakness.Skill;
import Characters.Enemy.Enemy;
import Characters.Character;
import Item.Item;
import Item.ItemPool;

import java.util.*;
import java.util.stream.Collectors;

public class Battle {
    static Scanner scanner = new Scanner(System.in);
    private List<Item> loot = new ArrayList<>();
    private static int enemyTurnCounter = 0;

    public static Enemy selectRandomEnemy() {
        Character character = Character.getSelectedCharacter(); // get the selected character

        // Check if a character has been selected
        if (character == null) {
            System.out.println("No character selected. Please select a character before selecting an enemy.");
            return null;
        }

        ArrayList<Enemy> enemies = Enemy.getEnemies();
        if (enemies.isEmpty()) {
            System.out.println("No enemies available.");
            return null;
        }

        // Filter out defeated enemies
        List<Enemy> availableEnemies = enemies.stream()
                .filter(enemy -> !isDead(enemy))
                .collect(Collectors.toList());

        if (availableEnemies.isEmpty()) {
            System.out.println("All enemies have been defeated!");
            System.out.println("Congratulations! You've completed the game! Thank you for playing! Goodbye");
            return null;
        }

        int randomIndex = new Random().nextInt(availableEnemies.size());
        Enemy result = availableEnemies.get(randomIndex);

        // Set the enemy's skill
        Skill enemySkill = result.getSkill();
        if (enemySkill == null) {
            System.out.println("Enemy has no skills.");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Enemy's Skill: " + enemySkill.getName() + ", Type: " + enemySkill.getType() + ", Base Damage: " + enemySkill.getBaseDamage());
        }

        // Generate two random numbers between -45 and 45
        double randomBoostStrength = -45 + new Random().nextInt(91);
        double randomBoostDefense = -45 + new Random().nextInt(91);

        // Get the character's strength and defense
        int characterStrength = Character.getSelectedCharacter().getStrength();
        int characterDefense = Character.getSelectedCharacter().getDefense();

        // Set the enemy's strength based on the character's defense and vice versa
        int newStrength = (int) (characterDefense * (1 + randomBoostStrength / 100));
        int newDefense = (int) (characterStrength * (1 + randomBoostDefense / 100));

        result.setStrength(newStrength);
        result.setDefense(newDefense);
//
//        System.out.println("Enemy's strength has been set to " + newStrength + ", which is " + randomBoostStrength + "% different than the character's defense of " + characterDefense);
//        System.out.println("Enemy's defense has been set to " + newDefense + ", which is " + randomBoostDefense + "% different than the character's strength of " + characterStrength);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("\nThe following enemy was selected for this battle: \nName: " + result.getName() + ", Life Points: " + result.getLifePoints() + ", Strength: " + result.getStrength() + ", Defense: " + result.getDefense() + ", Type: " + result.getType() + ", Xp Points: " + result.getXpPoints() + "\n");

        return result;
    }


    public static void startBattle(Enemy enemy) {
        Character character = Character.getSelectedCharacter(); // get the selected character

        // Print character's stats at the start of the battle
        character.printStats();

        // Get the character's skill
        Skill characterSkill = character.getSkill();

        // Store the character's xp points before the battle
        int initialXpPoints = character.getXpPoints();

        // Check if the character's life points are less than or equal to zero
        if (character.getLifePoints() <= 0) {
            System.out.println("This character cannot start a battle because it has negative life points. Please select another character.");
            return; // end the method prematurely
        }

        // Start the battle
        while (!isDead(character) && !isDead(enemy)) {

            // Apply effects to the character
            applyEffects(character, enemy);

            // Check if the character is stunned
            boolean isStunned = character.getActiveEffects().stream()
                    .anyMatch(effect -> effect.getName().equals("Stunned") && effect.getTurnsWithoutAttack() > 0);

            if (!isStunned) {
                // Character's turn
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("Choose an action: 1 - Attack, 2 - Defend, 3 - Use Skill, 4 - Run Away");
                int action = scanner.nextInt();

                switch (action) {
                    case 1:
                        attack(character, enemy);
                        break;
                    case 2:
                        defend(character);
                        System.out.println("Character's Life Points after defense: " + character.getLifePoints());
                        break;
                    case 3:
                        if (characterSkill != null) {
                            System.out.println("---------------------------------------------------------------------------------------------");
                            System.out.println("\nCharacter has chosen to use a skill.");
                            System.out.println("Character's Skill: " + characterSkill.getName() + ", Type: " + characterSkill.getType() + ", Base Damage: " + characterSkill.getBaseDamage());
                            character.useSkill(enemy, characterSkill); // Character uses the skill
                        } else {
                            System.out.println("Character has no skills.");
                        }
                        break;
                    case 4:
                        runAway(character);
                        return; // end the battle
                    default:
                        System.out.println("Invalid action. Please choose a valid action.");
                        continue; // skip to the next iteration of the loop
                }

//                // Apply effects to the character
//                applyEffects(character, enemy);
            } else {
                System.out.println("Character is stunned and cannot take an action this turn.");
            }

            if (isDead(enemy)) {
                System.out.println("\nEnemy is defeated! Congratulations!!!");
                character.updateXpPoints(enemy.getXpPoints()); // update character's xp points
                int xpGained = character.getXpPoints() - initialXpPoints; // calculate xp gained from the battle
                System.out.println("Character's received " + xpGained + " xp points\n");

                // Find the enemy with the minimum and maximum XP points
                int minXP = Enemy.getEnemies().stream().mapToInt(Enemy::getXpPoints).min().orElse(0);
                int maxXP = Enemy.getEnemies().stream().mapToInt(Enemy::getXpPoints).max().orElse(0);

                // Calculate the percentage of life points to restore based on the enemy's XP points
                double percentage = 0.2 + (double) (enemy.getXpPoints() - minXP) / (maxXP - minXP) * 0.4; // 20% to 60%
                int lifePointsToRestore = (int) (character.getLifePoints() * percentage);

                // Restore character's life points based on the XP points of the enemy
                character.setLifePoints(character.getLifePoints() + lifePointsToRestore);
                System.out.println("Character's life points have been restored by " + lifePointsToRestore + ". Current life points: " + character.getLifePoints());

                break;
            }

            // Enemy's turn
            if (enemyTurn(enemy, character)) {
                System.out.println("The battle has ended because the enemy has run away.");
                return; // End the battle if the enemy has run away
            }

            // Check if the character's life points are less than or equal to zero after the enemy's turn
            if (character.getLifePoints() <= 0) {
                System.out.println("The enemy has won the battle. Please select another character and try again.");
                return; // End the battle if the character is dead
            }
        }

        endBattle(character, enemy);

        character.printStats();
    }

    public static boolean enemyTurn(Enemy enemy, Character character) {
        // Get the character's skill
        Skill enemySkill = enemy.getSkill();
        int action = (int) (Math.random() * 20) + 1; // Randomly select an action (1-10)

        if (action <= 6) {
            System.out.println("\nEnemy has chosen to attack.");
            attack(enemy, character);
        } else if (action <= 12) {
            defend(enemy);
        } else if (action <= 19) {
            System.out.println("\nEnemy has chosen to use a skill.");
            System.out.println("Enemy's's Skill: " + enemySkill.getName() + ", Type: " + enemySkill.getType() + ", Base Damage: " + enemySkill.getBaseDamage());
            enemy.useSkill(character, enemySkill); // Use the enemy's skill
            // Add code here to use an ability
        } else {
            System.out.println("\nEnemy has chosen to run away.");
            return runAway(enemy);
        }

        enemyTurnCounter++;
        if (enemyTurnCounter == 4) {
            applyRandomEffect(character);
            enemyTurnCounter = 0;
        }

        return false;
    }

    public static void applyRandomEffect(Character character) {
        // Only apply a new effect if there are no active effects
        if (character.getActiveEffects().isEmpty()) {
            Effect randomEffect = Effect.getRandomEffect();
            character.addEffect(randomEffect);
            System.out.println(character.getName() + " is now " + randomEffect.getName());
        }
    }

    public static void applyEffects(Character character, Enemy enemy) {
        Iterator<Effect> iterator = character.getActiveEffects().iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            if (isDead(enemy)) {
                iterator.remove();
                System.out.println("The enemy that caused the " + effect.getName() + " effect is dead. The effect has been removed.");
                continue;
            }
            if (effect.getDamagePerTurn() > 0) {
                int oldLifePoints = character.getLifePoints();
                character.setLifePoints(oldLifePoints - effect.getDamagePerTurn());
                System.out.println(character.getName() + " took " + effect.getDamagePerTurn() + " damage from " + effect.getName() + " effect. Current life points: " + character.getLifePoints());
            }
            if (effect.getTurnsWithoutAttack() > 0) {
                // skip character's turn
                effect.setTurnsWithoutAttack(effect.getTurnsWithoutAttack() - 1);
                System.out.println(character.getName() + " is stunned by " + effect.getName() + " effect and will not attack for " + effect.getTurnsWithoutAttack() + " turn(s).");
            }
            if (effect.isEndsOnAttack() || effect.getTurnsWithoutAttack() <= 0) {
                iterator.remove();
                System.out.println(character.getName() + " is no longer under the effect of " + effect.getName() + ".");
            }
        }
    }

    public static int attack(Character character, Enemy enemy) {
        int defense = enemy.getDefense();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("\nIs your turn to attack!");
        if (enemy.isDefending()) {
            defense *= 2;
            System.out.println("Enemy is defending, defense is doubled for this turn!");
        }
        enemy.setDefending(false);
        int damage = character.getStrength() - defense;
        if (damage < 0) {
            damage = 0;
        }

        // Check for critical hit
        double criticalHitChance = 0.2; // 20% chance for a critical hit
        if (Math.random() <= criticalHitChance) {
            System.out.println("Critical hit!");
            int originalDamage = damage;
            damage *= 1.5; // Increase damage by 50%
            System.out.println("Original damage: " + originalDamage + ", Damage after critical hit: " + damage);
        }

        System.out.println("Total damage: " + damage + " (Enemy's Defense: " + defense + " - Character's Strength: " + character.getStrength() + ")"); // print the total damage
        int oldLifePoints = enemy.getLifePoints();
        enemy.setLifePoints(oldLifePoints - damage);
        System.out.println("Enemy's Life Points after attack (" + oldLifePoints + " - " + damage + ") = " + enemy.getLifePoints());
        return damage;
    }

    public static int attack(Enemy enemy, Character character) {
        int defense = character.getDefense();
        System.out.println("Is the turn of the enemy to attack!");
        if (character.isDefending()) {
            defense *= 2;
            System.out.println("Character is defending, defense is doubled for this turn!");
        }
        character.setDefending(false);
        int damage = enemy.getStrength() - defense;
        if (damage < 0) {
            damage = 0;
        }

        // Check for critical hit
        double criticalHitChance = 0.2; // 20% chance for a critical hit
        if (Math.random() <= criticalHitChance) {
            System.out.println("Critical hit!");
            int originalDamage = damage;
            damage *= 1.5; // Increase damage by 50%
            System.out.println("Original damage: " + originalDamage + ", Damage after critical hit: " + damage);
        }

        System.out.println("Total damage: " + damage + " (Character's Defense: " + defense + " - Enemy's Strength: " + enemy.getStrength() + ")"); // print the total damage
        int oldLifePoints = character.getLifePoints();
        character.setLifePoints(oldLifePoints - damage);
        System.out.println("Character's Life Points after enemy attack (" + oldLifePoints + " - " + damage + ") = " + character.getLifePoints());
        return damage;
    }

    public static void defend(Character character) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("\nCharacter has chosen to defend, so the defense will be doubled the next time the enemy chooses attacks.");
        character.setDefending(true);
    }

    public static void defend(Enemy enemy) {
        System.out.println("\nEnemy has chosen to defend, so the defense will be doubled the next time the character chooses attacks.");
        enemy.setDefending(true);
    }

    public static boolean runAway(Enemy enemy) {
        System.out.println("The enemy has run away!");
        Character.getSelectedCharacter().clearEffects();
        return true; // Enemy has run away
    }

    public static boolean runAway(Character character) {
        System.out.println("You have run away!");
        return true; // Character has run away
    }

    public static boolean isDead(Character character) {
        return character != null && character.getLifePoints() <= 0;
    }

    public static boolean isDead(Enemy enemy) {
        return enemy != null && enemy.getLifePoints() <= 0;
    }

    public static void endBattle(Character player, Enemy enemy) {
        if (isDead(enemy)) {
            System.out.println(enemy.getName() + " has been defeated!");
            Item loot = ItemPool.getRandomItem(); // Get a random item from the ItemPool
            player.getInventory().add(loot);
            System.out.println("You have gained the following item: " + loot.getName() + " with Strength Boost: " + loot.getStrengthBoost() + " and Defense Boost: " + loot.getDefenseBoost());

            // Check if the item is already equipped before equipping it
            if (player.getEquippedItem() != null && player.getEquippedItem().getName().equals(loot.getName())) {
                System.out.println("You have already equipped this item.");
            } else {
                player.setStrength(player.getStrength() + loot.getStrengthBoost());
                player.setDefense(player.getDefense() + loot.getDefenseBoost());
                player.setEquippedItem(loot); // Set the item as equipped
                System.out.println("Your strength and defense have been increased by the item!");
            }
        }
    }


}
