package structures.statistics;

import java.util.List;
import java.util.stream.Collectors;

import structures.metrics.TypeMetric;
import structures.results.MethodMetricResult;
import structures.results.TypeMetricResult;

public class StatisticalOfType extends StatisticalOperations {
	private List<TypeMetric> typeMetrics;
	private double[] sloc, nom, npm, wmc, dep, idep, fanIn, fanOut, noa;
	private TypeMetricResult tmr;
	
	public StatisticalOfType() {
		super();
	}
	
	public void defineResults(TypeMetricResult tmr) {
		this.tmr = tmr;
		tmr.setMethodMetricResult(new MethodMetricResult());
		typeMetrics = tmr.getTypeMetrics().values().stream().collect(Collectors.toList());
	}
	
	@Override
	public void compute() {
		createArrays();
		int indx = 0;
		for (TypeMetric typeMetric : typeMetrics) { 
			  sloc[indx] = typeMetric.getSloc();
			   nom[indx] = typeMetric.getNumOfMethods();
			   npm[indx] = typeMetric.getNumOfPublicMethods();
			   wmc[indx] = tmr.getTotalCycloBy(typeMetric.getFullName());
			   dep[indx] = typeMetric.getNumberOfDependencies();
			  idep[indx] = typeMetric.getNumberOfInternalDependencies();
			 fanIn[indx] = tmr.getFanInOf(typeMetric.getName());
			fanOut[indx] = typeMetric.getFanOut();
			   noa[indx] = typeMetric.getNumOfVariables();
			 indx++;
		}
	}

	private void createArrays() {
		  sloc = new double[typeMetrics.size()];
		   nom = new double[typeMetrics.size()];
	  	   npm = new double[typeMetrics.size()];
		   wmc = new double[typeMetrics.size()];
		   dep = new double[typeMetrics.size()];
		  idep = new double[typeMetrics.size()];
		 fanIn = new double[typeMetrics.size()];
		fanOut = new double[typeMetrics.size()];
		   noa = new double[typeMetrics.size()];
	}
	
	public void useSLOC() {
		sa.setElements(sloc);
	}
	
	public void useNOM() {
		sa.setElements(nom);
	}

	public void useNPM() {
		sa.setElements(npm);
	}

	public void useWMC() {
		sa.setElements(wmc);
	}

	public void useDEP() {
		sa.setElements(dep);
	}

	public void useIDEP() {
		sa.setElements(idep);
	}

	public void useFanIn() {
		sa.setElements(fanIn);
	}

	public void useFanOut() {
		sa.setElements(fanOut);
	}

	public void useNOA() {
		sa.setElements(noa);
	}
}
