package net.demomaker.applegame.engine.scene;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class SceneManager {
    private static List<Scene> scenes = new ArrayList<>();
    private static Dictionary<String,Scene> sceneNames = new Hashtable<>();
    private static Dictionary<String, Object> sharedObjects = new Hashtable<>();
    private static Dictionary<Scene, Boolean> saveStates = new Hashtable<>();
    private static Dictionary<Scene, Boolean> finishedLoading = new Hashtable<>();
    private static Scene activeScene = null;
    private static int activeSceneIndex = 0;

    public static void init() {

    }

    public static Scene getActiveScene() {
        return activeScene;
    }

    public static Scene getSceneByName(String name) {
        return sceneNames.get(name);
    }

    public static void SaveActiveSceneState() {
        if(saveStates.get(activeScene) != null)
            saveStates.remove(activeScene);
        saveStates.put(activeScene, true);
    }

    public static void addScene(Scene scene) {
        scenes.add(scene);
        sceneNames.put(scene.getClass().getSimpleName(), scene);
    }

    public static void removeScene(Scene scene) {
        scenes.remove(scene);
        sceneNames.remove(scene.getClass().getSimpleName());
    }

    public static void setActiveScene(Scene scene) {
        boolean saveState = false;
        if(activeScene != null) {
            saveState = saveStates.get(activeScene) != null && saveStates.get(activeScene) == true;
            if(!saveState) activeScene.cleanup();
        }
        activeScene = scene;
        saveState = saveStates.get(activeScene) != null && saveStates.get(activeScene) == true;
        finishedLoading.put(activeScene, false);
        if(!saveState) activeScene.init();
        if(saveState) activeScene.onResume();
        finishedLoading.put(activeScene, true);
        saveStates.remove(activeScene);
    }

    public static void goToNextScene() {
        activeSceneIndex = (activeSceneIndex + 1) % scenes.size();
        setActiveScene(scenes.get(activeSceneIndex));
    }

    public static void goToPreviousScene() {
        activeSceneIndex--;
        if(activeSceneIndex < 0)
            activeSceneIndex = scenes.size() - 1;
        setActiveScene(scenes.get(activeSceneIndex));
    }

    public static int getSceneIndex(Scene scene) {
        return scenes.indexOf(scene);
    }

    public static void setSharedObject(String key, Object object) {
        if(sharedObjects.get(key) != null)
            sharedObjects.remove(key);
        sharedObjects.put(key, object);
    }

    public static Object getSharedObject(String key) {
        return sharedObjects.get(key);
    }

    public static void cleanup() {
        scenes.clear();
    }

    public static boolean activeSceneFinishedLoading() {
        return finishedLoading.get(activeScene);
    }
}
