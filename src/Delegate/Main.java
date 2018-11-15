package Delegate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.CanvasBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane mandelPane = new Pane();
        Canvas canvas = CanvasBuilder
                .create()
                .height(Configuration.HEIGHT)
                .width(Configuration.WIDTH)
                .layoutX(Configuration.XOFFSET)
                .layoutY(Configuration.YOFFSET)
                .build();

        Controller.paintMandelbrot(
                canvas.getGraphicsContext2D(),
                Controller.generateMandel()
        );

        mandelPane.getChildren().add(canvas);

        primaryStage.setTitle("Mandelbrot Set");
        Scene scene = new Scene(
                mandelPane, Configuration.WIDTH + (2 * Configuration.XOFFSET)
                , Configuration.HEIGHT + (2 * Configuration.YOFFSET) + 50
        ); //the additional 50 pixels added to the Height is to accommodate a
        //navigation bar
        scene.setFill(Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
