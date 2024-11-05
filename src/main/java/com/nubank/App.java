package com.nubank;

import com.nubank.application.CliRunner;
import com.nubank.infrastructure.objectParsers.JsonAdapter;
import com.nubank.infrastructure.userInterfaces.CliAdapter;

import java.util.Locale;

public class App {

    private static boolean debugEnable = false;

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        if(args.length > 0 && ("-d".equals(args[0]) || "--debug".equals(args[0]))){
            debugEnable = true;
        }
        CliRunner cliRunner = new CliRunner(new CliAdapter(new JsonAdapter())).printBanner();
        //noinspection InfiniteLoopStatement
        while (true) {
            cliRunner.run(System.in).forEach(System.out::println);
        }
    }

    public static boolean debug(){
        return debugEnable;
    }


}