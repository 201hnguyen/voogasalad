package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class RotationRangeSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public RotationRangeSlider() {
        this.setMin(0);
        this.setMax(720);
        this.setValue(180);
        this.setMajorTickUnit(180);
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
