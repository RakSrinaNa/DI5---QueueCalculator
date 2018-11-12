package fr.mrcraftcod.queue;

import java.util.Objects;

class Computation{
	
	static QueueResult compute(QueueInput input) throws BadInputException{
		if(Objects.isNull(input.getS()) || input.getS() < 1){
			throw new BadInputException("Number of waiters must be at least 1", InputField.S);
		}
		
		if(Objects.isNull(input.getLambda()) || input.getLambda() <= 0){
			throw new BadInputException("Lambda must be greater than 0", InputField.LAMBDA);
		}
		if(Objects.isNull(input.getMu()) || input.getMu() <= 0){
			throw new BadInputException("Mu must be greater than 0", InputField.MU);
		}
		
		double rho = input.getLambda() / (input.getS() * input.getMu());
		
		if(Objects.nonNull(input.getLimit()) && input.getLimit() < 1){
			throw new BadInputException("Queue limit must be left blank or be at least 1", InputField.QUEUE_LIMIT);
		}
		
		if(input.getLimit() != null){
			if(input.getS() == 1){
				//M|M|1|K
				double q0;
				if(rho == 1){
					q0 = 1.0 / (input.getLimit() + 1);
				}
				else{
					q0 = ((1.0 - rho)) / (1.0 - Math.pow(rho, input.getLimit() + 1));
				}
				
				double l;
				if(rho == 1){
					l = input.getLimit() / 2.0;
				}
				else{
					l = rho * (1.0 - ((input.getLimit() + 1) * Math.pow(rho, input.getLimit())) + (input.getLimit() * Math.pow(rho, input.getLimit() + 1))) / ((1.0 - rho) * (1.0 - Math.pow(rho, input.getLimit() + 1)));
				}
				
				double lq = l - (1 - q0);
				
				double w = 1.0 / (input.getMu() - input.getLambda());
				
				double wq = (input.getLambda() / input.getMu()) * w;
				
				double ref = q0 * (Math.pow(input.getS(), input.getS()) * Math.pow(rho, input.getS())) / (fact(input.getS()));
				
				return new QueueResult(lq, l, wq, w, ref);
			}
			else{
				throw new BadInputException("Problem not studied", InputField.QUEUE_LIMIT, InputField.S);
			}
		}
		else{
			//M|M|S
			if(rho >= 1){
				throw new BadInputException("Rho is greater or equal to 1, impossible problem", InputField.MU, InputField.LAMBDA);
			}
			
			//Q0
			double sumQ0 = 0;
			for(int i = 0; i < input.getS(); i++){
				sumQ0 += Math.pow(rho * input.getS(), i) / fact(i);
			}
			sumQ0 += Math.pow(rho * input.getS(), input.getS()) / (fact(input.getS()) * (1 - rho));
			
			double q0 = 1.0 / (sumQ0);
			
			double lq = q0 * ((Math.pow(rho * input.getS(), input.getS()) * rho) / (fact(input.getS()) * Math.pow(1 - rho, 2)));
			double wq = lq / input.getLambda();
			double w = wq + 1 / input.getMu();
			double l = input.getLambda() * w;
			
			return new QueueResult(lq, l, wq, w, null);
		}
	}
	
	private static int fact(int f){
		if(f <= 1){
			return 1;
		}
		return f * fact(f - 1);
	}
}
