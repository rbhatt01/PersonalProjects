/* 
 * Rashmika Bhatt Period 6 3/11/18
 * Over all this lab was pretty fun to make the birthday card and see what effects/ creativity I 
 * could use for it. A couple things not working right now are the sound and png, basically anything
 * I imported into eclipse. The picture is especially important because it is supposed to be the button
 * that starts the card. The button is also not working but I believe that is just syntax errors so 
 * I can get help with thtat easily at school. What I have took me about 2-3 hours becausse of all the
 * reading of apis and I spent a lot of time on the button.
 */


import java.awt.event.MouseEvent;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Effects extends Application{
	// measurements
			private final double BODY_RADIUS = 60; // changing this scales everything
			
			private final double SMALLER_BODY_RADIUS = BODY_RADIUS / 1.5;
			private final double STEM_HEIGHT = BODY_RADIUS / 2;
			private final double STEM_WIDTH = BODY_RADIUS / 6;
			
			private final double LEAF_RADIUS_Y = BODY_RADIUS / 4;
			private final double LEAF_RADIUS_X = BODY_RADIUS / 9;
			
			// colors
			private final Color PEAR_COLOR = Color.YELLOW;
			private final Color STEM_COLOR = Color.BROWN;
			private final Color LEAF_COLOR = Color.GREEN;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		
		stage.setTitle("Birthday Card");
		stage.setResizable(false);
		stage.sizeToScene();
		
		Circle button = new Circle(100);
		button.setFill(Color.RED);
		
		DropShadow dropShadow = new DropShadow(5,5,5, Color.GRAY); 
		Text text = new Text(10, 70, "Click Me!"); 
		text.setFont(new Font("Arial, 40", 0));
		text.setEffect(dropShadow);
		
		Text hbd = new Text(scene.getWidth()/2, scene.getHeight()/2, "HAPPY BIRTHDAY!"); 
		hbd.setFont(new Font("Impact, 70", 0));
		FadeTransition ft = new FadeTransition();
		ft.setFromValue(0.2);
		ft.setToValue(1.0);
		ft.setNode(hbd);
		ft.play();
		
		RotateTransition rt= new RotateTransition(); 
		rt.setDuration(Duration.seconds(2));
		rt.setByAngle(360);
		rt.setNode(hbd);
		rt.play(); 
		
			
		
		
		ButtonBase buttonBase = new Button (null, button); 
		
		root.getChildren().add(buttonBase);
		buttonBase.isArmed();
		buttonBase.setOn(new EventHandler <ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				root.getChildren().remove(button);
				root.getChildren().remove(text);
			}
		});
		
		
		
		stage.show();
		//button.setOnAction
		//button.onTouchMovedProperty(); 
		
		/*Image img = new Image("BirthdayPresent.png");
		ImageView imgView = new ImageView();
		imgView.setImage(img)    ;// Add this to your root node
		HBox box = new HBox(); 
		box.getChildren().add(imgView);
		root.getChildren().add(box);
		
		
		stage.show(); 
		/*String fileName = getClass().getResource("HappyBirthday.mp3").toURI().toString();
		AudioClip clip = new AudioClip(fileName);
		clip.play();
		
		
		
		stage.show(); 
		
		*/
	}
	
	

}

