package de.patricklass.scheduler.control;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Patrick Laß
 */
public class SceneManager {

    private final Stage primaryStage;

    private Stack<Scene> lastScenes = new Stack<>();

    private Map<String, Scene> sceneMap = new HashMap<>();

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void addScene(String identifier, Scene scene) {
        if(identifier == null) throw new IllegalArgumentException("Identifier for Scene can't be null");
        if(scene == null) throw new IllegalArgumentException("Scene can't be null");
        sceneMap.put(identifier, scene);
    }

    public void showScene(String identifier) throws IllegalArgumentException {
        if(identifier == null) throw new IllegalArgumentException("Identifier for Scene can't be null");

        Scene selectedScene = sceneMap.get(identifier);

        if (selectedScene == null){
            throw new IllegalArgumentException("Scene with identifier \"" + identifier + "\" does not exist.");
        }

        Scene lastScene = this.primaryStage.getScene();

        if(lastScene != null) lastScenes.push(lastScene);

        primaryStage.setScene(selectedScene);
    }

    public void showLastScene() throws IllegalStateException {
        if (lastScenes.isEmpty()) throw new IllegalStateException("No last Scene");
        Scene selectedScene = lastScenes.pop();
        primaryStage.setScene(selectedScene);
    }
}
