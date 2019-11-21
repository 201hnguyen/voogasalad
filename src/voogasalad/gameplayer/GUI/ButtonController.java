package voogasalad.gameplayer.GUI;

public class ButtonController {

    private PlayerVisualization playerVisualization;

    public ButtonController(PlayerVisualization playerVisualization) {
        this.playerVisualization = playerVisualization;
    }

    public void startButtonAction() { playerVisualization.startButtonAction(); }

    public void pauseButtonAction() { playerVisualization.pauseButtonAction(); }
}
