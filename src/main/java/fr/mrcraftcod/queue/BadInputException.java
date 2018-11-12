package fr.mrcraftcod.queue;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-11-12.
 *
 * @author Thomas Couchoud
 * @since 2018-11-12
 */
public class BadInputException extends Exception{
	private final InputField[] fields;
	
	public BadInputException(String reason, InputField... fields){
		super(reason);
		this.fields = fields;
	}
	
	public InputField[] getFields(){
		return fields;
	}
}
