package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class ProjectileSpeedSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public ProjectileSpeedSlider() {
        this.setMin(0);
        this.setMax(3);
        this.setValue(0.67);
        this.setMajorTickUnit(0.67);
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
