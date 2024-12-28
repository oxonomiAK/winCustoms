// package com.customizer.ui.ButtonEffectUtils;

// import javafx.scene.Scene;
// import javafx.scene.layout.Pane;
// import javafx.fxml.FXMLLoader;

// import java.util.HashMap;
// import java.util.Map;

// public class SceneManager {
//     private Map<String, Pane> scenes = new HashMap<>();

//     public Pane getScene(String fxmlPath) {
//         if (!scenes.containsKey(fxmlPath)) {
//             try {
//                 FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//                 Pane pane = loader.load();
//                 scenes.put(fxmlPath, pane); // Кэшируем сцену
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 return null;
//             }
//         }
//         return scenes.get(fxmlPath);
//     }
//     // Метод для предзагрузки всех необходимых сцен
//     public void preloadScenes(String... fxmlPaths) {
//         for (String fxmlPath : fxmlPaths) {
//             try {
//                 if (!scenes.containsKey(fxmlPath)) {
//                     FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//                     Pane pane = loader.load();
//                     scenes.put(fxmlPath, pane); // Кэшируем сцену
//                 }
//             } catch (Exception e) {
//                 System.err.println("Failed to preload FXML: " + fxmlPath);
//                 e.printStackTrace();
//             }
//         }
//     }
// }

