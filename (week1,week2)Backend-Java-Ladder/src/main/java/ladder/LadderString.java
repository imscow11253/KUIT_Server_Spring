package ladder;

public class LadderString {
    private final char[][] ladderString;

    LadderString(NaturalNumber row, NaturalNumber col){
        this.ladderString = new char[row.getNumber()][col.getNumber()];
    }

    public char[][] getLadder(){
        return this.ladderString;
    }

    public void setRowString(Position row, char[] rowString){
        ladderString[row.getValue()] = rowString;
    }

    public static LadderString of(NaturalNumber row, NaturalNumber col){
        return new LadderString(row,col);
    }
}
