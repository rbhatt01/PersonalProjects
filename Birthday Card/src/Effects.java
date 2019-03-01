/* 
 * Rashmika Bhatt Period 6 3/27/18
 * I fixed the lab. I think my biggest problem when first doing this lab was not understanding the 
 * way java fx works. With Hboxes borderPanes and nodes that are added and positioned accordingly. 
 * Coming back to this lab after getting more of a feel for java fx and working on projects like 
 * minesweeper I was surprised at how much quicker I could create a fixed and working lab than my 
 * broken one i turned in last time. Over all this lab took me about an hour. 
 */

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Effects extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		
		stage.setTitle("Birthday Card");
		stage.setResizable(false);
		stage.sizeToScene();
				
		GridPane grid = new GridPane(); 
		HBox top = new HBox(); 
		HBox mid = new HBox(); 
		
		Text hbd = new Text( "HAPPY BIRTHDAY!"); 
		hbd.setFont(Font.font ("Verdana", 30));
		hbd.setFill(Color.DODGERBLUE);
		Glow glow = new Glow(0.8); 
		Reflection reflection = new Reflection(); 
		glow.setInput(reflection);
		hbd.setEffect(glow);
		FadeTransition ft = new FadeTransition(Duration.millis(2000), hbd);
	     ft.setFromValue(0);
	     ft.setToValue(1);
	     ft.play();


		
		top.setPadding(new Insets(50,0,20,100));
		top.getChildren().add(hbd);
		
		Image img = new Image("file:BirthdayStuff/BirthdayPresent.png");
		ImageView imgView = new ImageView();
		imgView.setImage(img)    ;// Add this to your root node
		RotateTransition rt = new RotateTransition(Duration.millis(3000), imgView);
		rt.setByAngle(360);
		rt.play();
		
		Image img2 = new Image("file:BirthdayStuff/Balloon.jpg"); 
		ImageView imgView2 = new ImageView(); 
		imgView2.setFitWidth(150);
		imgView2.setFitHeight(200);
		imgView2.setImage(img2);
		
		Image img3 = new Image("file:BirthdayStuff/Balloon.jpg"); 
		ImageView imgView3 = new ImageView(); 
		imgView3.setFitWidth(150);
		imgView3.setFitHeight(200);
		imgView3.setImage(img3);
		
		
		Line line = new Line(); 
	    line.setStartX(50.0f);
	    line.setEndX(50.0f);
	    line.setStartY(500.0f);
	    line.setEndY(50.0f);
		PathTransition path = new PathTransition(Duration.millis(5000), line );
		path.setNode(imgView2);
		
		path.play();
		
		Line line2 = new Line(); 
		line2.setStartX(75.0f);
		line2.setEndX(75.0f);
		line2.setStartY(500.0f);
		line2.setEndY(50.0f);
		PathTransition path2 = new PathTransition(Duration.millis(5000), line2 );
		path2.setNode(imgView3);
		path2.play();
		
		DropShadow dropShadow = new DropShadow(5,5,5, Color.GRAY); 
		mid.setPadding(new Insets(50,20,0,0));
		mid.getChildren().addAll(imgView2, imgView, imgView3);
		
		HBox bottom = new HBox(); 
		bottom.setPadding(new Insets(50,20,0,200));
		Text name = new Text("Name");
		name.setFont(Font.font ("Bodoni", 40));
		name.setFill(Color.DARKMAGENTA);
		name.setEffect(dropShadow);
		bottom.getChildren().add(name); 
		
		
		root.setTop(top);
		root.setCenter(mid);
		root.setBottom(bottom);
		
	
		
		stage.show(); 
				
	}

}

