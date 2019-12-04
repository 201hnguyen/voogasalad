//public class GameManager {
//    private LevelManager myCurrentLevelManager;
//    private List<LevelManager> myLevelManagers;
//    private State myState;
//    public static void COLLISION_MAP = "resources/collision_map";
//
//    public GameStateObject getGameStateObject() {
//        myCurrentLevelManager.update(myState);
//        return new GameStateObject(myState)
//    }
//}
//
//public class LevelManager {
//    private CollisionManager myCollisionManager;
//    private List<Sprite> myPresentSprites;
//
//    private void checkCollision() {
//        myCollisionManager.checkCollisions(myPresentSprites);
//    }
//}
//
//public class CollisionManager {
//    public void checkCollisions(List<Sprite> spritesList) {
//        for (Sprite sprite : spritesList) {
//            //checks collision and invokes appropriate method from map (e.g., decrease health);
//        }
//    }
//}
//
//public class Sprite {
//    Health myHealth;
//
//    public void alterHealth();
//
//}
//
//    public Interface Health {
//public void alterHealth(int value);
//        }
//
//public class FiniteHealth implements Health {
//    int myHealthValue;
//
//    public void alterHealth(int value) {
//        myHealthValue--;
//    }
//}