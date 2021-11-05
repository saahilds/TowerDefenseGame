package com.main.game.gamePane;

import com.main.config.Config;
import com.main.game.GameFlowController;
import com.main.game.common.IndexPosition;
import com.main.game.common.UpdateData;
import com.main.game.entity.EnemyEntity;
import com.main.game.entity.Entity;
import com.main.model.UpdateDataTypeType;
import io.reactivex.observables.ConnectableObservable;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.Stack;
//import java.util.concurrent.Flow;


public class GamePaneWrapper {
    private Pane pane;

    //getter
    public Pane getPane() {
        return pane;
    }

    public PositionMap getPosMap() {
        return posMap;
    }

    public void setPosMap(PositionMap posMap) {
        this.posMap = posMap;
    }

    private PositionMap posMap;

    private int width;
    private int height;
    private int nodeWidth;
    private int nodeHeight;

    private EventHandler<? super MouseEvent> onMouseClickedHandler;

    public void setOnMouseClickedHandler(EventHandler<? super MouseEvent> onMouseClickedHandler) {
        this.onMouseClickedHandler = onMouseClickedHandler;
        pane.setOnMouseClicked(onMouseClickedHandler);
    }

    public EventHandler<? super MouseEvent> getOnMouseClickedHandler() {
        return onMouseClickedHandler;
    }

    private EventHandler<? super MouseEvent> onMouseMovedHandler;

    public void setOnMouseMovedHandler(EventHandler<? super MouseEvent> onMouseMovedHandler) {
        this.onMouseMovedHandler = onMouseMovedHandler;
        pane.setOnMouseMoved(onMouseMovedHandler);
    }

    public EventHandler<? super MouseEvent> getOnMouseMovedHandler() {
        return onMouseMovedHandler;
    }

    private int widthCapacity;

    public int getWidthCapacity() {
        return widthCapacity;
    }

    private int heightCapacity;

    public int getHeightCapacity() {
        return heightCapacity;
    }

    private int maxXidx;

    public int getMaxXidx() {
        return maxXidx;
    }

    private int maxYidx;

    public int getMaxYidx() {
        return maxYidx;
    }

    private GameFlowController gameFlowController;
    private ConnectableObservable<Long> intervalHotObservable;

    public ConnectableObservable<Long> getIntervalHotObservable() {
        return intervalHotObservable;
    }

    public void setIntervalHotObservable(ConnectableObservable<Long> intervalHotObservable) {
        this.intervalHotObservable = intervalHotObservable;
    }

    public GameFlowController getGameFlowController() {
        return gameFlowController;
    }

    public void setGameFlowController(GameFlowController gameFlowController) {
        this.gameFlowController = gameFlowController;
    }

    public GamePaneWrapper(
            Pane pane, int width, int height,
            int nodeWidth, int nodeHeight,
            GameFlowController gameFlowController
    ) {
        this.pane = pane;
        this.width = width;
        this.height = height;
        this.nodeWidth = nodeWidth;
        this.nodeHeight = nodeHeight;
        this.pane.setPrefWidth(width);
        this.pane.setPrefHeight(height);
        this.widthCapacity = (int) Math.floor(this.width / this.nodeWidth);
        this.heightCapacity = (int) Math.floor(this.height / this.nodeHeight);
        this.maxXidx = this.widthCapacity - 1;
        this.maxYidx = this.heightCapacity - 1;
        this.posMap = new PositionMap(this.width, this.height, this.nodeWidth, this.nodeHeight);

        this.gameFlowController = gameFlowController;
        this.intervalHotObservable = this.gameFlowController.getIntervalObservable$();
    }

