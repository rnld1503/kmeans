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
    	s.append("#Algoritmo K-Mean	\n");
    	s.append("#=================================\n");
    	s.append("#UPC-Ingenieria de Sistemas 		\n");
    	s.append("#Curso: Inteligencia Artificial	\n");
    	s.append("#2016-01 							\n");
    	s.append("#									\n");
    	s.append("##################################\n");
    	s.append("#Datos del entrada\n");
    	s.append("#=================\n");
    	s.append("#Numero de Puntos \t: " + NUM_POINTS + "\n");
    	s.append("#Numero de Clusters\t: " + NUM_CLUSTERS + "\n");
    	s.append("#									\n");
    	s.append("##################################\n");
    	System.out.println(s.toString()); 
    	
    }
}
