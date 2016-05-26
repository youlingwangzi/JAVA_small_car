package shulin_homework;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Polygon;
//import javafx.scene.text.Text;

public class Car extends Application {
  @Override 
  public void start(Stage primaryStage) {       
    CarPane car = new CarPane();
    
    Scene scene = new Scene(car, 200, 200);
    primaryStage.setTitle("Exercise15_29"); 
    primaryStage.setScene(scene); 
    primaryStage.show();
    
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(100), e -> car.move()));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); 
    
    scene.widthProperty().addListener(e -> car.setW(car.getWidth()));
    scene.heightProperty().addListener(e -> car.setH(car.getHeight()));
    
    car.requestFocus();
    car.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.UP) {
        animation.setRate(animation.getRate() + 1);
      }
      else if (e.getCode() == KeyCode.DOWN) {
        animation.setRate(animation.getRate() - 1);
      }
      else if (e.getCode() == KeyCode.LEFT) {
    	  animation.pause();
      }
      else if (e.getCode() == KeyCode.RIGHT) {
    	  animation.play();
      }
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
} 

class CarPane extends Pane {
  private double w = 200;
  private double h = 200;
  private double baseX = 0;
  private double baseY = h;
  private Circle c1 = new Circle(baseX + 15, baseY - 5, 5);
  private Circle c2 = new Circle(baseX + 35, baseY - 5, 5);
    
  private Rectangle carBody = new Rectangle(baseX, baseY - 20, 50, 10);
  //private Text text = new Text(baseX-20,baseY - 50,"王树林和刘昊来的\n超级无敌小轿车");
  private Polygon polygon = new Polygon();
    ObservableList<Double> list = polygon.getPoints();
  
  public CarPane() {
    carBody.setFill(Color.BLUE);
    list.add(baseX+30);
    list.add(baseY-30);
    list.add(baseX+20);
    list.add(baseY-30);
    list.add(baseX+10);
    list.add(baseY-20);
    list.add(baseX+40);
    list.add(baseY-20);
    polygon.setFill(Color.RED);
    this.getChildren().addAll(c1, c2, carBody, polygon);
    //this.getChildren().add(text);
  }
  
  public void move() {
    if (baseX > w)
      baseX = -20;
    else
      baseX += 1;
    
    setValues();
  }
  
  public void setValues() {

    list.set(0,baseX+30);
    list.set(1,baseY-30);
    list.set(2,baseX+20);
    list.set(3,baseY-30);
    list.set(4,baseX+10);
    list.set(5,baseY-20);
    list.set(6,baseX+40);
    list.set(7,baseY-20);
    c1.setCenterX(baseX + 15);
    c1.setCenterY(baseY - 5);
    c2.setCenterX(baseX + 35);
    c2.setCenterY(baseY - 5);
/*    text.setX(baseX-20);
    text.setY(baseY - 50);*/

    carBody.setX(baseX);
    carBody.setY(baseY - 20);
  }
  
  public void setW(double w) {
    this.w = w;
    setValues();
  }
  
  public void setH(double h) {
    this.h = h;
    baseY = h;
    setValues();
  }
}
