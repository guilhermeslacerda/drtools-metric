package structures.statistics;

import java.util.List;
import java.util.stream.Collectors;

import structures.metrics.MethodMetric;
import structures.results.MethodMetricResult;
import structures.results.StatisticMetricResult;

public class StatisticOfMethod extends StatisticalOperations {
	private List<MethodMetric> methodMetrics;
	private double[] mloc, cyclo, calls, nbd, param;
	
	public StatisticOfMethod() {
		super();
	}
	
	public void defineResults(MethodMetricResult mmr) {
		methodMetrics = mmr.getMethodMetrics().values().stream().collect(Collectors.toList());
		compute();
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
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("MLOC").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("MLOC").getMax()));
	}
	
	public void useCYCLO() {
		sa.setElements(cyclo);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("CYCLO").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("CYCLO").getMax()));
	}

	public void useCALLS() {
		sa.setElements(calls);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("CALLS").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("CALLS").getMax()));
	}

	public void useNBD() {
		sa.setElements(nbd);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("NBD").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("NBD").getMax()));
	}

	public void usePARAM() {
		sa.setElements(param);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("PARAM").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("PARAM").getMax()));
	}

	@Override
	protected void setInfo() {
		useMLOC();
		useCYCLO();
		useCALLS();
		useNBD();
		usePARAM();
	}
}