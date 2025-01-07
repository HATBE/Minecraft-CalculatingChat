package ch.hatbe.calculatingChat.commands;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NetherCoordinatesCommandTest {

    private NetherCoordinatesCommand netherCoordinatesCommand;

    @BeforeEach
    void setUp() {
        this.netherCoordinatesCommand = new NetherCoordinatesCommand();
    }

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
        NetherCoordinates netherCoordinates = this.netherCoordinatesCommand.calculateNetherCoordinates(overworldX, overworldZ);

        assertEquals(expectedNetherX, netherCoordinates.x(), "Nether X coordinate is incorrect");
        assertEquals(expectedNetherZ, netherCoordinates.z(), "Nether Z coordinate is incorrect");
    }

    @ParameterizedTest
    @CsvSource({
            "2147483647, 0, 268435455, 0",     // Integer.MAX_VALUE
            "0, 2147483647, 0, 268435455",     // Integer.MAX_VALUE
            "-2147483648, 0, -268435456, 0",   // Integer.MIN_VALUE
            "0, -2147483648, 0, -268435456",   // Integer.MIN_VALUE
            "2147483647, 2147483647, 268435455, 268435455", // Both MAX_VALUE
            "-2147483648, -2147483648, -268435456, -268435456" // Both MIN_VALUE
    })
    void calculateNetherCoordinatesBoundaryCases(int overworldX, int overworldZ, int expectedNetherX, int expectedNetherZ) {
        NetherCoordinates netherCoordinates = this.netherCoordinatesCommand.calculateNetherCoordinates(overworldX, overworldZ);

        assertEquals(expectedNetherX, netherCoordinates.x(), "Boundary Nether X coordinate is incorrect");
        assertEquals(expectedNetherZ, netherCoordinates.z(), "Boundary Nether Z coordinate is incorrect");
    }

    @Test
    void calculateNetherCoordinatesHandlesLargeNegativeValues() {
        NetherCoordinates netherCoordinates = this.netherCoordinatesCommand.calculateNetherCoordinates(-1_000_000, -1_000_000);

        assertEquals(-125_000, netherCoordinates.x(), "Nether X coordinate is incorrect for large negative values");
        assertEquals(-125_000, netherCoordinates.z(), "Nether Z coordinate is incorrect for large negative values");
    }

    @Test
    void invalidInputThrowsException() {
        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("invalid");
        });
    }
}