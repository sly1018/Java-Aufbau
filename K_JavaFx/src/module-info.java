module javaFxDemos {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;

	// Damit JavaFX unsere Application und View Klassen mit Reflection zugreifen
	// darf
	// sonst gibt es solche Fehler: class com.sun.javafx.application.LauncherImpl
	// (in module javafx.graphics) cannot access class intro.IntroMain (in module
	// javaFxDemos) because module javaFxDemos does not export intro to module
	// javafx.graphics
	opens intro;
	opens introFxml;
	opens menus;
}