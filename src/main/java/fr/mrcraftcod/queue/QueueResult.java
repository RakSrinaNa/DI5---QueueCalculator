package fr.mrcraftcod.queue;

public class QueueResult{
	
	final private double lq;
	final private double l;
	final private double wq;
	final private double w;
	final private Double ref;
	
	public QueueResult(double lq, double l, double wq, double w, Double ref){
		this.lq = lq;
		this.l = l;
		this.wq = wq;
		this.w = w;
		this.ref = ref;
	}
	
	public double getL(){
		return l;
	}
	
	public double getLq(){
		return lq;
	}
	
	public Double getRef(){
		return ref;
	}
	
	public double getW(){
		return w;
	}
	
	public double getWq(){
		return wq;
	}
}
