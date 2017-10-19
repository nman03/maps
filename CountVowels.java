package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CountVowels extends Application {
	TextArea text = new TextArea();
	HashMap<Character, Integer> map = new HashMap<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		Label prompt = new Label("Enter path for text file: ");
		TextField filePath = new TextField();
		Button displayButton = new Button("Display");
		
		instantiateMap();
		displayButton.setOnAction(e -> display(readFile(filePath.getText())));

		HBox header = new HBox();
		header.getChildren().addAll(prompt, filePath, displayButton);

		root.setTop(header);
		root.setCenter(text);
		Scene scene = new Scene(root,400,400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Problem 2");
		primaryStage.show();
	}
	
	private void instantiateMap() {
		map.put('a', 0);
		map.put('e', 0);
		map.put('i', 0);
		map.put('o', 0);
		map.put('u', 0);
	}

	private HashMap<Character, Integer> readFile(String filePath) {

		File inFile = new File(filePath);

		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(inFile);

			while (s.hasNext()) {
				String temp = s.next();

				for (int i = 0 ; i < temp.length() ; i++) {
					if (map.containsKey(temp.charAt(i))) {
						map.replace(temp.charAt(i), map.get(temp.charAt(i)) + 1);
					}
				}
			}	

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			text.appendText("Error 404 - File Not Found");
		}

		return map;
	}

	private void display(HashMap<Character, Integer> map) {
		text.clear();
		for (Character key : map.keySet()) {
			text.appendText(String.valueOf(key) + " = " + String.valueOf(map.get(key)) + "\n");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
