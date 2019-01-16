package fr.mrcraftcod.queue;

import javafx.scene.control.TextField;
import java.util.Objects;

/**
 * A text field accepting only numbers.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 24/05/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-05-24
 */
public class NumberField<T> extends TextField{
	/**
	 * Constructor.
	 *
	 * @param defaultValue The default value to set at the beginning.
	 */
	public NumberField(T defaultValue){
		super("" + (Objects.isNull(defaultValue) ? "" : defaultValue));
	}
	
	@Override
	public void replaceText(int start, int end, String text){
		if(validate(getText().substring(0, start) + text + getText().substring(Math.min(start + 1, getText().length())))){
			super.replaceText(start, end, text);
		}
	}
	
	@Override
	public void replaceSelection(String text){
		if(validate(text)){
			super.replaceSelection(text);
		}
	}
	
	/**
	 * Validate the changes or not.
	 *
	 * @param text The text to valid.
	 *
	 * @return True if a number matching the verifier, false else.
	 */
	protected boolean validate(String text){
		return text.length() <= 3 && text.matches("[0-9]*");
	}
	
	/**
	 * Get the text as an int.
	 *
	 * @return The value.
	 */
	public Integer getInt(){
		return getText().isBlank() ? null : Integer.parseInt(getText());
	}
}
