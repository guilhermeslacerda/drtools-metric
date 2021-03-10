package output;

import java.util.Set;

import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;

public class MetricResultDOT {
	private NamespaceMetricResult nmr;
	private TypeMetricResult tmr;
	private StringBuilder file;

	public void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr) {
		this.nmr = nmr;
		this.tmr = tmr;
	}

	public String generateArchitecturalDependencies() {
		open();
			configure();
			defineNamespaces();
			defineLinks();
		close();
		return file.toString();
	}

	private void open() {
		file = new StringBuilder();
		file.append("digraph {\n\n");
	}

	private void close() {
		file.append("}\n");
	}

	private void configure() {
		file.append("node [\n");
		file.append("style=\"filled\",\n fontcolor = black,\n color = white, fontname = Poppins];\n");
		file.append("graph [rankdir=TB],\n\n");
	}

	private void defineNamespaces() {
		for (String name : nmr.getNamesResult()) 
		    file.append("\""+ name + "\" [shape=\"ellipse\" label=\"" + name + "\" fillcolor=\"lightgrey\"],\n");
	}

	private void defineLinks() {
		for (String name : nmr.getNamesResult()) {
			if (name == null) 	continue;
			Set<String> links = tmr.getInternalImportsBy(name);
			for (String link : links)
				file.append("\""+ name + "\" -> \"" + link + "\",\n");
		}
	}
}
