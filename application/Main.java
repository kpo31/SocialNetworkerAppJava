package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Adapted things from Java's Using JavaFX UI Controls
 * 
 * @author Eli Asher, Mihir Khatri, Harrison Bell, Hunter Abraham, Ian Jameson
 *
 */
public class Main extends Application {
	SocialNetwork network = new SocialNetwork(); // Main network of friends
	String centralUser = ""; // Starting user
	ArrayList<Scene> previous = new ArrayList<Scene>();// Stores previous scene

	/**
	 * Start the GUI
	 */
	@Override
	public void start(Stage stage) {

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: red");
		backButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		// Probally jsut gonna get rid of login scene
		Text username = new Text("Username"); // Create username and password text
		TextField user = new TextField(); // Field to type username and password into
		user.setPromptText(centralUser); // Will change these later

		// Logout button
		Button logout = new Button("X");
		logout.setStyle("-fx-background-color: red");
		logout.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		logout.setPadding(new Insets(3, 22, 3, 20));

		// Pane for logout screen
		BorderPane logoutPane = new BorderPane();
		logoutPane.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

		logoutPane.setCenter(new Label("ADS"));
		// Two button options, save and exit
		Button saveButton = new Button("Save");
		Button exitProgram = new Button("Exit");
		exitProgram.setStyle("-fx-background-color: red");
		exitProgram.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		logoutPane.setLeft(exitProgram);
		logoutPane.setRight(saveButton);

		// Save file pane
		BorderPane saveFilePane = new BorderPane();
		saveFilePane.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
		// text field for
		TextField saveFileText = new TextField();
		saveFileText.setText("example.txt");
		saveFilePane.setCenter(saveFileText);

		// button to save network
		Button saveButtonFile = new Button("Save");
		saveFilePane.setLeft(saveButtonFile);

		// compile elements into scenes
		Scene logoutScene = new Scene(logoutPane, 300, 300);
		Scene saveFileScene = new Scene(saveFilePane, 300, 300);
		logout.setOnAction(e -> stage.setScene(logoutScene));
		exitProgram.setOnAction(e -> stage.setScene(logoutScene));

		// create initial screen
		Button submit = new Button("Submit");
		Button loadFile = new Button(" Clear or Load File  ");
		loadFile.setPadding(new Insets(5, 28, 5, 28));

		GridPane login = new GridPane();
		login.setMinSize(250, 150); // Make grid right size

		// Set the gradient to the login page
		login.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

		// Setting up the spacing and layout of the login page
		login.setVgap(5);
		login.setHgap(5);
		login.setAlignment(Pos.CENTER);

		// Create account button
		Button createAcc = new Button("Create New Account"); // FINISH SCENE
		createAcc.setPadding(new Insets(5, 25, 5, 25));

		// Add elements to login page
		login.add(username, 0, 0);
		login.add(user, 1, 0);
		login.add(logout, 0, 2);
		login.add(createAcc, 1, 2);
		login.add(submit, 0, 1);
		login.add(loadFile, 1, 1);

		// Login Scene
		Scene loginScene = new Scene(login, 250, 250);
		stage.setTitle("TWEETER");

		// New scene
		// This scene will appear if help button is pressed
		// Could be seperated and fixed

		// Scene to load a file of friends
		Label loadLabel = new Label("Load New File ");

		TextField loadText = new TextField();
		loadText.setText("example.txt or just click cubmit");

		loadLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		// Button to load network
		Button submitFile = new Button("Submit");

		// Button to clear network
		Button clearNetwork = new Button("Clear");
		// Bottom button
		HBox bottom = new HBox();
		bottom.getChildren().addAll(clearNetwork, submitFile);
		bottom.setPadding(new Insets(15, 12, 15, 12));
		bottom.setSpacing(10);

		// layout for loading file
		BorderPane layout2 = new BorderPane();
		layout2.setAlignment(loadLabel, Pos.TOP_CENTER);
		layout2.setTop(loadLabel);
		layout2.setBottom(bottom);
		layout2.setCenter(loadText);
		// layout2.setLeft(clearNetwork);
		layout2.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");
		Scene loadFileScene = new Scene(layout2, 300, 100);

		// set first scene to load file page
		stage.setScene(loadFileScene);
		stage.show();

		// Scene 4
		// Account creation
		// Text field for entering new username
		Text newUser = new Text("Username");
		TextField newUserTextField = new TextField("letters, digits, underscore, apostrophe");
		// arrange text and text field
		BorderPane createNewAccount = new BorderPane();
		createNewAccount.setCenter(newUserTextField);
		createNewAccount.setTop(newUser);
		createNewAccount.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");
		// Put "create" button on the bottom of the page
		Button create = new Button("Create");
		createNewAccount.setBottom(create);

		// Put arrangement into a scene
		Scene createNewACC = new Scene(createNewAccount, 200, 100);

		// These are all the action events for when buttons are pressed will likely need
		// to be changed
		// in the future
		/**
		 * Functional interface to handle loading users
		 */
		EventHandler<ActionEvent> loadUsers = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				previous.add(stage.getScene()); // add previous scene to list
				stage.setScene(loadFileScene); // set current scene to the loadFileScene
			}
		};
		loadFile.setOnAction(loadUsers); // make loadFile Button switch scenes

		/**
		 * Functional interface to handle loading a file
		 */
		EventHandler<ActionEvent> loadFileSub = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				previous.add(stage.getScene()); // add previous scene to list
				if (!loadText.getText().equals("")) { // if the text isn't empty
					if (!loadFile(loadText.getText())) { // if loadFile() returns false
						// show warning message
						Label errorLabel = new Label("EXCEPTION OCCURED WITH READING FILE TRY DIFFERENT FILE");
						errorLabel.setStyle("-fx-background-color: red");
						errorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
						Stage errorMessage = new Stage();
						Scene errorScene = new Scene(errorLabel);
						errorMessage.setScene(errorScene);
						errorMessage.show();
					}
				} else {
					// Otherwise, set the new scene to the loginScene
					stage.setScene(loginScene);
				}
				if (network.string().size() != 0) { // if the network isn't empty,
					user.setPromptText(centralUser); // set the prompt text and scene to login scene
					stage.setScene(loginScene);
				} else {
					// otherwise, if the file is empty,
					loadText.setText("No file. Delete this text and submit, or try new file");
				}

			}
		};
		submitFile.setOnAction(loadFileSub); // add action to button

		/**
		 * Functional interface to handle a login action
		 */
		EventHandler<ActionEvent> loginSubmit = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (network.string().contains(user.getText())) { // if the user exists in the network
					if (user.getText().equals(centralUser)) { // If the user is the central user
						previous.add(stage.getScene()); // add current scene to previous
						// previous = stage.getScene();
						loadUser(user.getText(), stage, loginScene); // load the user's network
					}
				} else {
					user.setText(centralUser); // otherwise, user doesn't have access
				}
			}
		};
		submit.setOnAction(loginSubmit); // make submit button have appropriate handler

		/**
		 * Functional interface to handle creating a new account
		 */
		EventHandler<ActionEvent> createNewAcc = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				previous.add(stage.getScene());
				// previous = stage.getScene();
				stage.setScene(createNewACC);
			}
		};
		createAcc.setOnAction(createNewAcc); // assign action to button

		/**
		 * Functional interface to create a new account
		 */
		EventHandler<ActionEvent> createNewAcco = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try { // try to create a new account
					network.createNewAccount(newUserTextField.getText());
				} catch (Exception q) { // catch any error and print error message
					newUserTextField.setText("name not valid");
					Label errorLabel = new Label("a username may only contain letters, digits, underscore, apostrophe");
					Stage errorMessage = new Stage();
					Scene errorScene = new Scene(errorLabel);
					errorMessage.setScene(errorScene);
					errorMessage.show();
				}
				if (centralUser.equals("")) { // if the central user is an empty string, make name
					centralUser = newUserTextField.getText();
					log = log + "s " + newUserTextField.getText() + "\n";
				}
				log = log + "a " + newUserTextField.getText() + "\n";
				stage.setScene(previous.get(previous.size() - 1)); // return to previous scene
				previous.remove(previous.size() - 1); // remove from previous
			}
		};
		create.setOnAction(createNewAcco); // assign action to button

		/**
		 * Functional interface to go back a screen
		 */
		EventHandler<ActionEvent> backBut = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(previous.get(previous.size() - 1)); // set scene to previous
				previous.remove(previous.size() - 1); // remove scence from list of previous
			}
		};
		backButton.setOnAction(backBut); // assign action

		/**
		 * Functional interface to exit program
		 */
		EventHandler<ActionEvent> exitProg = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				logoutPane.setCenter(goodbye); // set the middle of screen to say "Goodbye"
			}
		};
		exitProgram.setOnAction(exitProg); // assign action

		/**
		 * Functional interface to move to saveFile scene
		 */
		EventHandler<ActionEvent> exitSave = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(saveFileScene); // set scene
			}
		};
		saveButton.setOnAction(exitSave); // assign action to button

		/**
		 * Functional interface to save network to file to
		 */
		EventHandler<ActionEvent> submitSave = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				boolean test = true;
				try {
					saveLogFile(saveFileText.getText()); // save file
				} catch (Exception e1) { // catch any exception and set flag to false
					test = false;
				}
				if (test) { // if saved correctly, display success message
					saveFilePane.setCenter(goodbyeSave);
				} else {
					saveFileText.setText("Error in writing file, choose new name"); // if it didn't work, print error
				}
			}
		};
		saveButtonFile.setOnAction(submitSave); // assign action

		/**
		 * Functional interface to clear the network
		 */
		EventHandler<ActionEvent> clearNetworkEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				network.clear(); // clear network
				log = ""; // clear any log variables
				centralUser = "";
				stage.setScene(loadFileScene); // return to loadFile Scene
				previous.clear(); // clear previous screen
				loadText.setText("Network clear, load file or submit"); // set text on screen
			}
		};
		clearNetwork.setOnAction(clearNetworkEvent); // assign action

		goodbye.setOnAction(e -> stage.close()); // set actions to goodbye buttons
		goodbyeSave.setOnAction(e -> stage.close());
	}

	/**
	 * Saves the log of all additions and subtractions
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	private void saveLogFile(String fileName) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.println(log);
		writer.close();
	}

	/**
	 * Launches GUI
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		launch(args);
	}

	/**
	 * Adds the Goto button to the table Adapted portions from
	 * https://riptutorial.com/javafx
	 * 
	 * @param stage     main stage that is show
	 * @param tableMain table for the user
	 */
	private void goTo(Stage stage, TableView<Person> tableMain) {
		Scene scene = stage.getScene(); // find scene
		TableColumn<Person, Void> goToPageCol = new TableColumn("User Page");

		Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
			@Override
			public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
				final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

					private final Button goToPage = new Button("Go to Page"); // make button to go to page

					{
						goToPage.setOnAction((ActionEvent event) -> { // assign action to button
							Person username = getTableView().getItems().get(getIndex());
							previous.add(stage.getScene());
							loadUser(username.getUserName(), stage, scene);
						});
					}

					/**
					 * Updates an item
					 * 
					 * @param item     - item to be updated
					 * @param emptyRow - if there is an empty row in table
					 */
					@Override
					public void updateItem(Void item, boolean emptyRow) { // update the item with an empty row
						super.updateItem(item, emptyRow);
						if (emptyRow) {
							setGraphic(null);
						} else {
							setGraphic(goToPage);
						}
					}
				};
				return cell; // return TableCell generated
			}
		};
		goToPageCol.setCellFactory(cell); // set cell to cell generated

		tableMain.getColumns().add(goToPageCol);
	}

	/**
	 * Adds the Add button to the table Adapted portions from
	 * https://riptutorial.com/javafx
	 * 
	 * @param stage    stage that is show
	 * @param table
	 * @param mainUser
	 */
	private void goToAdd(Stage stage, TableView<Person> table, Person mainUser) {
		Scene scene = stage.getScene();
		TableColumn<Person, Void> goToPageCol = new TableColumn("User Page"); // This is the column that
																				// holds the button to go
																				// to a user page
		Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
			@Override
			public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
				final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

					private final Button addFriendPage = new Button("Add");

					{
						addFriendPage.setOnAction((ActionEvent event) -> {
							Person username = getTableView().getItems().get(getIndex());
							network.addFriend(username.getUserName(), mainUser.getUserName());
							log = log + "a " + username.getUserName() + " " + mainUser.getUserName() + "\n";
							loadUser(mainUser.getUserName(), stage, scene);
						});
					}

					@Override
					public void updateItem(Void item, boolean emptyRow) {
						super.updateItem(item, emptyRow);
						if (emptyRow) { // if there is an emptyRow, make graphic null
							setGraphic(null);
						} else {
							setGraphic(addFriendPage);
						}
					}
				};
				return cell; // return TableCell
			}
		};
		goToPageCol.setCellFactory(cell); // set the column to the tableCell

		table.getColumns().add(goToPageCol); // the "goto" column to the table
	}

	/**
	 * 
	 * Recursive method to load all user data
	 * 
	 * @param user  user to load
	 * @param stage the main stage that is show
	 * @param main  the main scene
	 */
	private void loadUser(String user, Stage stage, Scene main) {
		Person mainUser = null;
		// find user in the network
		for (Person user_1 : network.get()) {
			if (user_1.getUserName().equals(user)) {
				mainUser = user_1;
				break;
			}
		}
		TableView<Person> tableMain = new TableView<Person>();
		// This will eventually just find the user in the graph and read the connections
		ObservableList<Person> dataMain = FXCollections.observableArrayList(network.getFriendList(user));

		Button backButton = new Button("BACK");
		backButton.setStyle("-fx-background-color: red");
		backButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		BorderPane home = new BorderPane();
		Label name = new Label(user); // Take username
		name.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		home.setAlignment(name, Pos.TOP_CENTER);
		home.setStyle("-fx-background-color: white");
		home.setTop(name);
		home.setBottom(backButton);

		ImageView images = new ImageView(); // Profile pic would exist in create new account
		images.setFitHeight(300);
		images.setFitWidth(400);
		Image picture = new Image("me.png");
		images.setImage(picture);
		home.setCenter(tableMain);

		VBox homeVbox = new VBox(); // Side panel
		homeVbox.setPadding(new Insets(10));
		homeVbox.setSpacing(8);
		homeVbox.setStyle("-fx-background-color: darkblue ");

		Text title = new Text("Friends");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		title.setFill(Color.WHITE);
		homeVbox.getChildren().add(title);

		Button viewFriends = new Button("View Friends"); // Load a list of users friends
		viewFriends.setStyle("-fx-background-color: white");
		viewFriends.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		VBox.setMargin(viewFriends, new Insets(0, 0, 0, 8));
		// homeVbox.getChildren().add(viewFriends); // Possibly add a small friends
		// scene to show their
		// pages
		// Also add a back button

		// Scene five to show friends
		BorderPane showFriends = new BorderPane();
		showFriends.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
		Scene scene5 = new Scene(showFriends, 450, 450);
		final Label label = new Label("Friends");

		showFriends.setAlignment(label, Pos.TOP_CENTER);
		label.setFont(Font.font("Courier", FontWeight.BOLD, 20));

		tableMain.setEditable(true);

		// All of this will be formatted differently in order to accommodate a graph
		TableColumn userName = new TableColumn("Username");
		userName.setMinWidth(150);
		userName.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

		tableMain.setItems(dataMain);
		tableMain.getColumns().addAll(userName);
		goTo(stage, tableMain);
		viewMutuals(stage, tableMain, mainUser);

		final VBox vList = new VBox();
		vList.setSpacing(5);
		vList.setPadding(new Insets(10, 0, 0, 10));
		vList.getChildren().addAll(tableMain);
		tableMain.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

		Button backButtonOther = new Button("BACK");
		backButtonOther.setStyle("-fx-background-color: red");
		backButtonOther.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		showFriends.setTop(label);
		showFriends.setCenter(vList);
		showFriends.setLeft(new Label("ADS")); // LOL
		showFriends.setRight(new Label("ADS"));
		showFriends.setBottom(backButtonOther);

		// Add a scene with a search function This could show a list of the users in the
		// graph and
		// let
		// the main user add a connection to them
		Button addFriend = new Button("Add Friend");
		addFriend.setStyle("-fx-background-color: white");
		addFriend.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		VBox.setMargin(addFriend, new Insets(0, 0, 0, 8));
		homeVbox.getChildren().add(addFriend); // add new scene

		BorderPane addFriends = new BorderPane();
		addFriends.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
		final Label labelOther = new Label("All People");
		labelOther.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		addFriends.setAlignment(labelOther, Pos.TOP_CENTER);
		label.setFont(Font.font("Courier", FontWeight.BOLD, 20));

		TableView<Person> allTable = new TableView<Person>();

		ArrayList<Person> temp = network.get();
		ArrayList<Person> temp_1 = network.getFriendList(user);
		temp.remove(network.search(user));
		temp.removeAll(temp_1);

		ObservableList<Person> data = FXCollections.observableArrayList(temp);
		allTable.setEditable(true);

		// All of this will be formatted differently in order to accommodate a graph
		TableColumn userNameOther = new TableColumn("Username");
		userNameOther.setMinWidth(150);
		userNameOther.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

		allTable.setItems(data);
		allTable.getColumns().addAll(userNameOther);
		goToAdd(stage, allTable, mainUser);

		final VBox vList2 = new VBox();
		vList2.setSpacing(5);
		vList2.setPadding(new Insets(10, 0, 0, 10));
		vList2.getChildren().addAll(allTable);
		allTable.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

		addFriends.setTop(labelOther);
		addFriends.setCenter(vList2);

		Button backButton_two = new Button("Back");
		backButton_two.setStyle("-fx-background-color: red");
		backButton_two.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		addFriends.setAlignment(backButton_two, Pos.BOTTOM_LEFT);
		addFriends.setBottom(backButton_two);

		Scene addFriendsScene = new Scene(addFriends, 450, 450);

		Button removeFr = new Button("Remove Friend"); // Go to scene with list of friends
		// and delete from it
		removeFr.setStyle("-fx-background-color: white");
		removeFr.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		VBox.setMargin(removeFr, new Insets(0, 0, 0, 8));
		homeVbox.getChildren().add(removeFr); // add new scene

		TableView<Person> tableMainRemove = new TableView<Person>();
		ObservableList<Person> dataMainRemove = FXCollections.observableArrayList(network.getFriendList(user));

		BorderPane remove = new BorderPane();
		remove.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
		Scene removeFriendsScene = new Scene(remove, 450, 450);
		final Label label_remove = new Label("Remove Friends");

		remove.setAlignment(label, Pos.TOP_CENTER);
		label_remove.setFont(Font.font("Courier", FontWeight.BOLD, 20));

		tableMainRemove.setEditable(true);

		tableMainRemove.setItems(dataMainRemove);
		tableMainRemove.getColumns().addAll(userName);
		goToRemove(stage, tableMainRemove, mainUser);

		final VBox vList_2 = new VBox();
		vList_2.setSpacing(5);
		vList_2.setPadding(new Insets(10, 0, 0, 10));
		vList_2.getChildren().addAll(tableMainRemove);
		tableMainRemove.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

		Button allUsers = new Button("All Users"); // Load a list of users friends
		allUsers.setStyle("-fx-background-color: white");
		allUsers.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		VBox.setMargin(allUsers, new Insets(0, 0, 0, 8));
		homeVbox.getChildren().add(allUsers);

		Button backButtonOtherTwo = new Button("BACK");
		backButtonOtherTwo.setStyle("-fx-background-color: red");
		backButtonOtherTwo.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		remove.setTop(label_remove);
		remove.setCenter(vList_2);
		remove.setLeft(new Label("ADS")); // LOL
		remove.setRight(new Label("ADS")); // Making sure we can monetize this
		remove.setBottom(backButtonOtherTwo);

		home.setLeft(homeVbox);
		home.setBottom(backButton);
		home.setCenter(vList);

		Scene userScene = new Scene(home, 500, 500);
		stage.setScene(userScene);

		// These are all the event handlers for the buttons

		/**
		 * Allows user to view friends
		 */
		EventHandler<ActionEvent> viewFriendsEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				previous.add(stage.getScene()); // save prior scene

				stage.setScene(scene5); // set scene to the new scene
			}
		};
		viewFriends.setOnAction(viewFriendsEvent); // assign action to button

		/**
		 * Moves back a page
		 */
		EventHandler<ActionEvent> backBut = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(previous.get(previous.size() - 1)); // set scene to previous
				previous.remove(previous.size() - 1); // remove from previous pages
			}
		};
		backButton.setOnAction(backBut);

		/**
		 * alias for previous functional interface
		 */
		EventHandler<ActionEvent> backButt = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(previous.get(previous.size() - 1));
				previous.remove(previous.size() - 1);
			}
		};
		backButtonOther.setOnAction(backButt);

		/**
		 * Another alias for previous functional interface
		 */
		EventHandler<ActionEvent> backButt_2 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(previous.get(previous.size() - 1));
				previous.remove(previous.size() - 1);
			}
		};
		backButtonOtherTwo.setOnAction(backButt);

		/**
		 * Another alias for previous functional interface
		 */
		EventHandler<ActionEvent> backButton_Two = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.setScene(previous.get(previous.size() - 1));
				previous.remove(previous.size() - 1);
			}
		};
		backButton_two.setOnAction(backButton_Two);

		/**
		 * Move to add friend scene
		 */
		EventHandler<ActionEvent> addFriendEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				previous.add(stage.getScene());
				stage.setScene(addFriendsScene);
			}
		};
		addFriend.setOnAction(addFriendEvent);

		/**
		 * Move to remove friend scene
		 */
		EventHandler<ActionEvent> removeFriendEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				previous.add(stage.getScene());
				stage.setScene(removeFriendsScene);
			}
		};
		removeFr.setOnAction(removeFriendEvent);

		/**
		 * Move to screen that displays all friends
		 */
		EventHandler<ActionEvent> allFriendsEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Label userNumber = new Label(Integer.toString(network.string().size()));
				userNumber.setFont(Font.font("Arial", FontWeight.BOLD, 24));
				BorderPane allUserPane = new BorderPane();
				allUserPane.setTop(userNumber);
				allUserPane.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
				allUserPane.setAlignment(userNumber, Pos.CENTER);
				ArrayList<String> all = network.string();
				ComboBox<String> allUser = new ComboBox<String>(FXCollections.observableArrayList(all));

				allUserPane.setCenter(allUser);

				Scene allFriendsScene = new Scene(allUserPane, 200, 200);
				Stage stage = new Stage();
				stage.setScene(allFriendsScene);
				stage.show();
			}
		};
		allUsers.setOnAction(allFriendsEvent);

	}

	/**
	 * Adds the remove button to the table Adapted portions from
	 * https://riptutorial.com/javafx
	 * 
	 * @param stage
	 * @param table
	 * @param mainUser
	 */
	private void goToRemove(Stage stage, TableView<Person> table, Person mainUser) {
		Scene scene = stage.getScene();
		TableColumn<Person, Void> goToPageCol = new TableColumn("Remove"); // This is the column that
																			// holds the button to go
																			// to a user page
		Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
			@Override
			public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
				final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

					private final Button removeFriendPage = new Button("Remove");

					{
						removeFriendPage.setOnAction((ActionEvent event) -> {
							Person username = getTableView().getItems().get(getIndex());
							network.removeFriend(username.getUserName(), mainUser.getUserName());
							log = log + "r " + username.getUserName() + " " + mainUser.getUserName() + "\n";
							loadUser(mainUser.getUserName(), stage, scene);
						});
					}

					@Override
					public void updateItem(Void item, boolean emptyRow) {
						super.updateItem(item, emptyRow);
						if (emptyRow) {
							setGraphic(null);
						} else {
							setGraphic(removeFriendPage);
						}
					}
				};
				return cell;
			}
		};
		goToPageCol.setCellFactory(cell);

		table.getColumns().add(goToPageCol);
	}

	/**
	 * Loads a file into the network
	 * 
	 * @param fileName name of file to be loaded
	 */
	private boolean loadFile(String fileName) {
		try {
			FileReader file = new FileReader(fileName);
			Scanner sc = new Scanner(file);
			String[] data = sc.nextLine().trim().split(" ");
			while (sc.hasNextLine()) {

				// check if adding of some sort will occur
				if (data[0].equals("a")) {
					if (data.length == 2) {
						log = log + "a " + data[1] + "\n";
						network.createNewAccount(data[1]);
					} else if (data.length == 3) {// need to create a friendship (edge) between two users
						if (!network.string().contains(data[1])) {
							network.createNewAccount(data[1]);
						}
						if (!network.string().contains(data[2])) {
							network.createNewAccount(data[2]);
						}
						network.addFriend(data[1], data[2]);
						log = log + "a " + data[1] + " " + data[2] + "\n";
					}
				} else if (data[0].equals("r")) {// will remove either friendship or user
					if (data.length == 2) {
						network.removeUser(data[1]);
						log = log + "r " + data[1] + "\n";
					} else if (data.length == 3) {// need to remove a friendship (edge) between two users
						network.removeFriend(data[1], data[2]);
						log = log + "r " + data[1] + " " + data[2] + "\n";
					}
				} else if (data.length == 3) {
					network.removeFriend(data[1], data[2]);
				} else if (data[0].equals("s")) {
					// TODO: Load the Screen of the User
					centralUser = data[1];
					log = log + "s " + data[1] + "\n";
				}
				data = sc.nextLine().trim().split(" ");
			}
		} catch (DuplicateNameException e1) {
			return false;
			// e1.printStackTrace();
		} catch (Exception e1) {
			return false;
			// e1.printStackTrace();
		}
		return true;
	}

	/**
	 * Method to view mutual friends
	 * 
	 * @param stage     - the stage of the GUI
	 * @param tableMain - the table to store all mutuals
	 * @param mainUser  - the first user
	 */
	private void viewMutuals(Stage stage, TableView<Person> tableMain, Person mainUser) {
		TableColumn<Person, Void> goToPageCol = new TableColumn("Mutual Friends");

		Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
			@Override
			public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
				final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

					private final Button goToPage = new Button("Mutuals");

					{
						goToPage.setOnAction((ActionEvent event) -> { // make button to view mutuals
							Person username = getTableView().getItems().get(getIndex());
							previous.add(stage.getScene());

							ArrayList<String> mutual = network.mutuals(username.getUserName(), mainUser.getUserName());
							ComboBox<String> mutualFriends = new ComboBox<String>(
									FXCollections.observableArrayList(mutual));

							VBox vbox = new VBox();
							vbox.setPadding(new Insets(10));
							vbox.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
							Text title = new Text("Mutual Friends");
							title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
							title.setFill(Color.BLACK);
							vbox.getChildren().add(title);

							vbox.getChildren().add(mutualFriends);

							Scene mutualScene = new Scene(vbox, 200, 350);
							Stage stageTwo = new Stage();
							stageTwo.setScene(mutualScene);
							stageTwo.show();
						});
					}

					/**
					 * Method to update and item
					 */
					@Override
					public void updateItem(Void item, boolean emptyRow) {
						super.updateItem(item, emptyRow); // update item
						if (emptyRow) { // if it has an empty row, set the graphic to null
							setGraphic(null);
						} else {
							setGraphic(goToPage); // otherwise, set page
						}
					}
				};
				return cell; // return new addition to column
			}
		};
		goToPageCol.setCellFactory(cell); // add addition to column

		tableMain.getColumns().add(goToPageCol); // add column to table
	}

	String log = ""; // logs values to save to exit file
	Button goodbye = new Button("Exiting without saving, Goodbye"); // exit button without save
	Button goodbyeSave = new Button("Successful Save"); // exit button with save
}
