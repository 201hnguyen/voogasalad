package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.scene.control.Slider;

public class ImageSpecSlider extends Slider {

    private double mySliderValue;

    public ImageSpecSlider() {
        this.setMin(100);
        this.setMax(500);
        this.setValue(200);
        this.setMajorTickUnit(100);
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
