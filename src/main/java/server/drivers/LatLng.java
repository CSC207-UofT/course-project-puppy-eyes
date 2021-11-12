package server.drivers;

/**
 * A class that represents a latitude and longitude tuple.
 */
public class LatLng {

    private double lat, lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    /**
     * Return the distance in kilometers between this LatLng tuple and another, using the Haversine formula:
     * https://en.wikipedia.org/wiki/Haversine_formula/
     * @param other
     * @return distance in kilometers
     */
    public double calculateDistance(LatLng other) {
        final double earthRadius = 6371;
        double dLat = Math.toRadians(other.lat - this.lat);
        double dLng = Math.toRadians(other.lng - this.lat);

        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.pow(Math.sin(dLng / 2), 2)
                * Math.cos(Math.toRadians(this.lat))
                * Math.cos(Math.toRadians(other.lat));
        double c = 2 * Math.asin(Math.sqrt(a));

        return earthRadius * c;
    }

}
