package com.teamthirty.buyhighselllow.Entities.Towers;

import androidx.core.util.Pair;

import com.teamthirty.buyhighselllow.Components.LevelComponent;
import com.teamthirty.buyhighselllow.Components.ProjectileComponent;
import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;

public abstract class Tower implements LevelComponent, ProjectileComponent {
    protected Pair<Integer, Integer> position;
    protected int level;
    protected int fireRate;
    protected int damage;
    protected int upgradeCost;
    protected int range;

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
    public abstract void fireRateIncrease();

    /**
     * To be implemented by each tower individually to increase damage output
     */
    @Override
    public abstract void damageIncrease();

    /**
     * To be implemented by each tower individually to increase the upgrade cost
     */
    @Override
    public abstract void upgradeCostIncrease();

    /**
     * To be implemented by each tower individually to increase the range
     */
    @Override
    public abstract void rangeIncrease();

    /**
     * To be implemented by each tower individually to create a unique projectile
     *
     * @return the created projectile
     */
    @Override
    public Projectile generateProjectile() {
        return null;
    }

    /**
     * Returns the location of the tower
     *
     * @return location of tower
     */
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Returns the level of the tower
     *
     * @return level of tower
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the fire rate of the tower
     *
     * @return fire rate of tower
     */
    public int getFireRate() {
        return fireRate;
    }

    /**
     * Returns the damage output of the tower
     *
     * @return damage output of tower
     */
    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Returns the upgrade cost of the tower
     *
     * @return upgrade cost of the tower
     */
    public int getUpgradeCost() {
        return upgradeCost;
    }
}
