package voogasalad.gameengine.executors.sprites.strategies.upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreePathUpgrade {
    private class TreePathNode {
        private List<TreePathNode> myChildren;
        private int myId;
        private TreePathNode myParent;

        private TreePathNode(int id, TreePathNode parent) {
            myChildren = new ArrayList<>();
            myId = id;
            myParent = parent;
        }

        private void addChild(TreePathNode child) {
            myChildren.add(child);
        }

        private TreePathNode getChildAtIndex(int index) {
            return myChildren.get(index);
        }

        private TreePathNode getParent() {
            return myParent;
        }
    }

    public TreePathUpgrade(Map<String, Object> parameters) {

    }
}