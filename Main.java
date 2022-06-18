import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    boolean pause = false;
    Timeline tl;
    int j = 0, type;
    ArrayList<Shapes> shapeBank = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Walls walls = new Walls();
        Shapes.fillPlan();
        Text scoreText = new Text("Score: ");
        scoreText.setStroke(Color.WHITE);
        scoreText.setStyle("-fx-font: 20 arials;");
        scoreText.setY(30);
        scoreText.setX(50);
        root.getChildren().add(scoreText);
        Random random = new Random();
        type = random.nextInt(7) + 1;
        shapeBank.add(new Shapes(6, -3, type));
        root.getChildren().add(shapeBank.get(j));

        tl = new Timeline(new KeyFrame(new Duration(400),
                event -> {
                    if (shapeBank.get(j).isGameOver(shapeBank.get(j).shape)) {
                        Text over = new Text("Game Over");
                        over.setFill(Color.RED);
                        over.setStyle("-fx-font: 70 arials;");
                        over.setY(350);
                        over.setX(100);
                        root.getChildren().add(over);
                    }
                    if (!(shapeBank.get(j).isGameOver(shapeBank.get(j).shape))) {
                        if (shapeBank.get(j).canMoveDown(shapeBank.get(j).shape))
                            shapeBank.get(j).moveDown();
                        else {
                            j++;
                            type = random.nextInt(7) + 1;
                            shapeBank.add(new Shapes(6, -3, type));
                            root.getChildren().add(shapeBank.get(j));
                            shapeBank.get(j).canMoveDown(shapeBank.get(j).shape);
                            shapeBank.get(j).moveDown();
                            Shapes.evenColumn = true;
                        }
                    }
                    //////////////////
                   /* for (int i = 0; i < 18; i++) {
                        if (Shapes.isLineFull(i)) {
                            for (int j = 1; j < 14; j++)
                                Shapes.gamePlan[i][j] = true;
                            for (int k = 0; k < shapeBank.size(); k++) {
                                for (int h = 0; h < 4; h++) {
                                    if (shapeBank.get(k).shape[h].getY() == i) {
                                        root.getChildren().remove(shapeBank.get(k));
                                    }
                                }
                            }
                        }
                    }*/
                    ///////////////
                }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                if (pause) {
                    tl.play();
                    pause = false;
                } else {
                    tl.pause();
                    pause = true;
                }
            }
            if (e.getCode() == KeyCode.W) {
                shapeBank.get(j).returnClockWise();
            }
            if (e.getCode() == KeyCode.S) {
                shapeBank.get(j).moveDown();
            }
            if (e.getCode() == KeyCode.D) {
                shapeBank.get(j).moveRight();
            }
            if (e.getCode() == KeyCode.A) {
                shapeBank.get(j).moveLeft();
            }
        });

        root.getChildren().add(walls);
        scene.setFill(Color.rgb(15, 55, 59));
        primaryStage.setTitle("TETRIS");
        primaryStage.setScene(scene);
        primaryStage.setHeight(880);
        primaryStage.setWidth(587);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
//    public void clearFloor(){
//        int i=0;
//        for (; i < shapeBank.size(); i++) {
//            shapeBank.get(i).move2();
////            shapeBank.get(i).makeFloorTrue(shapeBank.get(i).shape);
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
