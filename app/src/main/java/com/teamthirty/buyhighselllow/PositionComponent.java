package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;

public interface PositionComponent extends IComponent{
    /**
     * Updates the position of the entity
     * @return the updated positon based on speed and current position
     */
    public void updatePosition();
}
