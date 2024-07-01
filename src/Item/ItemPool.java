package Item;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemPool {
    private static final List<Item> ITEMS = Arrays.asList(
            new Item("Sword of Strength", 10, 0),
            new Item("Shield of Defense", 0, 10),
            new Item("Armor of Power", 5, 5),
            new Item("Potion of Vitality", 3, 3),
            new Item("Sword of Strength", 10, 0),
            new Item("Shield of Defense", 0, 10),
            new Item("Armor of Power", 5, 5),
            new Item("Potion of Vitality", 3, 3),
            new Item("Axe of Fury", 12, 0),
            new Item("Helmet of Wisdom", 0, 8),
            new Item("Boots of Speed", 4, 4),
            new Item("Ring of Health", 2, 6),
            new Item("Bow of Precision", 8, 2),
            new Item("Cloak of Invisibility", 0, 12),
            new Item("Staff of Magic", 10, 1),
            new Item("Gloves of Dexterity", 6, 4),
            new Item("Amulet of Luck", 3, 7),
            new Item("Dagger of Stealth", 7, 3),
            new Item("Shield of Invulnerability", 0, 14),
            new Item("Spear of Destiny", 11, 0),
            new Item("Armor of Invincibility", 5, 6),
            new Item("Potion of Immortality", 4, 5),
            new Item("Hammer of Thunder", 13, 0),
            new Item("Crown of Kings", 0, 13),
            new Item("Sword of the Phoenix", 9, 3),
            new Item("Armor of the Dragon", 6, 5)
    );

    private static final Random RANDOM = new Random();

    public static Item getRandomItem() {
        return ITEMS.get(RANDOM.nextInt(ITEMS.size()));
    }
}
