package voogasalad.gameplayer.gui.components.button;

import voogasalad.gameplayer.gui.PlayerVisualization;

public class ButtonController {

    private PlayerVisualization playerVisualization;

    public ButtonController(PlayerVisualization playerVisualization) {
        this.playerVisualization = playerVisualization;
    }

    public void startButtonAction() { playerVisualization.startButtonAction(); }

    public void pauseButtonAction() { playerVisualization.pauseButtonAction(); }
}
