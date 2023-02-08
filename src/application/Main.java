package application;
	
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
//import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;


public class Main extends Application implements EventHandler<ActionEvent>{
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	//private ObjectProperty<Integer> elementProperty = new SimpleObjectProperty<>(0);
	
	Button subBtn;
	TextField idStart;
	TextField idEnd;
	TextField rowSeat;
	TextField colSeat;
	ComboBox<String> choice;
	TextArea arrangement;
	
	
	public static void main(String[] args) {
		launch(args); //launch() is a static method of the Application class
	}

	@Override
	public void start(Stage stage0) throws Exception {
		// TODO Auto-generated method stub
//		Group root = new Group();
//		Scene scene = new Scene(root, Color.BLACK);
//		
//		stage0.setTitle("Demo");
//		
//		Image icon = new Image("seat_icon.jpg");
//		stage0.getIcons().add(icon);
//		
//		stage0.setHeight(500);
//		stage0.setWidth(500);
//		stage0.setResizable(false);
//		stage0.setScene(scene);
//		stage0.show();
		
		stage0.setTitle("Seat Sorter");
		
		Image icon = new Image("seat_icon.jpg");
		stage0.getIcons().add(icon);
		
		VBox layout0 = new VBox();
		
	    subBtn = new Button("Submit");
		subBtn.setOnAction(this); //means that clicking the button will trigger the event handler of the current class instance.
//		layout0.getChildren().add(button0);
		
		Label idStartLb = new Label("First ID:");
		
		idStart = new TextField();
		TextFormatter<Integer> startFormatter = new TextFormatter<Integer>(new IntegerStringConverter(),0,new PosIntFilter());
		//formatter.valueProperty().bindBidirectional(elementProperty);
		idStart.setTextFormatter(startFormatter);
		
		Label idEndLb = new Label("Last ID:");
		
		idEnd = new TextField();
		TextFormatter<Integer> endFormatter = new TextFormatter<Integer>(new IntegerStringConverter(),0,new PosIntFilter());
		//formatter.valueProperty().bindBidirectional(elementProperty);
		idEnd.setTextFormatter(endFormatter);
		
		Label rowSeatLb = new Label("Number of rows:");
		
		rowSeat = new TextField();
		TextFormatter<Integer> rowFormatter = new TextFormatter<Integer>(new IntegerStringConverter(),0,new PosIntFilter());
		//formatter.valueProperty().bindBidirectional(elementProperty);
		rowSeat.setTextFormatter(rowFormatter);
		
		Label colSeatLb = new Label("Number of columns:");
		
		colSeat = new TextField();
		TextFormatter<Integer> colFormatter = new TextFormatter<Integer>(new IntegerStringConverter(),0,new PosIntFilter());
		//formatter.valueProperty().bindBidirectional(elementProperty);
		colSeat.setTextFormatter(colFormatter);
		
		Label choiceLb = new Label("Arrangement Type:");
		
		choice = new ComboBox<String>();
		choice.getItems().add("ordered");
		choice.getItems().add("random");
		choice.getSelectionModel().selectFirst();
		
		Label arrangementLb = new Label("Seat Arrangement:");
		
		arrangement = new TextArea();
		arrangement.setEditable(false);
		
		layout0.getChildren().addAll(idStartLb, idStart, idEndLb, idEnd, rowSeatLb, rowSeat, colSeatLb, colSeat, choiceLb, choice, subBtn, arrangementLb, arrangement);
		
		Scene scene0 = new Scene(layout0, 500, 500);
		stage0.setScene(scene0);
		stage0.show();
		
	}
	
	@Override
	public void handle(ActionEvent event0) {
		if(event0.getSource()==subBtn) {
						
//			if(choice.getValue() == "ordered") {
//				arrangement.setText(Integer.toString(Integer.parseInt(rowSeat.getText())+Integer.parseInt(colSeat.getText())));
//			}
//			else {
////				arrangement.setText(Integer.toString(Integer.parseInt(rowSeat.getText())*3));
//				arrangement.setText(Integer.toString(Integer.parseInt(idStart.getText())+Integer.parseInt(idEnd.getText())));
//			}
			
			int row = Integer.parseInt(rowSeat.getText());
			int col = Integer.parseInt(colSeat.getText());
			int startRange = Integer.parseInt(idStart.getText());
			int endRange = Integer.parseInt(idEnd.getText());
			int total = endRange-startRange+1;
//			int total = endRange-startRange;
			int limit = row*col;
			
			if(total <= limit) {
				int[][] seats = new int[row][col];
				String cond = choice.getValue();
				
				switch(cond) {
					case "ordered":
						int val = startRange;
						int rowNum = 0;
						int colNum = 0;
						for(int i=1; i<=limit; i++) {
							if(colNum >= col) {
								rowNum = rowNum+1;
								colNum = 0;
							}
//							if(val > endRange) {
//								break;
//							}
							seats[rowNum][colNum] = val;
							val = val+1;
							if(val > endRange) {
								break;
							}
							
							colNum = colNum+1;
							
						}
						break;
					
					case "random":
						int[] idList = new int[total];
						int id = startRange;
						for (int i = 0; i<=total-1; i++) {
							idList[i] = id;
							id = id+1;
						}
						
					/*	for(int i: idList) {	//print check
							System.out.println(i);
						} */
						
//						System.out.println("--------------------------------");
						
						Random r = new Random();
						for (int curInd = 0; curInd <= idList.length-1; curInd++) {
							int ranInd = r.nextInt(idList.length);
							int temp = idList[ranInd];
							idList[ranInd] = idList[curInd];
							idList[curInd] = temp;
						}
						
						int ind = 0;
						int rowCount = 0;
						int colCount = 0;
						for(int i=1; i<=limit; i++) {
							if(colCount >= col) {
								rowCount = rowCount+1;
								colCount = 0;
							}
							
							seats[rowCount][colCount] = idList[ind];
							ind = ind+1;
							if(ind > idList.length-1) {
								break;
							}
							
							colCount = colCount+1;
							
						}
						
					/*	for(int i: idList) {	//print check
							System.out.println(i);
						} */
				
				}
				
			/*	System.out.println(Arrays.deepToString(seats)     //copied from StackOverflow; https://stackoverflow.com/questions/19648240/the-best-way-to-print-a-java-2d-array
		                .replace("],","\n").replace(",","\t| ")
		                .replaceAll("[\\[\\]]", " ")); */
				
				arrangement.setText(Arrays.deepToString(seats)     //copied from StackOverflow; https://stackoverflow.com/questions/19648240/the-best-way-to-print-a-java-2d-array
		                .replace("],","\n").replace(",","\t| ")
		                .replaceAll("[\\[\\]]", " "));
				
//				for(int[] arr: seats) {
//					for(int num: arr) {
//						System.out.println(num);
//					}
//				}
			}
			else {
				arrangement.setText("**Number of students exceeds seat capacity**");
			}
			
		}
	}
}
