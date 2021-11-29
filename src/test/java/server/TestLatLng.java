package server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.LatLng;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestLatLng {

    @Test
    public void TestCorrectDistanceTorontoNewYork() {
        LatLng toronto = new LatLng(43.6532, 79.3832);
        LatLng newYork = new LatLng(40.7128, 74.0060);

        double expected = 550.42;
        double actual = toronto.calculateDistance(newYork);

        double errorMargin = 0.5;
        assertTrue(Math.abs(actual - expected) <= errorMargin);
    }

    @Test
    public void TestCorrectDistanceBahenCentreKoreatownToronto() {
        LatLng bahenCentre = new LatLng(43.659575, -79.397373);
        LatLng koreatownToronto = new LatLng(43.663943, -79.416584);

        double expected = 1.62;
        double actual = bahenCentre.calculateDistance(koreatownToronto);

        double errorMargin = 0.1;
        assertTrue(Math.abs(actual - expected) <= errorMargin);
    }

}
