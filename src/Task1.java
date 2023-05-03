import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Task1 extends Application{

    private static final int width = 600;
    private static final int height = 600;
    private static final int radius = 70;
    public void start(Stage stage){
        Sphere sphere = new Sphere();
        sphere.setRadius(radius);
        sphere.setTranslateX(width / 2);
        sphere.setTranslateY(height / 2);

        PhongMaterial phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.valueOf("#ACCAF2"));
        sphere.setMaterial(phongMaterial);

        Group root = new Group(sphere);
        Scene scene = new Scene(root, width, height);
        scene.setFill(Color.valueOf("#FFEBCF"));
        root.getChildren().addAll(lights());

        stage.setScene(scene);
        stage.setTitle("Animation ball");
        stage.show();

        animateSphere(sphere);
    }

    private void animateSphere(Sphere sphere) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(sphere.translateZProperty(), -100),
                        new KeyValue(sphere.scaleXProperty(), 1.75),
                        new KeyValue(sphere.scaleYProperty(), 1.75),
                        new KeyValue(sphere.scaleZProperty(), 1.75)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(sphere.translateZProperty(), 300),
                        new KeyValue(sphere.scaleXProperty(), 0.5),
                        new KeyValue(sphere.scaleYProperty(), 0.5),
                        new KeyValue(sphere.scaleZProperty(), 0.5))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }

    private Node[] lights() {
        PointLight pointLight1 = new PointLight();
        PointLight pointLight2 = new PointLight();

        pointLight1.getTransforms().add(new Translate(1000, 400, 1000));

        return new Node[] {pointLight1, pointLight2};
    }
    public static void main(String[] args) {
        launch(args);
    }
}
