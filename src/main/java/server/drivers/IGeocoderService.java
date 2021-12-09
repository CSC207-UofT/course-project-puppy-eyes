package server.drivers;

import java.io.IOException;
import java.util.List;

public interface IGeocoderService {

    /**
     * Given a query, make a GET request to the HERE Geocoding API to fetch latitude and longitude.
     *
     * @param query the given query
     * @return Return a List of lat-lng tuples that match the query.
     * @throws IOException          IO Exception
     * @throws InterruptedException Interrupted Exception
     */
    List<LatLng> getLatLng(String query);

}