    public void addNode(Node node) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                getPane().getChildren().add(node);
            }
        });
    }

    public void addNodeWithXidxYidx(int xIdx, int yIdx, Entity node) {
        setAtIdx(xIdx, yIdx, getTranslatedNodeWithIdx(xIdx, yIdx, node));
    }

    public void addNodeWithIndexPosition(IndexPosition position, Entity node) {
        int xIdx = position.getX();
        int yIdx = position.getY();
        addNodeWithXidxYidx(xIdx, yIdx, node);
    }

    public void setAtIdx(int xIdx, int yIdx, Entity posTranslatedNode) {
        // add onto ACTURAL screen
        addNode(posTranslatedNode);
        // add into Virtual position map
        this.posMap.setAtIdx(xIdx, yIdx, posTranslatedNode);
    }

    public Entity getTranslatedNodeWithIdx(int xIdx, int yIdx, Entity node) {
        node.setPosIdx(xIdx, yIdx);
        node.setTranslateY(nodeHeight * yIdx);
        node.setTranslateX(nodeWidth * xIdx);
        return node;
    }

    public Entity getNodeWithIndexPosition(IndexPosition position) {
        return posMap.getAtPos(position);
    }

    public IndexPosition getIdxWithPos(double xPos, double yPos) {
        return new IndexPosition(
                (int) xPos / nodeWidth,
                (int) yPos / nodeHeight
        );
    }

    public Entity removeAtPos(IndexPosition position) {
        Entity prevNode = getNodeWithIndexPosition(position);
        if (prevNode == null) {
            return null;
        }
        pane.getChildren().remove(prevNode);
        posMap.setAtPos(position, null);
        return prevNode;
    }

    //add entity that moves
    public void addEnemyEntityWithDesginatedPath(
            EnemyEntity enemyEntity,
            ArrayList<IndexPosition> pathPositionArray
    ) {
        int cnt = 0;
        Stack<IndexPosition> pathPositionStack = new Stack<>();
        Stack<IndexPosition> pathIndexDeltaStack = new Stack<>();
        IndexPosition prevIndexPosition = null;
        for (int i = 0; i < pathPositionArray.size(); i++) {
            IndexPosition idxPos = pathPositionArray.get(i);
            pathPositionStack.push(idxPos);
            if (prevIndexPosition != null) {
                int deltaX = idxPos.getX() - prevIndexPosition.getX();
                int deltaY = idxPos.getY() - prevIndexPosition.getY();
                pathIndexDeltaStack.push(new IndexPosition(deltaX, deltaY));
            }
            prevIndexPosition = idxPos;
        }
        IndexPosition currPos = pathPositionArray.get(cnt);
        addNodeWithIndexPosition(currPos, enemyEntity);
        EntityTransitionController entityTransitionController = new EntityTransitionController(
                enemyEntity, pathIndexDeltaStack, pane
        );
        intervalHotObservable.subscribe(time -> {
            translateOnClock(entityTransitionController, enemyEntity);
        });
    }

    private void translateOnClock(
            EntityTransitionController entityTransitionController,
            EnemyEntity enemyEntity
    ) {
        EntityActionType action = entityTransitionController.onTick();
        if (action == EntityActionType.ARRIVE_DESTINATION) {
            gameFlowController.getGameUpdateDataSubject().onNext(
                    new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, (float) -10)
            );
        }
    }

    enum EntityActionType {
        NONE,
        MOVE,
        ARRIVE_DESTINATION
    }

    /**
     * class that controls the transition of the entity
     */
    public class EntityTransitionController {
        private int clock = -1;

        public int getClock() {
            return clock;
        }

        public void setClock(int clock) {
            this.clock = clock;
        }

        private Entity entity;
        private Stack<IndexPosition> pathIndexDeltaStack;

        public Entity getEntity() {
            return entity;
        }

        public void setEntity(Entity entity) {
            this.entity = entity;
        }

        public Stack<IndexPosition> getPathIndexDeltaStack() {
            return pathIndexDeltaStack;
        }

        public void setPathIndexDeltaStack(Stack<IndexPosition> pathIndexDeltaStack) {
            this.pathIndexDeltaStack = pathIndexDeltaStack;
        }

        public Pane getPane() {
            return pane;
        }

        public void setPane(Pane pane) {
            this.pane = pane;
        }

        private Pane pane;

        public EntityTransitionController(
                Entity entity,
                Stack<IndexPosition> pathIndexDeltaStack,
                Pane pane
        ) {
            this.entity = entity;
            this.pathIndexDeltaStack = pathIndexDeltaStack;
            this.pane = pane;
        }

        public EntityActionType onTick() {
            clock += 1;
            if (entity == null) {
                return EntityActionType.NONE;
            }
            // if the entity arrives at its destination
            if (pathIndexDeltaStack.empty()) {
                remove(entity);
                return EntityActionType.ARRIVE_DESTINATION;
            } else {
                IndexPosition deltaIdxPos = pathIndexDeltaStack.pop();
                move(entity, deltaIdxPos.getX(), deltaIdxPos.getY());
                return EntityActionType.MOVE;
            }
        }

        public void move(Entity entity, int deltaX, int deltaY) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    //Instantiating TranslateTransition class
                    TranslateTransition translate = new TranslateTransition();
                    //shifting the X coordinate of the centre of the circle by 400
                    translate.setByX(deltaX * Config.UNIT);
                    translate.setByY(deltaY * Config.UNIT);
                    //setting the duration for the Translate transition
                    translate.setDuration(Duration.millis(Config.TICK));
                    translate.setNode(entity);
                    translate.play();
                }
            });
        }

        public void remove(Entity entity) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    getPane().getChildren().remove(entity);
                }
            });
            this.entity = null;
        }
    }

}