package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;

public interface ProjectileComponent extends IComponent{
    /**
     * Creates a new projectile
     * @return the projectile created
     */
    public Projectile generateProjectile();

}
