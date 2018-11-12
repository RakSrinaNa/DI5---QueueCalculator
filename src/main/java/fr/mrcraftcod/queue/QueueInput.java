package fr.mrcraftcod.queue;

public class QueueInput{
	
	final private ProbabilityLaw lawL;
	final private ProbabilityLaw lawM;
	
	final private Integer S;
	final private Double lambda;
	final private Double mu;
	final private Integer limit;
	
	public QueueInput(ProbabilityLaw lawL, ProbabilityLaw lawM, Integer s, Double lambda, Double mu, Integer limit){
		this.lawL = lawL;
		this.lawM = lawM;
		this.S = s;
		this.lambda = lambda;
		this.mu = mu;
		this.limit = limit;
	}
	
	public Double getLambda(){
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
	
	public Double getMu(){
		return mu;
	}
	
	public Integer getS(){
		return S;
	}
}
