package structures.statistics;

import java.util.List;
import java.util.stream.Collectors;

import structures.metrics.MethodMetric;
import structures.results.MethodMetricResult;

public class StatisticalOfMethod extends StatisticalOperations {
	private List<MethodMetric> methodMetrics;
	private double[] mloc, cyclo, calls, nbd, param;
	
	public StatisticalOfMethod() {
		super();
	}
	
	public void defineResults(MethodMetricResult mmr) {
		methodMetrics = mmr.getMethodMetrics().values().stream().collect(Collectors.toList());
	}
	
	@Override
	public void compute() {
		createArrays();
		int indx = 0;
		for (MethodMetric methodMetric : methodMetrics) { 
			   mloc[indx] = methodMetric.getLoc();
			  cyclo[indx] = methodMetric.getCyclo();
			  calls[indx] = methodMetric.getCalls();
			    nbd[indx] = methodMetric.getNestedBlockDepth();
			  param[indx] = methodMetric.getNumOfParameters();
			 indx++;
		}
	}

	private void createArrays() {
		    mloc = new double[methodMetrics.size()];
		   cyclo = new double[methodMetrics.size()];
	  	   calls = new double[methodMetrics.size()];
		     nbd = new double[methodMetrics.size()];
		   param = new double[methodMetrics.size()];
	}
	
	public void useMLOC() {
		sa.setElements(mloc);
	}
	
	public void useCYCLO() {
		sa.setElements(cyclo);
	}

	public void useCALLS() {
		sa.setElements(calls);
	}

	public void useNBD() {
		sa.setElements(nbd);
	}

	public void usePARAM() {
		sa.setElements(param);
	}

	@Override
	protected void setInfo() {
		// TODO Auto-generated method stub
		
	}
}