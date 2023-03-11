import org.kohsuke.args4j.*;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    @Argument(required = true, metaVar = "InputObjects")
    private List<String> listOfObjects = new ArrayList<>();

    @Option(name = "-h", metaVar = "HumanFormat")
    private boolean h;

    @Option(name = "-c", metaVar = "Summarize")
    private boolean c;

    @Option(name = "-si", metaVar = "Base")
    private boolean si;

    public static void main(String[] args){
        new Parser().parse(args);
    }

    private int parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return 1;
        }

        Du duHast /*du hast mich*/ = new Du();
        try {
            listOfObjects.remove("du");
            duHast.output(listOfObjects, h, c, si);
        } catch (
                IllegalArgumentException e) {
            System.err.println("file not found");
            return 1;
        }
        return 0;
    }
}
