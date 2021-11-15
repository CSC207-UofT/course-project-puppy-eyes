package server.drivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeocoderService implements IGeocoderService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private Dotenv dotenv;

    public GeocoderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.mapper = new ObjectMapper();
        dotenv = Dotenv.load();
    }

    private final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";
    private final String API_KEY = dotenv.get("GEOCODER_API_KEY");

    /**
     * Given a query, make a GET request to the HERE Geocoding API to fetch latitude and longitude.
     * @param query
     * @return Return a List of lat-lng tuples that match the query.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<LatLng> getLatLng(String query) {
        try {
            String requestUrl = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + query;

            // Make a GET request to the geocode API and store the response
            ResponseEntity<String> response = this.restTemplate.getForEntity(requestUrl, String.class);

            List<LatLng> results = new ArrayList<>();

            if (response.getStatusCode() == HttpStatus.OK) {
                // Convert the raw String response into a JSON node
                JsonNode jsonNodeItems = mapper.readTree(response.getBody()).get("items");

                for (JsonNode item : jsonNodeItems) {
                    JsonNode position = item.get("position");
                    Double lat = position.get("lat").asDouble();
                    Double lng = position.get("lng").asDouble();

                    results.add(new LatLng(lat, lng));
                }
            }

            return results;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}