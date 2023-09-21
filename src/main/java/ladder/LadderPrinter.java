package ladder;

public class LadderPrinter {
    private final Row[] rows;

    LadderPrinter(Row[] rows){
        this.rows = rows;
    }

    public LadderString getLadderPrint(Position position) {
        LadderString ladderString = new LadderString(NaturalNumber.of(rows.length), rows[position.getValue()].getCol());
        for (int i = 0; i < rows.length; i++) {
            ladderString.setRowString(Position.of(i), rows[i].getRowString());
        }
        return ladderString;
    }
}
