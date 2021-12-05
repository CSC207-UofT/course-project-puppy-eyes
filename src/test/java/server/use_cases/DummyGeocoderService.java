package server.use_cases;

import server.drivers.IGeocoderService;
import server.drivers.LatLng;

import java.util.Arrays;
import java.util.List;

public class DummyGeocoderService implements IGeocoderService {

    /**
     * A dummy geocoder service that returns the lat-lng of Toronto regardless of query.
     * @param query
     * @return a List<LatLng> of one element
     */
    @Override
    public List<LatLng> getLatLng(String query) {
        return Arrays.asList(new LatLng(43.6532, 79.3832));
    }
}
