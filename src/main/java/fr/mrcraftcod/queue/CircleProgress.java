package fr.mrcraftcod.queue;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
		setPrefHeight(2 * ProgressCircle.radius);
		setMaxWidth(Double.MAX_VALUE);
	}
	
	public void clear(){
		getChildren().clear();
	}
	
	public void setCount(Double d){
		getChildren().clear();
		if(Objects.nonNull(d)){
			var complete = d.intValue();
			for(; complete >= 1000; complete -= 1000){
				addCircle(1, Color.RED);
			}
			for(; complete >= 100; complete -= 100){
				addCircle(1, Color.ORANGE);
			}
			IntStream.range(0, complete).forEach(i -> this.addCircle(1, Color.BLACK));
			final var decimal = d - complete;
			if(decimal > 0){
				addCircle(decimal, Color.BLACK);
			}
		}
	}
	
	private void addCircle(double progress, Color color){
		getChildren().add(new ProgressCircle(progress, color));
	}
}
