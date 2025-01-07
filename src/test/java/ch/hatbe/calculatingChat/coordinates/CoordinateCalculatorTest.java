package ch.hatbe.calculatingChat.coordinates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateCalculatorTest {
    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0",
            "8, 8, 1, 1",
            "-8, -8, -1, -1",
            "16, 0, 2, 0",
            "0, 16, 0, 2",
            "-16, -16, -2, -2",
            "32, 24, 4, 3",
            "-24, -32, -3, -4",
            "64, 48, 8, 6",
            "80, -40, 10, -5",
            "-96, 64, -12, 8",
            "128, -96, 16, -12",
            "-160, 112, -20, 14",
            "192, -144, 24, -18",
            "256, 200, 32, 25"
    })
    void calculateNetherCoordinates(int overworldX, int overworldZ, int expectedNetherX, int expectedNetherZ) {
        Coordinates2D coordinates2D = CoordinateCalculator.calculateNetherCoordinates(overworldX, overworldZ);

        assertEquals(expectedNetherX, coordinates2D.x(), "Nether X coordinate is incorrect");
        assertEquals(expectedNetherZ, coordinates2D.z(), "Nether Z coordinate is incorrect");
    }

    @ParameterizedTest
    @CsvSource({
            "2147483647, 0, 268435455, 0",                      // Integer.MAX_VALUE
            "0, 2147483647, 0, 268435455",                      // Integer.MAX_VALUE
            "-2147483648, 0, -268435456, 0",                    // Integer.MIN_VALUE
            "0, -2147483648, 0, -268435456",                    // Integer.MIN_VALUE
            "2147483647, 2147483647, 268435455, 268435455",     // Both MAX_VALUE
            "-2147483648, -2147483648, -268435456, -268435456"  // Both MIN_VALUE
    })
    void calculateNetherCoordinatesBoundaryCases(int overworldX, int overworldZ, int expectedNetherX, int expectedNetherZ) {
        Coordinates2D coordinates2D = CoordinateCalculator.calculateNetherCoordinates(overworldX, overworldZ);

        assertEquals(expectedNetherX, coordinates2D.x(), "Boundary Nether X coordinate is incorrect");
        assertEquals(expectedNetherZ, coordinates2D.z(), "Boundary Nether Z coordinate is incorrect");
    }

    @Test
    void calculateNetherCoordinatesHandlesLargeNegativeValues() {
        Coordinates2D coordinates2D = CoordinateCalculator.calculateNetherCoordinates(-1_000_000, -1_000_000);

        assertEquals(-125_000, coordinates2D.x(), "Nether X coordinate is incorrect for large negative values");
        assertEquals(-125_000, coordinates2D.z(), "Nether Z coordinate is incorrect for large negative values");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0",
            "1, 1, 8, 8",
            "-1, -1, -8, -8",
            "2, 0, 16, 0",
            "0, 2, 0, 16",
            "-2, -2, -16, -16",
            "4, 3, 32, 24",
            "-3, -4, -24, -32",
            "8, 6, 64, 48",
            "10, -5, 80, -40",
            "-12, 8, -96, 64",
            "16, -12, 128, -96",
            "-20, 14, -160, 112",
            "24, -18, 192, -144",
            "32, 25, 256, 200"
    })
    void calculateOverworldCoordinates(int netherX, int netherZ, int expectedOverworldX, int expectedOverworldZ) {
        Coordinates2D coordinates2D = CoordinateCalculator.calculateOverworldCoordinates(netherX, netherZ);

        assertEquals(expectedOverworldX, coordinates2D.x(), "Overworld X coordinate is incorrect");
        assertEquals(expectedOverworldZ, coordinates2D.z(), "Overworld Z coordinate is incorrect");
    }

    @ParameterizedTest
    @CsvSource({
            "268435455, 0, 2147483640, 0",                      // Integer.MAX_VALUE
            "0, 268435455, 0, 2147483640",                         // Integer.MAX_VALUE
            "-268435456, 0, -2147483648, 0",                    // Integer.MIN_VALUE
            "0, -268435456, 0, -2147483648",                    // Integer.MIN_VALUE
            "268435455, 268435455, 2147483640, 2147483640",     // Both MAX_VALUE
            "-268435456, -268435456, -2147483648, -2147483648"  // Both MIN_VALUE
    })
    void calculateOverworldCoordinatesBoundaryCases(int netherX, int netherZ, int expectedOverworldX, int expectedOverworldZ) {
        Coordinates2D coordinates2D = CoordinateCalculator.calculateOverworldCoordinates(netherX, netherZ);

        assertEquals(expectedOverworldX, coordinates2D.x(), "Boundary Overworld X coordinate is incorrect");
        assertEquals(expectedOverworldZ, coordinates2D.z(), "Boundary Overworld Z coordinate is incorrect");
    }

    @Test
    void calculateOverworldCoordinatesHandlesLargeNegativeValues() {
        Coordinates2D coordinates2D = CoordinateCalculator.calculateOverworldCoordinates(-125_000, -125_000);

        assertEquals(-1_000_000, coordinates2D.x(), "Overworld X coordinate is incorrect for large negative values");
        assertEquals(-1_000_000, coordinates2D.z(), "Overworld Z coordinate is incorrect for large negative values");
    }
}