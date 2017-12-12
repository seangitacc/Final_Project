package Final_Project;

import GUI.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Driver extends Application {

    public static void main(String[] args) {

        try{

            //Known bug workaround for Windows 10 computers with touchscreens
            System.setProperty("glass.accessible.force", "false");

            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Create a connection
            Utilities.createConnnection();
            Utilities.createStatement();

            //Launch the display
            launch ( args );

            // Close the connection
            Utilities.connection.close();

        }catch(Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }
    }

    public static final String SPLASH_IMAGE =
            "https://i.imgur.com/7OxXNQs.png";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Loading application...");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        progressText.setStyle(
                "-fx-text-fill: white !important;"
        );
        splashLayout.setStyle(
                        "-fx-padding: 5; " +
                        "-fx-background-color: #383838; " +
                        "-fx-border-width:5; " +
                        "-fx-border-color: " +
                        "linear-gradient(" +
                        "to bottom, " +
                        "#ab4642, " +
                        "derive(white, 50%)" +
                        ");" +
                        "-fx-font: 10pt Helvetica;"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> loadTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFiles =
                        FXCollections.observableArrayList();
                ObservableList<String> loadingFiles =
                        FXCollections.observableArrayList(
                                "Configuration","Settings","GUI"
                        );

                updateMessage("Loading application . . .");
                for (int i = 0; i < loadingFiles.size(); i++) {
                    Thread.sleep(600);
                    updateProgress(i + 1, loadingFiles.size());
                    String nextLoad = loadingFiles.get(i);
                    foundFiles.add(nextLoad);
                    updateMessage("Loading " + nextLoad + "...");
                }
                Thread.sleep(400);
                updateMessage("Application loaded.");

                return foundFiles;
            }
        };

        showSplash(
                initStage,
                loadTask,
                EntryWindow::display
        );
        new Thread(loadTask).start();
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            Driver.InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    public interface InitCompletionHandler {
        void complete();
    }

}
