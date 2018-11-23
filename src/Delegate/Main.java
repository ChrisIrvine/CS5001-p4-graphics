package Delegate;

import Model.MandelbrotCalculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.CanvasBuilder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.Point;

/**
 * Main class for this application. Will allow the users to generate and
 * manipulate the Mandelbrot Set Image.
 */
public class Main extends Application {

    private BorderPane mandelPane = new BorderPane();
    private Canvas canvas = CanvasBuilder
            .create()
            .height(Configuration.HEIGHT)
            .width(Configuration.WIDTH)
            .layoutX(Configuration.XOFFSET)
            .layoutY(Configuration.YOFFSET)
            .build();
    private final ToggleGroup zp = new ToggleGroup();
    private RadioButton zoom = new RadioButton("Zoom");
    private RadioButton pan = new RadioButton("Pan");
    private boolean panZoom = true;
    private int colour;

    /**
     * Main method for the application. Will generate the GUI by triggering the
     * JavaFX's launch() method, which will then trigger the start() method.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overriden start method (from JavaFX) that will construct the ToolBar and
     * Canvas containing the Mandelbrot Set Image. Then will add those
     * components to the BorderPane. Will then construct the stage and the
     * scene in order to display the BorderPane.
     * @param primaryStage - stage to display the BorderPane on.
     * @throws Exception - exception handling.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Add items to pane
        mandelPane.setTop(createToolbar());
        mandelPane.setCenter(paintMandel(1, panZoom));

        primaryStage.setTitle("Mandelbrot Set");
        Scene scene = new Scene(
                mandelPane, Configuration.WIDTH + (2 * Configuration.XOFFSET)
                , Configuration.HEIGHT + (2 * Configuration.YOFFSET) + 50
        ); //the additional 50 pixels added to the Height is to accommodate a
        scene.setFill(Color.WHITE);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Function to declare the components of a ToolBar, in addition to the
     * functionality of those components. The ToolBar object will then be
     * wrapped inside a VBox object, which is then returned.
     * @return VBox containing the ToolBar
     */
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

        zoom.setToggleGroup(zp);
        pan.setToggleGroup(zp);
        zp.selectToggle(zoom);

        toolBar.getItems().add(zoom);
        toolBar.getItems().add(pan);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(undo);
        toolBar.getItems().add(redo);
        toolBar.getItems().add(changeColor);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(reset);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(iterations);
        toolBar.getItems().add(updateIterations);
        toolBar.getItems().add(new Separator());

        toolBar.prefWidthProperty().bind(mandelPane.widthProperty());

        updateIterations.setOnAction(event -> {
            Controller.updateIterations(Integer.valueOf(iterations.getText()));
            Controller.createSetting();
            System.out.println("Iterations now set to: " + Controller.getIterations());
            mandelPane.getChildren().remove(canvas);
            mandelPane.setCenter(paintMandel(2, panZoom));
        });

        reset.setOnAction(event -> {
            Controller.restoreDefaults();
            iterations.setText(String.valueOf(Controller.getIterations()));
            mandelPane.getChildren().remove(canvas);
            mandelPane.setCenter(paintMandel(1, panZoom));
        });

        undo.setOnAction(event -> {
            Controller.undo();
            mandelPane.getChildren().remove(canvas);
            mandelPane.setCenter(paintMandel(2, panZoom));
        });

        redo.setOnAction(event -> {
            Controller.redo();
            mandelPane.getChildren().remove(canvas);
            mandelPane.setCenter(paintMandel(2, panZoom));
        });

        zp.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(zp.getSelectedToggle() == zoom) {
                System.out.println("Zoom Enabled");
                panZoom = true;
                zp.selectToggle(zoom);
                paintMandel(2, panZoom);
            } else if (zp.getSelectedToggle() == pan) {
                System.out.println("Pan Enabled");
                panZoom = false;
                zp.selectToggle(pan);
                paintMandel(2, panZoom);
            }
        });

        changeColor.setOnAction(event -> {
            if(colour == 6) {
                colour = 0;
            } else {
                colour++;
            }
            paintMandel(2, panZoom);
        });

        return new VBox(toolBar);
    }

    /**
     * Method to generate the Mandelbrot Set Image inside a canvas. Will
     * generate the "default" image or a mutated image depending on the integer
     * value of the switchCon parameter. Additionally will dictate whether the
     * user can zoom or pan.
     * @param switchCon - controls which case is triggered in the switch
     *                  statement
     * @param panZoom - if true, user can zoom. if false, user can pan.
     * @return - Canvas containing the Mandelbrot Set Image
     */
    private Canvas paintMandel(int switchCon, boolean panZoom) {
        final Rectangle zoom = new Rectangle(0, 0, 0, 0);
        final Point anchor = new Point();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        final Line pan = new Line();

        switch (switchCon) {
            case 1:
                Controller.paintMandelbrot(gc, Controller.generateDefaultMandel(), colour);
                break;
            case 2:
                Controller.paintMandelbrot(gc, Controller.generateMandel(), colour);
                break;
            default:
                Controller.paintMandelbrot(gc, Controller.generateDefaultMandel(), colour);
                break;
        }

        gc.setFont(new Font(20));
        gc.setFill(Color.ORANGE);
        gc.fillText("Zoom Factor: " + Controller.getZoomFactor(), 10, 20);

        if(panZoom) {
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
                zoom.setWidth(Math.abs(anchor.getX() - event.getX()));
                zoom.setHeight(Math.abs(anchor.getX() - event.getX()));
                zoom.setX(Math.min(anchor.getX(), event.getX()));
                zoom.setY(Math.min(anchor.getY(), event.getY()));
            });

            canvas.setOnMouseReleased(event -> {
                zoom.setX(event.getX());
                zoom.setY(event.getY());
                System.out.println("Start Coords: " + anchor.getX() + ", " + anchor.getY());
                System.out.println("End Coords: " + zoom.getX() + ", " + zoom.getY());
                Controller.updateParams(anchor.getX(), zoom.getX(), anchor.getY(), zoom.getY());
                Controller.createSetting();
                Controller.calculateZoom();
                mandelPane.getChildren().remove(canvas);
                mandelPane.setCenter(paintMandel(2, panZoom));
            });
        } else {
            canvas.setOnMousePressed(event -> {
                anchor.setLocation(event.getX(), event.getY());
                pan.setStartX(anchor.getX());
                pan.setStartY(anchor.getY());
                pan.setStroke(Color.ORANGE);
                pan.getStrokeDashArray().add(10.0);
                mandelPane.getChildren().add(pan);
            });

            canvas.setOnMouseDragged(event -> {
                pan.setEndX(event.getX());
                pan.setEndX(event.getY());
            });

            canvas.setOnMouseReleased(event -> {
                pan.setEndX(event.getX());
                pan.setEndY(event.getY());
                System.out.println("Start Coords: " + pan.getStartX() + ", " + pan.getStartY());
                System.out.println("End Coords: " + pan.getEndX() + ", " + pan.getEndX());
                Controller.calculateDistance(pan.getStartX(), pan.getEndX(), pan.getStartY(), pan.getEndY());
                Controller.createSetting();
                mandelPane.getChildren().remove(canvas);
                mandelPane.setCenter(paintMandel(2, panZoom));
            });
        }
        return canvas;
    }
}
