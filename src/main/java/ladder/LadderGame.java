package ladder;

import ladder.creator.LadderCreator;

public class LadderGame {

    private final LadderCreator ladderCreator;

    public LadderGame(LadderCreator ladderCreator) {
        this.ladderCreator = ladderCreator;
    }


    public int run(Position position) {
        LadderRunner ladderRunner = new LadderRunner(ladderCreator.getRows());
        return ladderRunner.run(position);
    }

    public LadderString getLadderPrint(Position position){
        LadderPrinter ladderPrinter = new LadderPrinter(ladderCreator.getRows());
        return ladderPrinter.getLadderPrint(position);
    }

}