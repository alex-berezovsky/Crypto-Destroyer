package com.teamthirty.buyhighselllow.Components;

import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;

public interface ProjectileComponent {
    /**
     * Creates a new projectile
     *
     * @return the projectile created
     */
    public Projectile generateProjectile();

}
