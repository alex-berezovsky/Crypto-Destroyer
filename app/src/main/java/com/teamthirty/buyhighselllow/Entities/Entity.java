package com.teamthirty.buyhighselllow.Entities;

import com.teamthirty.buyhighselllow.Components.IComponent;

import java.util.List;

public class Entity {
    protected long id;
    protected List<IComponent> components;

    /**
     * Adds a component to the component List
     *
     * @param component to be added to list
     */
    public void addComponent(IComponent component) {
        components.add(component);
    }

    /**
     * Removes a component from the component List
     *
     * @param component to be removed from list
     */
    public void removeComponent(IComponent component) {
        components.remove(component);
    }
}
