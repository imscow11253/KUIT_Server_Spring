package ladder.creator;

import ladder.Position;
import ladder.Row;

public interface LadderCreatorInterface {

    public void drawLine(Position row, Position col);

    public Row[] getRows();
}
