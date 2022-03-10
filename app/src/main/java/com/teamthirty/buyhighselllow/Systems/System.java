package com.teamthirty.buyhighselllow.Systems;

import com.teamthirty.buyhighselllow.Entities.*;
import java.util.List;

public class System {
    protected List<Entity> entityList;

    /**
     * Default constructor for a system
     */
    public System() { }
    /** System constructor that takes in a list of entities
     * @param entityList list of entities system is responsible for
     */
    public System(List<Entity> entityList) {
        this.entityList = entityList;
    }
    /**
     * adds entities to the entity list
     * @param entity the entity to be added
     */
    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    /**
     * removes entities from the system list
     * @param entity the entity to be removed
     */
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }
}
