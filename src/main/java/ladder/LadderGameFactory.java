package ladder;

import ladder.creator.BasicLadderCreator;
import ladder.creator.LadderCreatorInterface;
import ladder.creator.RandomLadderCreator;

public class LadderGameFactory {
    private static NaturalNumber numberOfRow;
    private static NaturalNumber numberOfPerson;

    public LadderGameFactory(NaturalNumber numberOfRow, NaturalNumber numberOfPerson){
        LadderGameFactory.numberOfPerson = numberOfPerson;
        LadderGameFactory.numberOfRow = numberOfRow;
    }

    public static LadderGame createRandomLadderGame(){
        return new LadderGame(new RandomLadderCreator(numberOfRow, numberOfPerson));
    }

    public static LadderGame createBasicLadderGame(){
        return new LadderGame(new BasicLadderCreator(numberOfRow, numberOfPerson));
    }



}
