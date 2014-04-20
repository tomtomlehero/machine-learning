package fr.mla.machinelearning.algorithm.id3;

import java.io.IOException;
import java.util.Map;

import fr.mla.machinelearning.DataSet;

public class ID3 {

	public void process(String filePath) throws IOException {

		DataSet dataSet = new DataSet(filePath);
		System.out.println(dataSet);
		
		
		double h = entropy(dataSet);
		
		System.out.println("Entropy = " + h);
		
		
		for (int feature = 0; feature < dataSet.getFeatures().length; feature++) {
			double gain = gain(dataSet, feature, h);
		}
		

	}

	private double entropy(DataSet dataSet) {

		double h = 0.0d;
		
		for (String className : dataSet.getClasses()) {
			double p = dataSet.getClassRate(className);
			h -= p * Math.log(p) / Math.log(2.0d);
		}
		
		return h;
	}

	
	private double gain(DataSet dataSet, int feature, double initialEntropy) {

		System.out.println("Spliting feature " + dataSet.getFeatures()[feature]);
		
		Map<String, Map<String, Integer>> classDictionnaryWithFeatureSplit = dataSet.getClassDictionnaryWithFeatureSplit(feature);
		System.out.println(classDictionnaryWithFeatureSplit);
		
		double gain = initialEntropy;
		
		for (String property : classDictionnaryWithFeatureSplit.keySet()) {


			int propertyTotal = 0;
			
			for (String className : classDictionnaryWithFeatureSplit.get(property).keySet()) {
				propertyTotal += classDictionnaryWithFeatureSplit.get(property).get(className);
			}

			
			double hProperty = entropy(classDictionnaryWithFeatureSplit, property, propertyTotal);
			System.out.println("Entropy / " + dataSet.getFeatures()[feature] + " / " + property + " : " + hProperty);
			gain -= hProperty * propertyTotal / dataSet.getData().size();
		}
		
		System.out.println("GAIN = " + gain);
		
		return gain;
		
		
	}

	private double entropy(Map<String, Map<String, Integer>> classDictionnaryWithFeatureSplit, String property, int propertyTotal) {

		Map<String, Integer> classSubDictionnary = classDictionnaryWithFeatureSplit.get(property);

		double h = 0.0d;
		for (String className : classSubDictionnary.keySet()) {
			int n = classSubDictionnary.get(className);
			if (n > 0) {
				double p = (double) n / propertyTotal;
				h -= p * Math.log(p) / Math.log(2.0d);
			}
		}
		
		return h;
	}

	
}
