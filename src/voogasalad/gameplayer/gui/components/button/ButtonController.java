package voogasalad.gameplayer.gui.components.button;

import voogasalad.gameplayer.gui.PlayerVisualization;

public class ButtonController {

    private PlayerVisualization playerVisualization;

    public ButtonController(PlayerVisualization playerVisualization) {
        this.playerVisualization = playerVisualization;
    }

    public void toggleStart() { playerVisualization.toggleStartAction(); }

    public void toggleMute() { playerVisualization.toggleMuteAction(); }
}
