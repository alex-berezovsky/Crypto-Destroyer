package com.teamthirty.buyhighselllow.Entities.Towers;

import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;
import androidx.core.util.Pair;

public class TradingChad extends Tower {
    /**
     * Creates a new TradingChad tower
     * @param position of tower on the screen
     */
    public TradingChad (Pair<Integer, Integer> position) {
        this.position = position;
        level = 1;
        fireRate = 10;
        damage = 10;
        upgradeCost = 100;
    }

    /**
     * Adds 5 to the current fire rate
     */
    @Override
    public void fireRateIncrease() {
        fireRate += 5;
    }

    /**
     * Multiplies the current damage by 2
     */
    @Override
    public void damageIncrease() {
        damage *= 2;
    }

    /**
     * Multiplies the current upgrade cost by 5
     */
    @Override
    public void upgradeCostIncrease() {
        upgradeCost *= 5;
    }

    /**
     * Increases the current range by 5
     */
    @Override
    public void rangeIncrease() {
        range += 5;
    }

    /**
     * Creates a new projectile
     * @return created projectile
     */
    @Override
    public Projectile generateProjectile() {
        return new Projectile(position, damage);
    }
}
