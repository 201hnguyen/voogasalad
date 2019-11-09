package GamePlayer;

public interface GamePlayerInternalAPI{
    //Visualises the game given the current configuration specified in xml file as well as collection of serializable objects
    void displayGameCurrentState(SerializableObjects <Collection>);

    //Sets up all the event handlers that call corresponding engine methods on invocation
    void initialiseEventHandlers();
}