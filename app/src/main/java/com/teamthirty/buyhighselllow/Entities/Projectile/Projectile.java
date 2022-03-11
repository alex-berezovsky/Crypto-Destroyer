package com.teamthirty.buyhighselllow.Entities.Projectile;

import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Components.TransformComponent;
import com.teamthirty.buyhighselllow.Entities.Entity;

public class Projectile extends Entity implements TransformComponent {
    protected Pair<Integer, Integer> position;
    protected int damage;
    protected final int speed = 20;

    /**
     * Constructor for a projectile
     *
     * @param startingPosition the starting location of the projectile
     * @param damage           the amount of damage done by the projectile
     */
    public Projectile(Pair<Integer, Integer> startingPosition, int damage) {
        this.position = startingPosition;
        this.damage = damage;
    }

    /**
     * Updates the position of the projectile
     */
    @Override
    public void updatePosition() {
        position = new Pair<>(position.first + speed, position.second + speed);
    }

    /**
     * Returns the position of the projectile
     *
     * @return position of projectile
     */
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Returns the amount of damage the projectile does
     *
     * @return amount of damage projectile does
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Return the speed of the projectile
     *
     * @return speed of the projectile
     */
    public int getSpeed() {
        return speed;
    }
}
