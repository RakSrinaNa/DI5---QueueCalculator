package fr.mrcraftcod.queue;

/**
 * A text field accepting only numbers.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 24/05/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-05-24
 */
public class DecimalNumberField extends NumberField<Double>{
	/**
	 * Constructor.
	 * The default value will be set to 0.
	 */
	public DecimalNumberField(){
		this(0D);
	}
	
	/**
	 * Constructor.
	 *
	 * @param defaultValue The default value to set at the beginning.
	 */
	public DecimalNumberField(Double defaultValue){
		super(defaultValue);
	}
	
	/**
	 * Validate the changes or not.
	 *
	 * @param text The text to valid.
	 *
	 * @return True if a number matching the verifier, false else.
	 */
	@Override
	protected boolean validate(String text){
		return text.matches("[0-9]*(\\.[0-9]*)?");
	}
	
	/**
	 * Get the text as an int.
	 *
	 * @return The value.
	 */
	public Double getDouble(){
		return getText().isBlank() ? null : Double.parseDouble(getText());
	}
}
