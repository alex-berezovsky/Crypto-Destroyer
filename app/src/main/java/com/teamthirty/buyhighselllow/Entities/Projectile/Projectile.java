package com.teamthirty.buyhighselllow.Entities.Projectile;

import com.teamthirty.buyhighselllow.Components.*;
import com.teamthirty.buyhighselllow.Entities.Entity;
import androidx.core.util.Pair;

public class Projectile extends Entity implements TransformComponent {
    Pair<Integer, Integer> position;
    int damage;
    final int speed = 20;

    /**
     * Constructor for a projectile
     * @param startingPosition the starting location of the projectile
     * @param damage the amount of damage done by the projectile
     */
    public Projectile (Pair<Integer, Integer> startingPosition, int damage) {
        this.position = startingPosition;
        this.damage = damage;
    }

    /**
     * Updates the position of the projectile
     * @return the new position of the projectile
     */
    @Override
    public void updatePosition() {
        Pair<Integer, Integer> newPosition = new Pair<>(position.first + speed, position.second + speed);
        position = newPosition;
    }

    /**
     * Returns the position of the projectile
     * @return position of projectile
     */
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Returns the amount of damage the projectile does
     * @return amount of damage projectile does
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Return the speed of the projectile
     * @return speed of the projectile
     */
    public int getSpeed() {
        return speed;
    }
}
