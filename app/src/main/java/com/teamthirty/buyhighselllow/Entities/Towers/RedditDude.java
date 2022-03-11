package com.teamthirty.buyhighselllow.Entities.Towers;

import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;

public class RedditDude extends Tower {
    protected final int initialCost = 10;

    /**
     * Creates a new RedditDude tower
     *
     * @param position of tower on the screen
     */
    public RedditDude(Pair<Integer, Integer> position) {
        this.position = position;
        level = 1;
        fireRate = 20;
        damage = 10;
        upgradeCost = 250;
    }

    /**
     * Adds 10 to the current fire rate
     */
    @Override
    public void fireRateIncrease() {
        fireRate += 10;
    }

    /**
     * Multiplies the current damage by 4
     */
    @Override
    public void damageIncrease() {
        damage *= 4;
    }

    /**
     * Multiplies the current upgrade cost by 10
     */
    @Override
    public void upgradeCostIncrease() {
        upgradeCost *= 10;
    }

    /**
     * Increases the current range by 10
     */
    @Override
    public void rangeIncrease() {
        range += 10;
    }

    /**
     * Creates a new projectile
     *
     * @return created projectile
     */
    @Override
    public Projectile generateProjectile() {
        return new Projectile(position, damage);
    }
}
