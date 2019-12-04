////Use case for killing an enemy and increasing player money by that enemy's bounty
//public class Sprite {
//
//    Health myHealth;
//    boolean isDead;
//    int bounty;
//
//    public void chunkHealth() {
//        myHealth.chunk();
//        if(myHealth.isDead) {
//            isDead = true;
//        }
//    }
//
//    public boolean isDead() {
//        return isDead;
//    }
//
//    public int getBounty() {
//        return bounty;
//    }
//}
//
//public class LevelManager {
//    Money myMoney;
//    List<Sprite> mySprites;
//
//    public void checkForDeath() {
//        List<Sprite> toRemove;
//        for(Sprite s : mySprites) {
//            if(s.isDead()) {
//                handleDeath(s);
//            }
//        }
//        for(Sprite s : toRemove) {
//            mySprites.remove(s);
//        }
//    }
//
//    public void handleDeath(Sprite deadSprite) {
//        int bounty = deadSprite.getBounty();
//        myMoney.increment(bounty);
//    }
//}
