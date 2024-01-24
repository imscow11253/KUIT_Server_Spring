package ladder.creator;

import ladder.NaturalNumber;
import ladder.Position;
import ladder.Row;
import java.util.Random;

public class RandomLadderCreator implements LadderCreatorInterface{
    private final Row[] rows;

    public RandomLadderCreator(NaturalNumber numberOfRow, NaturalNumber numberOfPerson){
        rows = new Row[numberOfRow.getNumber()];
        for (int i = 0; i < numberOfRow.getNumber(); i++) {
            rows[i] = new Row(numberOfPerson);
        }
        setRandomLine(numberOfRow,numberOfPerson);
    }

    @Override
    public void drawLine(Position row, Position col) {
        rows[row.getValue()].drawLine(col);
    }

    @Override
    public Row[] getRows() {
        return rows;
    }

    private void setRandomLine(NaturalNumber numberOfRow, NaturalNumber numberOfPerson){
        int numberOfLine = (int) (numberOfRow.getNumber() * numberOfPerson.getNumber() *0.3);
        for(int i =0;i<numberOfLine;i++){
            if(!drawRandomLine(numberOfRow,numberOfPerson)){
                i--;
            }
        }
    }

    private boolean drawRandomLine(NaturalNumber numberOfRow, NaturalNumber numberOfPerson){
        Random random = new Random();
        int row = random.nextInt(numberOfRow.getNumber());
        int col = random.nextInt(numberOfPerson.getNumber()-1);
        try{
            System.out.println(row + " " + col);
            this.rows[row].drawLine(Position.of(col));
        }catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
