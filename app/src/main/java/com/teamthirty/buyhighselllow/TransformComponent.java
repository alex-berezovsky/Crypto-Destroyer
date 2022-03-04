package com.teamthirty.buyhighselllow;

public interface TransformComponent extends IComponent{
    /**
     * Updates the position of the entity
     * @return the updated positon based on speed and current position
     */
    public void updatePosition();
}
