package general.options;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

public class OptionsTest {

	private OptionDefinition prepareOption(String argument) {
		return new Options().selectBy(argument);
	}
	
	@Test
	public void testForAllMetricsOption() {
		 assertThat(prepareOption("-a"), IsInstanceOf.instanceOf(AllMetricsOption.class));
	}

	@Test
	public void testForAllCouplingOption() {
		 assertThat(prepareOption("-ac"), IsInstanceOf.instanceOf(AllCouplingOption.class));
	}

	@Test
	public void testForNamespaceCouplingOption() {
		 assertThat(prepareOption("-nc"), IsInstanceOf.instanceOf(NamespaceCouplingOption.class));
	}

	@Test
	public void testForCyclicDependencyOption() {
		 assertThat(prepareOption("-cd"), IsInstanceOf.instanceOf(CyclicDependencyOption.class));
	}

	@Test
	public void testForDependencyOption() {
		 assertThat(prepareOption("-d"), IsInstanceOf.instanceOf(DependencyOption.class));
	}

	@Test
	public void testForInternalDependencyOption() {
		 assertThat(prepareOption("-id"), IsInstanceOf.instanceOf(InternalDependencyOption.class));
	}

	@Test
	public void testForMethodOption() {
		 assertThat(prepareOption("-m"), IsInstanceOf.instanceOf(MethodOption.class));
	}

	@Test
	public void testForNamespaceOption() {
		 assertThat(prepareOption("-n"), IsInstanceOf.instanceOf(NamespaceOption.class));
	}

	@Test
	public void testForSummaryOption() {
		 assertThat(prepareOption("-s"), IsInstanceOf.instanceOf(SummaryOption.class));
	}

	@Test
	public void testForThresholdsOption() {
		 assertThat(prepareOption("-mt"), IsInstanceOf.instanceOf(ThresholdsOption.class));
	}

	@Test
	public void testTypeOption() {
		 assertThat(prepareOption("-t"), IsInstanceOf.instanceOf(TypeOption.class));
	}

	@Test
	public void testTypeCouplingOption() {
		 assertThat(prepareOption("-tc"), IsInstanceOf.instanceOf(TypeCouplingOption.class));
	}
	
	@Test
	public void testMetricVisualizationOption() {
		 assertThat(prepareOption("-mv"), IsInstanceOf.instanceOf(MetricVisualizationOption.class));
	}
}
