package com.nubank.application;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class CliRunner {

    private final UserInterface<InputStream, String> ui;

    public CliRunner(UserInterface<InputStream, String> ui) {
        this.ui = ui;
    }

    public List<String> run(InputStream stdin) {
        return ui.handleInput(stdin)
                .stream()
                .map(simulation -> new Simulator().handle(simulation))
                .map(ui::handleOutput)
                .collect(Collectors.toList());
    }

    public CliRunner printBanner(){
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
        return this;
    }
}
