package com.dainsleif.hartebeest.players;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.dainsleif.hartebeest.helpers.SpriteSheetLoaderJson;
import com.dainsleif.hartebeest.utils.CollisionDetector;

import java.util.HashMap;
import java.util.Map;

public class PlayerMeley extends Player {
    private static final String TEXTURE_PATH = "sprite/player/Meley.png";
    private static final String JSON_PATH = "sprite/player/Meley.json";

    private static int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int damage;
    private int level;
    private int experience;
    private boolean isDead;

    public PlayerMeley(World world, CollisionDetector collisionDetector) {
        super(world, TEXTURE_PATH, JSON_PATH, collisionDetector);
        initializeStats();
        initializeAnimations();
    }

    @Override
    protected void initializeAnimations() {
        SpriteSheetLoaderJson loader = new SpriteSheetLoaderJson(TEXTURE_PATH, JSON_PATH);

        Map<String, Animation<TextureRegion>> animations = new HashMap<>();
        animations.put("up", new Animation<>(0.1f, loader.getFrames("flyTop")));
        animations.put("down", new Animation<>(0.1f, loader.getFrames("flyDown")));
        animations.put("left", new Animation<>(0.1f, loader.getFrames("flyLeft")));
        animations.put("right", new Animation<>(0.1f, loader.getFrames("flyRight")));
        animations.put("cast", new Animation<>(0.1f, loader.getFrames("atkTop")));

        setAnimations(animations);
        setCurrentFrame(animations.get("down").getKeyFrame(0));
        setStateTime(0f);
    }

    private void initializeStats() {
        this.maxHealth = 1500;
        this.health = maxHealth;
        this.maxMana = 50;
        this.mana = maxMana;
        this.damage = 10;
        this.level = 1;
        this.experience = 0;
    }

    public void takeDamage(int amount) {
        health -= amount;
        System.out.println("Meley took " + amount + " damage!");
        if (health <= 0) {
            health = 0;
            die();
        }
    }

    public void useSkill1() {

    }

    public void useSkill2() {

    }

    public void useUltimate() {

    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public boolean useMana(int amount) {
        if (mana >= amount) {
            mana -= amount;
            return true;
        }
        return false;
    }

    public void restoreMana(int amount) {
        mana += amount;
        if (mana > maxMana) {
            mana = maxMana;
        }
    }

    public void gainExperience(int amount) {
        experience += amount;
        // Simple level up system
        int experienceNeeded = level * 100;
        if (experience >= experienceNeeded) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        maxHealth += 10;
        health = maxHealth;
        maxMana += 5;
        mana = maxMana;
        damage += 2;
        experience = 0;
        System.out.println("Meley leveled up to level " + level + "!");
    }

    private void die() {
        System.out.println("Meley has been defeated!");
        // Handle player death (game over, respawn, etc.)
    }

    // Getters and setters
    public static int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public int getDamage() { return damage; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public boolean isDead() { return isDead; }
    public void setDead(boolean dead) { isDead = dead; }

    public void setHealth(int i) {
        health = i;
    }
}
