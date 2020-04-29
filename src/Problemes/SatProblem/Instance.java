package Problemes.SatProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class Instance {
    private int nbVars;
    private int nbClauses;
    // contains for each literal the clauses it satisfies
    private BitSet[][] literals;

    public Instance(int numberOfVariables, int numberOfClauses) {
        this.nbVars = numberOfVariables;
        this.nbClauses =numberOfClauses ;

        literals = new BitSet[2][nbVars];

        for (int i = 0; i < nbVars; i++) {
            literals[0][i] = new BitSet(nbClauses);
            literals[1][i] = new BitSet(nbClauses);
        }
    }

    public static Instance fromDIMACS(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line.charAt(0) != 'p') {
            line = reader.readLine();
        }

        String[] first = line.split("\\s+");
        Instance se = new Instance(Integer.parseInt(first[2]),Integer.parseInt(first[3]));

        int i = 0;
        while ((line = reader.readLine()) != null && i < se.nbClauses) {
            if(line.length()>0 && line.charAt(0) == ' ')
                line = line.substring(1);

            String[] sLine = line.split("\\s+");
            for (int j = 0; j < sLine.length - 1; j++) {
                int i1 = Integer.parseInt(sLine[j]);
                se.literals[(i1 > 0)?1:0][Math.abs(i1)-1].set(i);
            }
            i++;
        }
        reader.close();
        return se;
    }

    public BitSet[][] getLiterals() {
        return literals;
    }

    public int getNbVars() {
        return nbVars;
    }

    public int getNbClauses() {
        return nbClauses;
    }
}
