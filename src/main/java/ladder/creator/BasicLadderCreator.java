package ladder.creator;

import ladder.NaturalNumber;
import ladder.Position;
import ladder.Row;

public class BasicLadderCreator implements LadderCreatorInterface{
    private final Row[] rows;

    public BasicLadderCreator(NaturalNumber numberOfRow, NaturalNumber numberOfPerson){
        rows = new Row[numberOfRow.getNumber()];
        for (int i = 0; i < numberOfRow.getNumber(); i++) {
            rows[i] = new Row(numberOfPerson);
        }
    }

    @Override
    public void drawLine(Position row, Position col) {
        rows[row.getValue()].drawLine(col);
    }

    @Override
    public Row[] getRows() {
        return rows;
    }
}
