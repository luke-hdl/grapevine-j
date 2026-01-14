package com.grapevine;

import com.grapevine.io.files.ExchangeLoader;
import com.grapevine.io.files.ExchangeSaver;
import com.grapevine.records.game.Game;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Game game = new ExchangeLoader().loadExchange("../sample/complex_game.gex");
        System.out.println(game.toDetailedString());
        new ExchangeSaver().saveExchange(game, "./test_out.gex");
    }
}
