package structures.statistics;

import java.util.List;
import java.util.stream.Collectors;

import structures.metrics.TypeMetric;
import structures.results.MethodMetricResult;
import structures.results.StatisticMetricResult;
import structures.results.TypeMetricResult;

public class StatisticOfType extends StatisticalOperations {
	private List<TypeMetric> typeMetrics;
	private double[] sloc, nom, npm, wmc, dep, idep, fanIn, fanOut, noa, lcom;
	private TypeMetricResult tmr;
	
	public StatisticOfType() {
		super();
	}
	
	public void defineResults(TypeMetricResult tmr, MethodMetricResult mmr) {
		this.tmr = tmr;
		tmr.setMethodMetricResult(mmr);
		typeMetrics = tmr.getTypeMetrics().values().stream().collect(Collectors.toList());
		compute();
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
			 fanIn[indx] = tmr.getFanInOf(typeMetric.getFullName());
			fanOut[indx] = typeMetric.getFanOut();
			   noa[indx] = typeMetric.getNumOfVariables();
			  lcom[indx] = tmr.getLackCohesionMethods(typeMetric.getFullName());
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
		  lcom = new double[typeMetrics.size()];
	}
	
	public void useSLOC() {
		sa.setElements(sloc);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("SLOC").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("SLOC").getMax()));
	}
	
	public void useNOM() {
		sa.setElements(nom);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("NOM").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("NOM").getMax()));
	}

	public void useNPM() {
		sa.setElements(npm);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("NPM").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("NPM").getMax()));
	}

	public void useWMC() {
		sa.setElements(wmc);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("WMC").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("WMC").getMax()));
	}

	public void useDEP() {
		sa.setElements(dep);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("DEP").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("DEP").getMax()));
	}

	public void useIDEP() {
		sa.setElements(idep);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("I-DEP").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("I-DEP").getMax()));
	}

	public void useFanIn() {
		sa.setElements(fanIn);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("FAN-IN").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("FAN-IN").getMax()));
	}

	public void useFanOut() {
		sa.setElements(fanOut);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("FAN-OUT").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("FAN-OUT").getMax()));
	}

	public void useNOA() {
		sa.setElements(noa);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("NOA").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("NOA").getMax()));
	}

	public void useLCOM() {
		sa.setElements(lcom);
		statisticList.add(new StatisticMetricResult(mt.getMetricDefinition("LCOM3").getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition("LCOM3").getMax()));
	}

	@Override
	protected void setInfo() {
		useSLOC();
		useNOM();
		useNPM();
		useWMC();
		useDEP();
		useIDEP();
		useFanIn();
		useFanOut();
		useNOA();
		useLCOM();
	}
}
