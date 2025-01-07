package ch.hatbe.calculatingChat.commands.coordinateCommands;

import ch.hatbe.calculatingChat.coordinates.CoordinateCalculator;
import ch.hatbe.calculatingChat.coordinates.Coordinates2D;

public class OverworldCoordinatesCommand extends AbstractCoordinatesCommand {
    @Override
    protected Coordinates2D calculateCoordinates(int x, int z) {
        return CoordinateCalculator.calculateOverworldCoordinates(x, z);
    }

    @Override
    protected String getDimensionName() {
        return "Overworld";
    }
}