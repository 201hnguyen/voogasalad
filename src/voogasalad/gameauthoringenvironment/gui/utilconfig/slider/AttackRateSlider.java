package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class AttackRateSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public AttackRateSlider() {
        this.setMin(0.00);
        this.setMax(1);
        this.setValue(0.25);
        this.setMajorTickUnit(0.25);
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

