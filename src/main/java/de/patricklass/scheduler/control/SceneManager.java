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

    public static final String ADMIN_GROUP_OVERVIEW = "groupOverview";
    public static final String CREATE_GROUP = "createGroup";
    public static final String ADMIN_MAIN = "adminMain";
    public static final String USER_VIEW = "userView";
    public static final String INVITATION_VIEW = "invitationView";
    public static final String ADMIN_CREATE_EVENT = "adminCreateEvent";
    public static final String LOGIN = "login";

    /**
     * Adds a new entry consisting of the supplied identifier and Scene Object to the {@code sceneMap}
     * @param identifier the identifier for the new scene
     * @param scene the new scene
     * @throws IllegalArgumentException when one of the parameters is null
     */
    public void addScene(String identifier, Scene scene) throws IllegalArgumentException{
        if(identifier == null) throw new IllegalArgumentException("Identifier for Scene can't be null");
        if(scene == null) throw new IllegalArgumentException("Scene can't be null");
        sceneMap.put(identifier, scene);
    }

    /**
     * Selects the scene with the supplied key {@code identifier} form the sceneMap, if it exists and shows it on the
     * {@code primaryStage. Also pushes the current Scene onto the {@code lastScenes} Stack
     * @param identifier the identifier of the scene to be shown
     * @throws IllegalArgumentException if the supplied identifier is null or the map doesn't contain it
     */
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

    /**
     * Pops the last Scene off of the {@code lastScenes} Stack and shows it on the primaryStage
     * @throws IllegalStateException if lastScenes is empty
     */
    public void showLastScene() throws IllegalStateException {
        if (lastScenes.isEmpty()) throw new IllegalStateException("No last Scene");
        Scene lastScene = lastScenes.pop();
        primaryStage.setScene(lastScene);
    }

    public void clearLastScenes() {
        lastScenes.clear();
    }
}
