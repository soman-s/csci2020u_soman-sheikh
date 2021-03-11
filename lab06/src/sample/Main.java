package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    
    // data to be used for drawing graphs
    private static double[] avgHousingPricesByYear = {247381.0,264171.4,287715.3,294736.1,308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};

    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 800,550);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 06");
        primaryStage.setScene(scene);
        primaryStage.show();

        double max = primaryStage.getMaxHeight();
        System.out.println(max);

        drawBars(root, 500);
        drawPie(root);
    }

    public void drawBars(Group root, int h){
        gc = canvas.getGraphicsContext2D();

        int start1 = 50;
        int start2 = 65;
        int inc = 0;

        double maxVal = Double.NEGATIVE_INFINITY, minVal = Double.MAX_VALUE;
        for (double val : avgHousingPricesByYear) {
            if (val > maxVal)
                maxVal = val;
            if (val < minVal)
                minVal = val;
        }

        gc.setFill(Color.RED);
        for (double val : avgHousingPricesByYear){
            double barHeight = ((val / maxVal) * 100);
            gc.fillRect(start1 + inc, (h - barHeight), 15, barHeight);
            inc += 40;
        }

        gc.setFill(Color.BLUE);
        inc = 0;
        for (double val : avgCommercialPricesByYear){
            double barHeight = ((val / maxVal) * 100);
            gc.fillRect(start2 + inc, (h - barHeight), 15, barHeight);
            inc += 40;
        }

//        inc = 0;
//        gc.setFill(Color.BLUE);
//        for (double val : avgCommercialPricesByYear){
////            double barHeight = ((val - minVal) / (maxVal - minVal)) * h;
//            gc.fillRect(start2 + inc, h - (val / 5000), 10, val / 5000);
//            inc += 25;
//        }
    }

    public void drawPie(Group root){
        int total = 0;
        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            total += purchasesByAgeGroup[i];
        }

        double startAngle = 0;

        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            // calculate how much percentage each slice is
            double percentage = (double)purchasesByAgeGroup[i] / (double)total;

            // calculate how many angles the arc will cover in the pie (out of 360 degrees)
            double endAngle = percentage * 360;

            gc.setFill(pieColours[i]);

            // x: x coordinate, y: y coordinate, w: width, h: height, startAngle: starting angle of arc in degrees, arcExtent: ending angle of the arc, type of Arc
            gc.fillArc(450, 110, 300, 300, startAngle, endAngle, ArcType.ROUND);
            startAngle += endAngle;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
