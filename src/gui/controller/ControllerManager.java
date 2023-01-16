package gui.controller;

import gui.model.MCModel;

/**
 * Abstract class that manages a controller and its associated model.
 */
public abstract class ControllerManager {
    private MCModel model;

    /**
     * Returns the associated model.
     * @return the associated model
     */
    public MCModel getModel() {
        return model;
    }

    /**
     * Sets the associated model.
     * @param model the model to set
     */
    public void setModel(MCModel model) {
        this.model = model;
    }

    /**
     * Setup method to be implemented by subclasses.
     */
    public abstract void setup();
}
