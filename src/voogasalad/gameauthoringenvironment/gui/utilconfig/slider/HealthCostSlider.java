package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class HealthCostSlider extends Slider implements SliderConfiguration {

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

    @Override
    public double getSliderValue() {
        return mySliderValue;
    }
}
