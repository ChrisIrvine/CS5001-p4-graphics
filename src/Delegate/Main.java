package Delegate;

import Model.MandelbrotCalculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.CanvasBuilder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.awt.Point;


public class Main extends Application {

    private BorderPane mandelPane = new BorderPane();
    private Canvas canvas = CanvasBuilder
            .create()
            .height(Configuration.HEIGHT)
            .width(Configuration.WIDTH)
            .layoutX(Configuration.XOFFSET)
            .layoutY(Configuration.YOFFSET)
            .build();
    @Override
    public void start(Stage primaryStage) throws Exception{


        //Add items to pane
        mandelPane.setTop(createToolbar());
        mandelPane.setCenter(paintMandel());

        primaryStage.setTitle("Mandelbrot Set");
        Scene scene = new Scene(
                mandelPane, Configuration.WIDTH + (2 * Configuration.XOFFSET)
                , Configuration.HEIGHT + (2 * Configuration.YOFFSET) + 50
        ); //the additional 50 pixels added to the Height is to accommodate a
        scene.setFill(Color.WHITE);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private VBox createToolbar() {
        ToolBar toolBar = new ToolBar();

        Button changeColor = new Button("Change Color");
        Button undo = new Button("Undo");
        Button redo = new Button("Redo");
        Button reset = new Button("Reset");
        Button updateIterations = new Button("Update Iterations");

        TextField iterations = new TextField();
        iterations.setText(String.valueOf(
                MandelbrotCalculator.getInitialMaxIterations()
        ));

        final ToggleGroup zp = new ToggleGroup();
        RadioButton zoom = new RadioButton("Zoom");
        RadioButton pan = new RadioButton("Pan");
        zoom.setToggleGroup(zp);
        pan.setToggleGroup(zp);

        toolBar.getItems().add(zoom);
        toolBar.getItems().add(pan);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(undo);
        toolBar.getItems().add(reset);
        toolBar.getItems().add(changeColor);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(redo);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(iterations);
        toolBar.getItems().add(updateIterations);

        toolBar.prefWidthProperty().bind(mandelPane.widthProperty());

        updateIterations.setOnAction(event -> {
            Controller.updateIterations(Integer.valueOf(iterations.getText()));
            Controller.paintMandelbrot(canvas.getGraphicsContext2D(), Controller.generateDefaultMandel());
            System.out.println("Iterations now set to: " + Controller.getIterations());
        });

        return new VBox(toolBar);
    }

    private Canvas paintMandel() {
        final Rectangle zoom = new Rectangle(0, 0, 0, 0);
        final Point anchor = new Point();
        //final Line pan = new Line();

        Controller.paintMandelbrot(
                canvas.getGraphicsContext2D(),
                Controller.generateDefaultMandel()
        );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(event -> {
            anchor.setLocation(event.getX(), event.getY());
            zoom.setX(event.getX());
            zoom.setY(event.getY());
            zoom.setFill(null);
            zoom.setStroke(Color.ORANGE);
            zoom.getStrokeDashArray().add(10.0);
            mandelPane.getChildren().add(zoom);
        });

        canvas.setOnMouseDragged(event -> {
            zoom.setWidth(Math.abs(event.getX() - anchor.getX()));
            zoom.setHeight(Math.abs(event.getY() - anchor.getY()));
            zoom.setX(Math.min(anchor.getX(), event.getX()));
            zoom.setY(Math.min(anchor.getY(), event.getY()));
        });

        canvas.setOnMouseReleased(event -> {
            zoom.setX(event.getX());
            zoom.setY(event.getY());
            System.out.println("Start Coords: " + anchor.getX() + ", " + anchor.getY());
            System.out.println("End Coords: " + zoom.getX() + ", " + zoom.getY());
            Controller.paintMandelbrot(gc, Controller.generateZoomedMandel(anchor.getX(), zoom.getX(), anchor.getY(), zoom.getY()));
            mandelPane.setCenter(null);
            mandelPane.setCenter(canvas);
            canvas.setOnMouseClicked(null);
        });

        return canvas;
    }
}
