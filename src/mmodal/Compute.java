/***********************************
 *  Author: Daniel Flood
 *  Email: daniel@floodweb.us
 *  June 13th
 *  MModal Take Home Assignment
 ***********************************/

package mmodal;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Compute {
	private static final String USAGE = "Useage:\n\tjava Compute agrs\n\t\tEx: `java Compute 3 0 1 2 3 4` <= where the first digit is the window size integer and the following numbers are floats. \n\t\t\tShoud report back: [0, 0.5, 1, 2, 3]\n=================================\n";
	
	//Example tests
	public final static String test_args1[] = {"3", "0", "1", "2", "3", "4"};
	public final static String test_args[] = {"5","0","1","-2","3","-4","5","-6","7","-8","9"};
	
	public static ArrayList<Double> Results;

	public static void main(String[] args) {
		if(args.length == 0) {
			args = test_args;
			System.out.println("Warning: Using example args because no args were provided via command line. \n\n"+USAGE);
		}
		
		Results = new ArrayList<Double>();
		Integer window_size = Integer.parseInt(args[0]);
		ArrayList<Double> the_floats = new ArrayList<Double>(); 
		for(int i = 1; i<=args.length-1; i++) {
			try{
				the_floats.add(Double.parseDouble(args[i]));
			}catch(NumberFormatException e){
				System.out.println("There was an error parsing the floats passed in as arguments.\n"+USAGE);
			}
		}
		
		/******** Main call to compute method ******************/
		compute(window_size,the_floats);
		/*******************************************************/
	}

	private static void compute(Integer window_size, ArrayList<Double> the_floats) {
		//Initial cumulative averaging until window size is reached
		int i = 1;
		for(; i<=window_size; i++) {
			Results.add(cumulativeAverage(i, the_floats));
		}
		
		//After window size has been reached, keeping value of i for index
		while(i<=the_floats.size()) {
			Results.add(averageWithWindowSize(window_size, i, the_floats));
			i++;
		}
		
		DecimalFormat format = new DecimalFormat("0.###############");
		
		for(int k = 0; k<Results.size(); k++) {
			if(k==0)
				System.out.print("[");
			if(k!=Results.size()-1)
				System.out.print(format.format(Results.get(k))+",");
			else
				System.out.print(format.format(Results.get(k))+"]");
		}
	}
	
	public static Double cumulativeAverage(int n, ArrayList<Double> the_floats) {
		Double average = 0.0;
		for(int i = 0; i<n; i++) {
			average = (average / (i+1)) * (i) + the_floats.get(i) / (i+1); //saves storage but is slower performance because there are more operations
		}
		return average;
	}
	
	public static Double averageWithWindowSize(int window_size, int n, ArrayList<Double> the_floats) {
		Double average = 0.0;
		for(int j = n-window_size; j<n; j++) {
			average = average+the_floats.get(j);
		}
		return average/window_size;//less operations
	}

}
