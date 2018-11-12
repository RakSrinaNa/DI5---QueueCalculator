package fr.mrcraftcod.queue;

import javafx.scene.layout.HBox;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-11-12.
 *
 * @author Thomas Couchoud
 * @since 2018-11-12
 */
public class CircleProgress extends HBox{
	public CircleProgress(){
		super();
		setPrefHeight(10);
		setMaxWidth(Double.MAX_VALUE);
	}
	
	public void clear(){
		getChildren().clear();
	}
	
	public void setCount(Double d){
		getChildren().clear();
		if(Objects.nonNull(d)){
			final var complete = d.intValue();
			IntStream.range(0, complete).forEach(this::addFullCircle);
			final var decimal = d - complete;
			if(d > 0){
				getChildren().add(new ProgressCircle(decimal));
			}
		}
	}
	
	private void addFullCircle(int i){
		getChildren().add(new ProgressCircle(1));
	}
}
