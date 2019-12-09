package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class ImageSpecEnemySpeedSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public ImageSpecEnemySpeedSlider() {
        this.setMin(30);
        this.setMax(430);
        this.setValue(130);
        this.setMajorTickUnit(100);
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
