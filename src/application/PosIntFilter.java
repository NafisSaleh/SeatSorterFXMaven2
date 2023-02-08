package application;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;

public class PosIntFilter implements UnaryOperator<TextFormatter.Change> {

	@Override
	public TextFormatter.Change apply(TextFormatter.Change ch) {
        if (ch.getControlNewText().matches("[0-9]*")) {
            return ch;
        }
        return null;
    }
}
