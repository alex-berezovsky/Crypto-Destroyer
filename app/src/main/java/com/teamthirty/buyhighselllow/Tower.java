package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;

public abstract class Tower extends Entity implements LevelComponent, ProjectileComponent{
    protected Pair<Integer, Integer> position;
    protected int level;
    protected int fireRate;
    protected int damage;
    protected int upgradeCost;

    /**
     * Increments level by 1
     */
    @Override
    public void levelUp() {
        level++;
        fireRateIncrease();
        damageIncrease();
        upgradeCostIncrease();
    }

    /**
     * To be implemented by each tower individually to increase fire rate
     */
    @Override
    public void fireRateIncrease() {
    }

    /**
     * To be implemented by each tower individually to increase damage output
     */
    @Override
    public void damageIncrease() {
    }

    /**
     * To be implemented by each tower individually to increase the upgrade cost
     */
    @Override
    public void upgradeCostIncrease() {
    }

    /**
     * To be implemented by each tower individually to create a unique projectile
     * @return the created projectile
     */
    @Override
    public Projectile generateProjectile() {
        return null;
    }

    /**
     * Returns the location of the tower
     * @return location of tower
     */
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Returns the level of the tower
     * @return level of tower
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the fire rate of the tower
     * @return fire rate of tower
     */
    public int getFireRate() {
        return fireRate;
    }

    /**
     * Returns the damage output of the tower
     * @return damage output of tower
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the upgrade cost of the tower
     * @return upgrade cost of the tower
     */
    public int getUpgradeCost() {
        return upgradeCost;
    }
}
