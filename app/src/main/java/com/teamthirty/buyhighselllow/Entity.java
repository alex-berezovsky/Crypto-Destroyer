package com.teamthirty.buyhighselllow;

import java.util.List;

public class Entity {
    protected long id;
    protected List<IComponent> components;

    public void addComponent(IComponent component) {
        components.add(component);
    }

    public void removeComponent(IComponent component) {
        components.remove(component);
    }
}
