package com.dataonfocus.clustering;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Util {
    
    public static double toParse(double d){
    	double resultado;
    	Locale locale  = new Locale("en", "UK");
        String pattern = "###.##";

        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        String format = decimalFormat.format(d);
        resultado = Double.parseDouble(format);
        return resultado;
    }
    
    
    
    
    public static void plotInicialMessage(int NUM_POINTS, int NUM_CLUSTERS){
    	
    	StringBuilder s = new StringBuilder();
    	
    	s.append("##################################\n");
    	s.append("#K-MEANS ALGORITHM \n");
    	s.append("##################################\n");
    	s.append("#UPC - Systems Engineering   		\n");
    	s.append("#Course: Artificial Intelligence	\n");
    	s.append("#2016-01 							\n");
    	s.append("#Code adapted from:  \n");
    	s.append("# http://www.dataonfocus.com/k-means-clustering-java-code/ \n");
    	s.append("##################################\n");
    	s.append("#Data entry\n");
    	s.append("#=================\n");
    	s.append("#Number of Clusters\t: " + NUM_CLUSTERS + "\n");
    	s.append("#Number of Points \t: " + NUM_POINTS + "\n");
    	s.append("#Points\t: points are generated \n\trandomly in first iteration.\n");
    	s.append("##################################\n");
    	System.out.println(s.toString()); 
    	
    }
}
