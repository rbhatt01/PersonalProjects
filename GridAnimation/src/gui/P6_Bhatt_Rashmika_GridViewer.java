/* 
 * Rashmika Bhatt Period 6 3/15/18
 * The clear method and the slider part of the lab were not too difficult to implement. It was a little 
 * hard to understand everything we went over in class. The load for me is still not working because I 
 * haven't been able to figure out how to open the file chooser dialog from outside the start method. Also
 * the inverting colors is also not wokring yet. So far this lab has taken me about 3 hours. 
 * 
 * Rashmika Bhatt Period 6 3/18/18 
 * The rest of the lab is working now. The load file method was actually not too bad once I understood how to 
 * use a filechooser. The changing of the colors on the other hand was a little difficult for me. I couldn't figure 
 * out how to access the cell for the longest time but then I realized there were methods that took in x and y postitions
 * and gave you the according cell. After doing some research with mouse events I was able to implement this method and 
 * complete the changing thing. Also to deal with out of bounds errors I made an isValid method. The rest of this took about
 * 2 hours. 
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
import javafx.scene.input.MouseEvent;
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


public class P6_Bhatt_Rashmika_GridViewer extends Application {
	private BooleanGridPane view; 
	private GridModel<Boolean> model; 
	private Button clear;
	private Button load;
	private Slider slider;
	private FileChooser file; 
	private Stage mainStage; 
	
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		mainStage = stage; 
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
		 
	
		
		root.setCenter(view);
		BackgroundFill fill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(2),new Insets(0));
		
		
		HBox bottomPanel = new HBox(); 
		bottomPanel.setPadding(new Insets(15,15,15,15));
		
		clear = new Button("Clear"); 
		clear.setOnAction(new ButtonHandler());
		
		load = new Button("Load");
		load.setOnAction(new ButtonHandler());
		
		slider = new Slider(0,100, 50);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.valueProperty().addListener(new SliderHandler());
		
		Text text = new Text("Cell Size"); 
		bottomPanel.getChildren().addAll(clear, load, text, slider);
		bottomPanel.setSpacing(50);
		bottomPanel.setBackground(new Background (fill));
		root.setBottom(bottomPanel);
		
		view.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
		    int col = view.colForXPos(e.getX());
		    int row = view.rowForYPos(e.getY());
		    
		  
			   if(isValid(row -1, col -1 )) {
				   model.setValueAt(row - 1, col - 1, !model.getValueAt(row - 1, col - 1)); 
			   }
			   if(isValid(row -1, col)) {
				   model.setValueAt(row - 1, col, !model.getValueAt(row - 1, col));   
			   }
			   
			   if(isValid(row - 1, col + 1)) {
				   model.setValueAt(row - 1, col + 1, !model.getValueAt(row - 1, col + 1)); 
			   }
			   if(isValid(row, col -1)) {
				   model.setValueAt(row, col - 1, !model.getValueAt(row, col - 1)); 
			   }
			   if(isValid(row, col + 1)) {
				   model.setValueAt(row, col + 1, !model.getValueAt(row, col + 1)); 
			   }
			   if(isValid(row + 1, col - 1)) {
				   model.setValueAt(row + 1, col - 1, !model.getValueAt(row + 1, col - 1));
			   }
			   if(isValid(row + 1, col)) {
				   model.setValueAt(row + 1, col, !model.getValueAt(row + 1, col));
			   }
			   if(isValid(row + 1, col + 1)) {
				   model.setValueAt(row + 1, col + 1, !model.getValueAt(row + 1, col + 1)); 
			   }
		    
		    
		});
		stage.show(); 
		
	}
	
	public boolean isValid(int row, int col) {
		try {
			boolean ans = model.getValueAt(row, col);
			return true; 
		} catch (Exception exception) {
			return false; 
		}
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
			} 
			
			if(event.getSource() == load) {
				//choose file 
				file = new FileChooser(); 
				File selectedFile = file.showOpenDialog(mainStage);
				try {
					Scanner in = new Scanner(selectedFile); 
					Boolean [][] rawData = null;
					int j = 0; 
					while(in.hasNextLine()) {
						String r = in.nextLine(); 
						for(int i = 0; i < r.length(); i++) {
							if(r.charAt(i) == 'x') {
								rawData[j][i] = true; 
							} else {
								rawData[j][i] = false; 
							}
						}
					}
					model = new GridModel<Boolean> (rawData); 
					view.setModel(model);
				} catch (Exception e) {
					
				}
			}
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
