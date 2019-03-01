/* 
 * Rashmika Bhatt Period 6 3/15/18
 * The clear method and the slider part of the lab were not too difficult to implement. It was a little 
 * hard to understand everything we went over in class. The load for me is still not working because I 
 * haven't been able to figure out how to open the file chooser dialog from outside the start method. Also
 * the inverting colors is also not wokring yet. So far this lab has taken me about 3 hours. 
 */


package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner; 
import javafx.stage.FileChooser; 

public class effects extends Application {
	private BooleanGridPane view; 
	private GridModel<Boolean> model; 
	private Button clear;
	private Button load;
	private Slider slider;
	private FileChooser file; 
	
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		view = new BooleanGridPane(); 
		Boolean [][] rawData = { { true, true, true, true,},
				{false, true, true, false}, 
				{false, true, true, false}}; 
		
		
		
		model = new GridModel<Boolean>(rawData); 
		view.setModel(model);
		view.setTileSize(50);
		BorderPane root = new BorderPane(); 
		root.setPrefHeight(500);
		root.setPrefWidth(700);
		Scene scene = new Scene (root);
		stage.setScene(scene); 
		 
		file = new FileChooser(); 
		file.showOpenDialog(stage); 
		
		root.setCenter(view);
		BackgroundFill fill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(2),new Insets(0));
		
		
		HBox bottomPanel = new HBox(); 
		bottomPanel.setPadding(new Insets(15,15,15,15));
		
		clear = new Button("Clear"); 
		clear.setOnAction(new ButtonHandler());
		
		load = new Button("Load");
		
		slider = new Slider(0,100, 50);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.valueProperty().addListener(new SliderHandler());
		
		Text text = new Text("Cell Size"); 
		bottomPanel.getChildren().addAll(load, clear, text, slider);
		bottomPanel.setSpacing(50);
		bottomPanel.setBackground(new Background (fill));
		root.setBottom(bottomPanel);
		
		stage.show(); 
		
	}
	
	private class ButtonHandler implements EventHandler <ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource() == clear) {
				Boolean [][] rawData = { { false, false, false, false,},
						{false, false, false, false}, 
						{false, false, false, false}}; 
				model = new GridModel<Boolean>(rawData); 
				view.setModel(model);
			} else if(event.getSource() == load) {
				
			}
		}
		
	
	private class SliderHandler implements ChangeListener <Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
			// TODO Auto-generated method stub
			view.setTileSize((Double)newNumber); 
		}
		
	}

}
