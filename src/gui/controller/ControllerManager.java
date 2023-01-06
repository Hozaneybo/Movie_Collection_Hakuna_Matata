package gui.controller;

import gui.model.MCModel;

public abstract class ControllerManager {
    private MCModel model;

    public MCModel getModel() {
        return model;
    }

    public void setModel(MCModel model) {
        this.model = model;
    }

    public abstract void setup();
}
