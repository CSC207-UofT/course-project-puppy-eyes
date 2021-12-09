package server.use_cases;

import server.drivers.IGeocoderService;
import server.drivers.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyGeocoderService implements IGeocoderService {

    /**
     * A dummy geocoder service that returns the lat-lng of some select cities.
     * @param query
     * @return a List<LatLng>
     */
    @Override
    public List<LatLng> getLatLng(String query) {
        if (query.toLowerCase().contains("toronto")) {
            return Arrays.asList(new LatLng(43.6532, 79.3832));
        } else if (query.toLowerCase().contains("new york")) {
            return Arrays.asList(new LatLng(40.7128, 74.0060));
        }

        return new ArrayList<>();
    }
}
