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
	public static final double radius = 5;
	
	public ProgressCircle(double d, Color color){
		final var rec = new Rectangle();
		rec.setX(-radius);
		rec.setY(-radius);
		rec.setWidth(2 * radius);
		rec.setHeight(2 * radius);
		rec.setFill(Color.TRANSPARENT);
		
		final var arc = new Arc();
		arc.setRadiusX(radius);
		arc.setRadiusY(radius);
		arc.setStartAngle(0f);
		arc.setLength(d * 360);
		arc.setType(ArcType.ROUND);
		arc.setFill(color);
		
		getChildren().addAll(rec, arc);
	}
}
