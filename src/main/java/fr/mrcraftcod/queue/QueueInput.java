package fr.mrcraftcod.queue;

public class QueueInput{
	
	final private ProbabilityLaw lawL;
	final private ProbabilityLaw lawM;
	
	final private int S;
	final private Double lambda;
	final private Double mu;
	final private Integer limit;
	
	public QueueInput(ProbabilityLaw lawL, ProbabilityLaw lawM, int s, Double lambda, Double mu, Integer limit){
		this.lawL = lawL;
		this.lawM = lawM;
		S = s;
		this.lambda = lambda;
		this.mu = mu;
		this.limit = limit;
	}
	
	public double getLambda(){
		return lambda;
	}
	
	public ProbabilityLaw getLawL(){
		return lawL;
	}
	
	public ProbabilityLaw getLawM(){
		return lawM;
	}
	
	public Integer getLimit(){
		return limit;
	}
	
	public double getMu(){
		return mu;
	}
	
	public int getS(){
		return S;
	}
}
