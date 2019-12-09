package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.scene.control.Slider;

public class HealthCostSlider extends Slider {

    private double mySliderValue;

    public HealthCostSlider() {
        this.setMin(0);
        this.setMax(100);
        this.setValue(25);
        this.setMajorTickUnit(25);
        this.setMinorTickCount(3);
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
        this.getSliderValue();
        mySliderValue = this.getValue();
    }

    public double getSliderValue() {
        return mySliderValue;
    }
}
