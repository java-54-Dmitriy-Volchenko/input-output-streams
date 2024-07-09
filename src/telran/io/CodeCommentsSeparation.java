package telran.io;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class CodeCommentsSeparation {

    public static void main(String[] args) {
     

        if (args.length < 3) {
            System.out.println("Usage: java CodeCommentsSeparation <source file> <code file> <comments file>");
            return;
        }

        String sourceFilePath = args[0];
        String codeFilePath = args[1];
        String commentsFilePath = args[2];

        try {
            List<String> lines = Files.readAllLines(Paths.get(sourceFilePath));

            List<String> codeLines = lines.stream()
                .filter(line -> !line.trim().startsWith("//") && !line.trim().startsWith("/*") && !line.trim().contains("*/"))
                .collect(Collectors.toList());

            List<String> commentLines = lines.stream()
                .filter(line -> line.trim().startsWith("//") || line.trim().startsWith("/*") || line.trim().contains("*/"))
                .collect(Collectors.toList());

            Files.write(Paths.get(codeFilePath), codeLines);
            Files.write(Paths.get(commentsFilePath), commentLines);

            System.out.println("Separation complete. Code and comments have been written to separate files.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
