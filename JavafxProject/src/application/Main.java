package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Main extends Application {
	private double accountBalance = 1000.0; // Initial balance for demonstration

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		primaryStage.setTitle("ATM Interface");

		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(20, 20, 20, 20));

		Label titleLabel = new Label("Welcome to MyBank ATM");
		Label balanceLabel = new Label("Current Balance: $" + accountBalance);

		Button checkBalanceButton = new Button("Check Balance");
		Button withdrawButton = new Button("Withdraw Cash");
		Button depositButton = new Button("Deposit Cash");
		Button exitButton = new Button("Exit");

		TextField withdrawalAmountField = new TextField();
		withdrawalAmountField.setPromptText("Enter amount to withdraw");

		TextField depositAmountField = new TextField();
		depositAmountField.setPromptText("Enter amount to deposit");

		Label messageLabel = new Label();

		checkBalanceButton.setOnAction(event -> {
			messageLabel.setText("Current Balance: $" + accountBalance);
		});

		withdrawButton.setOnAction(event -> {
			try {
				double amount = Double.parseDouble(withdrawalAmountField.getText());
				if (amount <= accountBalance) {
					accountBalance -= amount;
					messageLabel.setText("Withdrawal Successful: $" + amount);
				} else {
					messageLabel.setText("Insufficient Balance");
				}
			} catch (NumberFormatException e) {
				messageLabel.setText("Invalid Amount");
			}
		});

		depositButton.setOnAction(event -> {
			try {
				double amount = Double.parseDouble(depositAmountField.getText());
				accountBalance += amount;
				messageLabel.setText("Deposit Successful: $" + amount);
			} catch (NumberFormatException e) {
				messageLabel.setText("Invalid Amount");
			}
		});

		exitButton.setOnAction(event -> {
			primaryStage.close();
		});

		vbox.getChildren().addAll(titleLabel, balanceLabel, checkBalanceButton, withdrawalAmountField, withdrawButton,
				depositAmountField, depositButton, messageLabel, exitButton);

		Scene scene = new Scene(vbox, 300, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
