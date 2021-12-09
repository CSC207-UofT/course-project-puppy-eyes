package server.drivers;

import java.io.IOException;
import java.util.List;

public interface IGeocoderService {

    /**
     * Given a query, make a GET request to the HERE Geocoding API to fetch latitude and longitude.
     *
     * @param query
     * @return Return a List of lat-lng tuples that match the query.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<LatLng> getLatLng(String query);

}
