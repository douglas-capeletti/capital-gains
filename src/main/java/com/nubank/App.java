package com.nubank;

import com.nubank.adapters.CliAdapter;
import com.nubank.adapters.json.JacksonAdapter;
import com.nubank.application.Simulator;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(new Locale("en", "US"));
        System.out.println();
        System.out.println(" $$$$$$\\                      $$\\   $$\\               $$\\          $$$$$$\\            $$\\                          ");
        System.out.println("$$  __$$\\                     \\__|  $$ |              $$ |        $$  __$$\\           \\__|                           ");
        System.out.println("$$ /  \\__| $$$$$$\\   $$$$$$\\  $$\\ $$$$$$\\    $$$$$$\\  $$ |        $$ /  \\__| $$$$$$\\  $$\\ $$$$$$$\\   $$$$$$$\\ ");
        System.out.println("$$ |       \\____$$\\ $$  __$$\\ $$ |\\_$$  _|   \\____$$\\ $$ |$$$$$$\\ $$ |$$$$\\  \\____$$\\ $$ |$$  __$$\\ $$  _____|");
        System.out.println("$$ |       $$$$$$$ |$$ /  $$ |$$ |  $$ |     $$$$$$$ |$$ |\\______|$$ |\\_$$ | $$$$$$$ |$$ |$$ |  $$ |\\$$$$$$\\         ");
        System.out.println("$$ |  $$\\ $$  __$$ |$$ |  $$ |$$ |  $$ |$$\\ $$  __$$ |$$ |        $$ |  $$ |$$  __$$ |$$ |$$ |  $$ | \\____$$\\        ");
        System.out.println("\\$$$$$$  |\\$$$$$$$ |$$$$$$$  |$$ |  \\$$$$  |\\$$$$$$$ |$$ |        \\$$$$$$  |\\$$$$$$$ |$$ |$$ |  $$ |$$$$$$$  |     ");
        System.out.println(" \\______/  \\_______|$$  ____/ \\__|   \\____/  \\_______|\\__|         \\______/  \\_______|\\__|\\__|  \\__|\\_______/");
        System.out.println("                    $$ |                                                                                      ");
        System.out.println("                    $$ |                                                                                      ");
        System.out.println("____________________\\__|______________________________________________________________________________________");
        System.out.println("Running...");
        System.out.println();
        //noinspection InfiniteLoopStatement
        while (true) {
            run(System.in).forEach(System.out::println);
        }
    }

    public static List<String> run(InputStream stdin) throws IOException {
        CliAdapter cli = new CliAdapter(new JacksonAdapter());
        return cli.handleInput(stdin)
                .stream()
                .map(simulation -> new Simulator().handle(simulation))
                .map(cli::handleOutput)
                .collect(Collectors.toList());
    }
}