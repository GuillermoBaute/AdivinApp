package dad.maven.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinaApp extends Application {

	private Button botonComprobador;
	private TextField numeroIntroducido;
	private int numAleatorio, intentos;

	private void reInicio() {
		numAleatorio();
		intentos = 0;
	}

	private void numAleatorio() {
		numAleatorio = (int) (Math.random() * 100);
	}

	private void comprobarIntroducido(ActionEvent e) {
		intentos++;
		try {
			int numero = Integer.parseInt(numeroIntroducido.getText());
			if (numero > 100 || numero < 0)
				mostrarError();
			else if (numero < numAleatorio)
				alertFalloMayor(numero);
			else if (numero > numAleatorio)
				alertFalloMenor(numero);
			else {
				if (intentos == 1)
					easterEgg();
				else {
					alertAcierto();
					reInicio();
				}
			}

		} catch (NumberFormatException error) {
			mostrarError();

		}

	}

	private void easterEgg() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("WOW!");
		alert.setContentText("¿En serio a la primera?\n" + "Ve a comprar un décimo de Navidad ya.");
		alert.showAndWait();
	}

	private void alertAcierto() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("¡Has ganado!");
		alert.setContentText("Sólo has necesitado " + intentos + " intentos.\n" + "Vuelve a jugar y hazlo mejor.");
		alert.showAndWait();
	}

	private void alertFalloMayor(int numero) {
		String mensajeModo = "El número a adivinar es mayor que " + numero + ".";
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("AdivinaApp");
		alert.setHeaderText("¡Has fallado!");
		alert.setContentText(mensajeModo + "\n\nVuelve a intentarlo.");
		alert.showAndWait();
	}

	private void alertFalloMenor(int numero) {
		String mensajeModo = "El número a adivinar es menor que " + numero + ".";
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("AdivinaApp");
		alert.setHeaderText("¡Has fallado!");
		alert.setContentText(mensajeModo + "\n\nVuelve a intentarlo.");
		alert.showAndWait();
	}

	private void mostrarError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("Error");
		alert.setContentText("El número introducido no es válido");
		alert.showAndWait();
		intentos--;
	}

	public void start(Stage primaryStage) throws Exception {
		reInicio();

		numeroIntroducido = new TextField();
		numeroIntroducido.setMaxWidth(100);
		numeroIntroducido.setAlignment(Pos.CENTER);

		Label primeraEscena = new Label();
		primeraEscena.setText("Introduce un número del 1 al 100");

		botonComprobador = new Button();
		botonComprobador.setText("Comprobar");
		botonComprobador.setDefaultButton(true);
		botonComprobador.setOnAction(e -> comprobarIntroducido(e));

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(primeraEscena, numeroIntroducido, botonComprobador);

		Scene escena = new Scene(root, 300, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}