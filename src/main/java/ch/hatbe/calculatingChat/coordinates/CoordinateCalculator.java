package ch.hatbe.calculatingChat.coordinates;

public class CoordinateCalculator {
    public static Coordinates2D calculateOverworldCoordinates(int x, int z) {
        int overworldX = x * 8;
        int overworldZ = z * 8;

        return new Coordinates2D(overworldX, overworldZ);
    }

    public static Coordinates2D calculateNetherCoordinates(int x, int z) {
        int netherX = x / 8;
        int netherZ = z / 8;

        return new Coordinates2D(netherX, netherZ);
    }
}
