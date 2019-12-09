package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class ProjectileDistanceSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public ProjectileDistanceSlider() {
        this.setMin(20);
        this.setMax(300);
        this.setValue(70);
        this.setMajorTickUnit(70);
        this.setMinorTickCount(3);
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
        this.getSliderValue();
        mySliderValue = this.getValue();
    }

    @Override
    public double getSliderValue() {
        return mySliderValue;
    };
}
