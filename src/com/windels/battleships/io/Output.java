package com.windels.battleships.io;

import com.windels.battleships.controller.GameBoard;
import java.util.List;

public interface Output {

    void renderGameBoard(GameBoard gameBoard);

    void displayInGameText(String text);

    void displayGameAdminText(String introText);

    void displaySavedGames(List<String> savedGames);
}
