package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import java.util.HashMap;

public class GeoLocations {
    private static class Geo{
        double lat;
        double lon;

        public Geo(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }
    }

    static HashMap<String, Geo> geoMap = new HashMap<>();

    static {
        geoMap.put("Boston", new Geo(42.361145, -71.057083));
        geoMap.put("San Francisco", new Geo(37.773972,  -122.431297));
        geoMap.put("Los Angeles", new Geo(34.052235, -118.243683));
        geoMap.put("New York", new Geo(40.730610, -73.935242));
        geoMap.put("Seattle", new Geo(47.608013, -122.335167));
    }

    public static double getLatitude(String city) {
        return geoMap.get(city).getLat();
    }

    public static double getLongitude(String city) {
        return geoMap.get(city).getLon();
    }
}
