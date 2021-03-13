package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends Application {

    private Map<String, Integer> warningTypes;
    private File data;

    private Canvas canvas;
    private GraphicsContext gc;

    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON};

    private int total;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root,800, 500);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(scene);
        primaryStage.show();

        // csv file that contains data
        File data = new File("src/sample/weatherwarnings-2015.csv");

        // call funciton that reads file and gathers data
        readFile(data);

        draw(root);
    }

    public void readFile(File file) throws FileNotFoundException {
        warningTypes = new TreeMap<>();

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String[] columns = scanner.nextLine().split(",");
            String typeOfWarning = columns[5];  // get the type of flood (located in the 6th column)

            // put counts of each type of warning into a map
            // if map already has the type, update the count
            if (warningTypes.containsKey(typeOfWarning)){
                int previousCount = warningTypes.get(typeOfWarning);
                warningTypes.put(typeOfWarning, previousCount + 1);
            }
            // if map doesn't contain the type, add it to the map and make its count 1
            else{
                warningTypes.put(typeOfWarning, 1);
            }
        }

        // Calculate total number of warning types
        Iterator<String> warningTypesIterator = warningTypes.keySet().iterator();
        while (warningTypesIterator.hasNext()){
            total += warningTypes.get(warningTypesIterator.next());
        }

        System.out.println(warningTypes);
        System.out.println("Total: " + total);
    }

    public void draw(Group root){
        gc = canvas.getGraphicsContext2D();

        Iterator<String> warningTypesIterator2 = warningTypes.keySet().iterator();
        int startAngle = 0;         // for drawing arcs
        int colour = 0;             // index of colours list
        gc.setStroke(Color.BLACK);  // colour for the text in the legend
        int inc = 0;                // how much to increase the y coordinate for the text each time

        while (warningTypesIterator2.hasNext()){
            String type = warningTypesIterator2.next();
            double percentage = (double)warningTypes.get(type) / (double)total;
            double endAngle = (double)percentage * (double)360;

            // drawing arcs
            gc.setFill(pieColours[colour]);     // different colour for each arc
            // draw outline of arcs in black
            gc.strokeArc(400, 85, 350, 350, startAngle, endAngle, ArcType.ROUND);
            // draw filles arcs
            gc.fillArc(400, 85, 350, 350, startAngle, endAngle, ArcType.ROUND);

            // drawing legend boxes
            gc.strokeRect(80, 130 + inc, 60,35);    // outline of rectangles
            gc.fillRect(80, 130 + inc, 60,35);      // filled in rectangles

            // drawing text in legend
            gc.setFill(Color.BLACK);
            gc.fillText(type, 150, 150 + inc);

            colour++;                   // go to next colour
            startAngle += endAngle;     // new arc begins where old arc finished
            inc += 50;                  // increment y coordinate of text by 50 each time
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
