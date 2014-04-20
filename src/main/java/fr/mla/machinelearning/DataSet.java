package fr.mla.machinelearning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataSet {

	private String[] features;
	private List<String[]> data;

	private Map<String, Integer> classDictionnary;

	public DataSet(String filePath) throws IOException {

		try (BufferedReader inputFile = new BufferedReader(new FileReader(filePath))) {

			// Read 1st line (header) -> features
			String line = inputFile.readLine();

			String[] tmp = line.split("\t");
			// Discard last position since the last collumn belongs to 'class'
			this.features = Arrays.copyOfRange(tmp, 0, tmp.length - 1);

			// Read remaining lines
			this.data = new ArrayList<String[]>();

			while ((line = inputFile.readLine()) != null) {
				this.data.add(line.split("\t"));
			}

		}

		this.classDictionnary = getClassDictionnary();

	}

	private Map<String, Integer> getClassDictionnary() {

		Map<String, Integer> classDictionnary = new HashMap<String, Integer>();

		for (String[] row : data) {

			// Get the class name which is the last element of the row
			String className = row[row.length - 1];

			// eventually, add the class name to the dictionnary
			if (!classDictionnary.containsKey(className)) {
				classDictionnary.put(className, 0);
			}

			// add 1 to the count
			Integer count = classDictionnary.get(className);
			classDictionnary.put(className, count + 1);
		}

		return classDictionnary;
	}

	
	
	
	public Set<String> getClasses() {
		return this.classDictionnary.keySet();
	}
	
	public double getClassRate(String className) {
		return ((double) classDictionnary.get(className)) / data.size();
	}
	
	public String[] getFeatures() {
		return features;
	}


	
	public List<String[]> getData() {
		return data;
	}

	public Map<String, Map<String, Integer>> getClassDictionnaryWithFeatureSplit(int feature) {
		
		Map<String, Map<String, Integer>> result = new HashMap<String, Map<String,Integer>>();
		
		for (String[] row : this.data) {
			
			String theProperty = row[feature];
			String theClass = row[row.length - 1];
			
			// eventually, add the property to the Map
			if (!result.containsKey(theProperty)) {
				Map<String, Integer> classSubDictionnary = new HashMap<String, Integer>();
				for (String className : classDictionnary.keySet()) {
					classSubDictionnary.put(className, 0);
				}
				result.put(theProperty, classSubDictionnary);
			}
			
			// add 1 to the count
			Integer count = result.get(theProperty).get(theClass);
			result.get(theProperty).put(theClass, count + 1);
		}
		
		return result;
	}
	
	@Override
	public String toString() {

		String result = "DATA\n{";

		for (String[] row : data) {
			result += "{";
			for (int j = 0; j < row.length; j++) {
				result += row[j] + ",";
			}
			if (row.length > 0) {
				result = result.substring(0, result.length() - 1);
			}
			result += "},";
		}
		if (data.size() > 0) {
			result = result.substring(0, result.length() - 1);
		}

		result += "}\nFEATURES\n";

		result += "{";
		for (String feature : features) {
			result += feature + ",";
		}
		if (features.length > 0) {
			result = result.substring(0, result.length() - 1);
		}

		result += "}\nCLASSES\n";

		result += classDictionnary;

		return result;

	}

}
