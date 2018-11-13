package fr.mrcraftcod.queue;

public class QueueResult{
	
	final private double lq;
	final private double l;
	final private double wq;
	final private double w;
	final private Double ref;
	
	public QueueResult(Double lq, Double l, Double wq, Double w, Double ref){
		this.lq = getGood(lq);
		this.l = getGood(l);
		this.wq = getGood(wq);
		this.w = getGood(w);
		this.ref = getGood(ref);
	}
	
	private Double getGood(Double ref){
		if(ref == null)
			return null;
		if(Math.abs(ref) < 1e-10)
			return 0D;
		return ref;
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
