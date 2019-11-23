package voogasalad.gameengine.executors.sprites.strategies.upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreePathUpgrade {
    private class TreePathNode {
        private List<TreePathNode> myChildren;
        private int myId;

        private TreePathNode(int id) {
            myChildren = new ArrayList<>();
            myId = id;
        }

        private void addChild(TreePathNode child) {
            myChildren.add(child);
        }

        private TreePathNode getChildAtIndex(int index) {
            return myChildren.get(index);
        }
    }

    public TreePathUpgrade(Map<String, Object> parameters) {

    }
}