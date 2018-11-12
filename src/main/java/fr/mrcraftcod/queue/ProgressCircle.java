package fr.mrcraftcod.queue;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-11-12.
 *
 * @author Thomas Couchoud
 * @since 2018-11-12
 */
public class ProgressCircle extends Group{
	public ProgressCircle(double d){
		final var rec = new Rectangle();
		rec.setX(-5);
		rec.setY(-5);
		rec.setWidth(10);
		rec.setHeight(10);
		rec.setFill(Color.TRANSPARENT);
		
		final var arc = new Arc();
		arc.setRadiusX(5);
		arc.setRadiusY(5);
		arc.setStartAngle(0f);
		arc.setLength(d * 360);
		arc.setType(ArcType.ROUND);
		
		getChildren().addAll(rec, arc);
	}
}
