package ladder;

import com.sun.jdi.event.ExceptionEvent;
import ladder.creator.LadderCreator;
import ladder.creator.LadderCreatorInterface;
import ladder.creator.BasicLadderCreator;
import ladder.creator.RandomLadderCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LadderGameTest {

    @Test
    void 사다리_생성_확인() {
        //given
        NaturalNumber numberOfRow = NaturalNumber.of(3);
        NaturalNumber numberOfPerson = NaturalNumber.of(5);

        //when
        LadderCreator ladderCreator = new LadderCreator(numberOfRow, numberOfPerson);

        //then
        assertNotNull(ladderCreator);
    }

    @Test
    void 사다리_사람_예외_처리_확인() {
        //when
        NaturalNumber numberOfRow = NaturalNumber.of(1);
        NaturalNumber numberOfPerson = NaturalNumber.of(3);
        LadderCreatorInterface ladderCreator = new BasicLadderCreator(numberOfRow, numberOfPerson);
        LadderGame ladderGame = new LadderGame(ladderCreator);

        //given
        int nthOfPerson = 4;
        Position position = Position.of(nthOfPerson);

        //then
        assertThrows(IllegalArgumentException.class, ()->ladderGame.run(position));
    }

    @Test
    void 사다리_결과_확인() {
        //when
        NaturalNumber numberOfRow = NaturalNumber.of(3);
        NaturalNumber numberOfPerson = NaturalNumber.of(4);
        LadderCreatorInterface ladderCreator = new BasicLadderCreator(numberOfRow, numberOfPerson);

        LadderGame ladderGame = new LadderGame(ladderCreator);

        ladderCreator.drawLine(Position.of(0),Position.of(0));
        ladderCreator.drawLine(Position.of(1),Position.of(1));
        ladderCreator.drawLine(Position.of(2),Position.of(0));

        //given
        int nthOfPerson = 0;
        Position position = Position.of(nthOfPerson);

        //then
        assertEquals(2, ladderGame.run(position));

        //given
        nthOfPerson = 1;
        position = Position.of(nthOfPerson);

        //then
        assertEquals(1, ladderGame.run(position));

        //given
        nthOfPerson = 2;
        position = Position.of(nthOfPerson);

        //then
        assertEquals(0, ladderGame.run(position));
    }

    @DisplayName("*의 위치가 없는 출력")
    @Test
    void 사다리_프린트_확인_노드_위치_미포함(){
        //when
        NaturalNumber numberOfRow = NaturalNumber.of(3);
        NaturalNumber numberOfPerson = NaturalNumber.of(4);
        LadderCreatorInterface ladderCreator = new BasicLadderCreator(numberOfRow, numberOfPerson);

        LadderGame ladderGame = new LadderGame(ladderCreator);

        ladderCreator.drawLine(Position.of(0),Position.of(0));
        ladderCreator.drawLine(Position.of(1),Position.of(1));
        ladderCreator.drawLine(Position.of(2),Position.of(0));

        //given
        char[][] ladderString = ladderGame.getLadderPrint().getLadder();
        char[] ladderStringRow1 = {'\u0000','1','\u0000','-','1','\u0000','\u0000','0','\u0000','\u0000','0','\u0000'};
        char[] ladderStringRow2 = {'\u0000','0','\u0000','\u0000','1','\u0000','-','1','\u0000','\u0000','0','\u0000'};

        //then
        assertArrayEquals(ladderStringRow1, ladderString[0]);
        assertArrayEquals(ladderStringRow2, ladderString[1]);
        assertArrayEquals(ladderStringRow1, ladderString[2]);
    }

    @Test
    void 랜덤_사다리_생성_확인(){
        //when
        NaturalNumber numberOfRow = NaturalNumber.of(3);
        NaturalNumber numberOfPerson = NaturalNumber.of(4);
        LadderCreatorInterface ladderCreator = new RandomLadderCreator(numberOfRow, numberOfPerson);

        LadderGame ladderGame = new LadderGame(ladderCreator);

        char[][] ladderString = ladderGame.getLadderPrint().getLadder();
        for(int i =0;i<ladderString.length;i++){
            for(int j=0;j<ladderString[0].length;j++){
                System.out.print(ladderString[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}