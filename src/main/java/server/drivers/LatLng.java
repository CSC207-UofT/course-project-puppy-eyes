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
     *
     * @param other
     * @return distance in kilometers
     */
    public double calculateDistance(LatLng other) {
        final double earthRadius = 6371;
        double dLat = Math.toRadians(other.lat - this.lat);
        double dLon = Math.toRadians(other.lng - this.lng);
        double lat1 = Math.toRadians(this.lat);
        double lat2 = Math.toRadians(other.lat);

        double a = Math.sin(dLat / 2) *
                Math.sin(dLat / 2) +
                Math.sin(dLon / 2) *
                        Math.sin(dLon / 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return earthRadius * c;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LatLng))
            return false;

        LatLng other = (LatLng) o;

        return other.lat == this.lat && other.lng == this.lng;
    }

    @Override
    public String toString() {
        return "Lat: " + this.lat + ", Lng: " + this.lng;
    }

}
