package com.teamthirty.buyhighselllow.Entities.Towers;

import androidx.core.util.Pair;

import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;

public class CryptoWhale extends Tower {
    private int damageAdder;

    /**
     * Creates a new CryptoWhale Tower
     *
     * @param position of tower on the screen
     */
    public CryptoWhale(Pair<Integer, Integer> position) {
        this.position = position;
        level = 1;
        damageAdder = 1;
        fireRate = 30;
        damage = 15;
        upgradeCost = 450;
    }

    /**
     * Adds 20 to the current fire rate
     */
    @Override
    public void fireRateIncrease() {
        fireRate += 20;
    }

    /**
     * Multiplies the current damage by 16
     */
    @Override
    public void damageIncrease() {
        damage *= 16;
        damageAdder += 1;
    }

    /**
     * Multiplies the current upgrade cost by 12
     */
    @Override
    public void upgradeCostIncrease() {
        upgradeCost *= 12;
    }

    /**
     * Increases the current range by 15
     */
    @Override
    public void rangeIncrease() {
        range += 15;
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

    /**
     * Returns damage adder
     *
     * @return damage adder
     */
    public int getDamageAdder() {
        return damageAdder;
    }
}
