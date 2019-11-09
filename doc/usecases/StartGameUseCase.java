package useCases;

public class GamePlayerUseCase{
    GamePlayer obj = new GamePlayer();
    obj.loadXML(String xmlPath);
    obj.startGame();
}