package structures;

@SuppressWarnings("rawtypes")
public interface MetricActivator <T extends MetricResultNotifier>{
	void update(T result);
}
