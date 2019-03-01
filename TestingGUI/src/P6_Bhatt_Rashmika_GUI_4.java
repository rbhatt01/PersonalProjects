/* 
 * Rashmika Bhatt Period 6 3/15/18
 * With the layout already created I realized I had a color picker. Since one of the requirements were to change 
 * out image somehow I realized you could use the color picker to change the color of that image which for me 
 * was the pear. This wasn't too difficult to figure out, I just had to figure out how to get the color from the 
 * color picker and then instead of setting fill of the pear to the old color, it would now set it to what the 
 * color picker chose. For the button, after learning how to use button handlers and private classes it wasn't 
 * too difficult to figure out how to get it to spin. Overall this was a fun lab. I would rate it 4/5. It took me 
 * about 20 min. 
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class P6_Bhatt_Rashmika_GUI_4 extends Application {
	// measurements
			private final double BODY_RADIUS = 20; // changing this scales everything
			
			private final double SMALLER_BODY_RADIUS = BODY_RADIUS / 1.5;
			private final double STEM_HEIGHT = BODY_RADIUS / 2;
			private final double STEM_WIDTH = BODY_RADIUS / 6;
			
			private final double LEAF_RADIUS_Y = BODY_RADIUS / 4;
			private final double LEAF_RADIUS_X = BODY_RADIUS / 9;
			
			// colors
			private final Color PEAR_COLOR = Color.YELLOWGREEN;
			private final Color STEM_COLOR = Color.BROWN;
			private final Color LEAF_COLOR = Color.GREEN;
			Button pickColor; 

	public static void main (String [] args) {
		launch(args); 
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Donut");

		
		BorderPane border = new BorderPane(); 
		border.setPrefHeight(500);
		border.setPrefWidth(800);
				
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    
		VBox vbox = new VBox();
	    vbox.setPadding(new Insets(30, 50, 20, 50));
	    vbox.setSpacing(8);
	    
	    
	    Text t  = new Text("incomPEARable donuts"); 
	    hbox.getChildren().add(t);
	    t.setFont(new Font(30));
	    
	    BackgroundFill fill = new BackgroundFill(Color.ALICEBLUE, new CornerRadii(2),new Insets(0));
	    BackgroundFill fill2 = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(2),new Insets(0));

	    hbox.setBackground(new Background(fill));
	    vbox.setBackground(new Background(fill2));
	    
	    pickColor = new Button("Choose toppings");
	    pickColor.setOnAction(new ButtonHandler());
	    
	    
	    ColorPicker glazeColor = new ColorPicker();
	    ChoiceBox flavor = new ChoiceBox(FXCollections.observableArrayList(
	    	    "Flavor", new Separator(), "Chocolate", "Vanilla"));
	    
	    
	    vbox.getChildren().add(flavor);
	    vbox.getChildren().add(pickColor);
	    vbox.getChildren().add(glazeColor);

	    border.setTop(hbox);
	    border.setLeft(vbox);
	    
	  
	    root.getChildren().add(border);
	    
	    Group root2 = new Group(); 
	   
	    Circle bod = new Circle(BODY_RADIUS);
		bod.setCenterX(hbox.getWidth());
		bod.setCenterY(hbox.getHeight() );
		
		
		Circle smallerBod = new Circle(SMALLER_BODY_RADIUS);
		smallerBod.setCenterX(scene.getWidth()/2);
		smallerBod.setCenterY(bod.getCenterY() - BODY_RADIUS + BODY_RADIUS/7);
		 glazeColor.setOnAction(new EventHandler() {
		      
				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Color c = glazeColor.getValue();
					smallerBod.setFill(c);
					bod.setFill(c); 
				}
		    });
		
		
		
		Rectangle stem = new Rectangle(smallerBod.getCenterX(), smallerBod.getCenterY() - SMALLER_BODY_RADIUS - STEM_HEIGHT,  STEM_WIDTH, STEM_HEIGHT);
		stem.setFill(STEM_COLOR);
		
		Ellipse leaf = new Ellipse(smallerBod.getCenterX() + LEAF_RADIUS_Y, smallerBod.getCenterY() - SMALLER_BODY_RADIUS - STEM_HEIGHT/2, LEAF_RADIUS_X, LEAF_RADIUS_Y);
		leaf.setFill(LEAF_COLOR);
		leaf.setRotate(BODY_RADIUS/2);

		root2.getChildren().addAll(bod, smallerBod, stem, leaf); 
		root2.setLayoutX(350);
		root2.setLayoutY(45);
		root.getChildren().add(root2);
	
		stage.show(); 

	}
	
	private class ButtonHandler implements EventHandler <ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			 if(event.getSource() == pickColor) { 
				 RotateTransition rt = new RotateTransition(Duration.millis(3000), pickColor);
					rt.setByAngle(360);
					rt.play();
			 }
		}
	}
}
	
	