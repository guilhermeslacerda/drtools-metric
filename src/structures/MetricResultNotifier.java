package structures;

import java.util.Set;

public interface MetricResultNotifier<T> {
	void add(T metric);
	void setTop(int number);
	Set<String> getNamesResult();
}
