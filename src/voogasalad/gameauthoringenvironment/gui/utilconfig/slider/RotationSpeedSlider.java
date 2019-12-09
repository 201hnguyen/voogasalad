package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;

public class RotationSpeedSlider extends Slider implements SliderConfiguration {

        private double mySliderValue;

        public RotationSpeedSlider() {
            this.setMin(0);
            this.setMax(360);
            this.setValue(90);
            this.setMajorTickUnit(90);
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


