package fr.mla.machinelearning;

import java.io.IOException;

import fr.mla.machinelearning.algorithm.id3.ID3;

public class App {

	public static void main(String[] args) {

		if (args.length < 1) {
			usage();
			return;
		}
		String inputFilePath = args[0];

		ID3 id3Algo = new ID3();
		
		try {
			id3Algo.process(inputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void usage() {
		System.out.println("Please specify input file.");
	}

}
