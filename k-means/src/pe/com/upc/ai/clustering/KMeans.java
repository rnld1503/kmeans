/* 
 * KMeans.java ; Cluster.java ; Point.java
 *
 * Solution implemented by DataOnFocus
 * www.dataonfocus.com
 * 2015
 *
*/
package pe.com.upc.ai.clustering;

import static pe.com.upc.ai.clustering.Util.plotPoins;
import static pe.com.upc.ai.clustering.Util.toParse;

import java.util.ArrayList;
import java.util.List;

import pe.com.upc.ai.clustering.Point;
import pe.com.upc.ai.clustering.Util;

public class KMeans {

	//Number of Clusters. This metric should be related to the number of points
    private static int NUM_CLUSTERS = 3;    
    //Number of Points
    private static int NUM_POINTS = 10;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 20;
    
    private List<Point> points;
    private List<Cluster> clusters;
    
    public KMeans() {
    	this.points = new ArrayList();
    	this.clusters = new ArrayList();    	
    }
    
    public static void main(String[] args) {
    	Util.plotInicialMessage(NUM_POINTS, NUM_CLUSTERS, MIN_COORDINATE, MAX_COORDINATE );
    	KMeans kmeans = new KMeans();
    	kmeans.init();
    	kmeans.calculate();
    }
    
    //Initializes the process
    public void init() {
    	//Create Points
    	points = Point.createRandomPoints(MIN_COORDINATE,MAX_COORDINATE,NUM_POINTS);
    	plotPoins(points);
    	
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster cluster = new Cluster(i);
    		Point centroid = Point.createRandomPoint(MIN_COORDINATE,MAX_COORDINATE);
    		cluster.setCentroid(centroid);
    		clusters.add(cluster);
    	}
    	
    	//Print Initial state
    	System.out.println("Init centroids randomly:");
    	System.out.println("========================");
    	plotClusters();
    }

	private void plotClusters() {
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster c = clusters.get(i);
    		c.plotCluster();
    	}
    }
    
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
        	//Clear cluster state
        	clearClusters();
        	
        	List<Point> lastCentroids = getCentroids();
        	
        	//Assign points to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	
        	iteration++;
        	
        	List<Point> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		distance += toParse(Point.distance(lastCentroids.get(i),currentCentroids.get(i)));
        	}

        	System.out.println("=============");
        	System.out.println("ITERATION: " + iteration);
        	System.out.println("=============");
        	System.out.println("Centroid distances: " + distance + "\n");
        	plotClusters();
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
        }
        
        System.out.println("############################################################");
    	System.out.println("# Total Iterations: " + iteration);
    	System.out.println("# Review iteration " + iteration + " to see elements in each cluster.");
    	System.out.println("############################################################\n");
    }
    
    private void clearClusters() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }
    
    private List getCentroids() {
    	List centroids = new ArrayList(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Point aux = cluster.getCentroid();
    		Point point = new Point(aux.getX(),aux.getY());
    		centroids.add(point);
    	}
    	return centroids;
    }
    
    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 
        
        for(Point point : points) {
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }
    
    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int n_points = list.size();
            
            for(Point point : list) {
            	sumX += point.getX();
                sumY += point.getY();
            }
            
            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
            	double newX = sumX / n_points;
            	double newY = sumY / n_points;
                centroid.setX(toParse(newX));
                centroid.setY(toParse(newY));
            }
        }
    }
}