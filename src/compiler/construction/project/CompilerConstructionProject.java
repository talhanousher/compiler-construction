/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.construction.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class CompilerConstructionProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        LexicalAnalyzer LA = new LexicalAnalyzer();

        FileReader fr = new FileReader("C:\\Users\\Dell\\Documents\\NetBeansProjects\\Compiler Construction Project\\src\\input.txt");
        BufferedReader br = new BufferedReader(fr);

        String str = "", r;

        while ((r = br.readLine()) != null) {
            str += r;
            str += "\n";

        }

        System.out.println(str);
        ArrayList<Token> finalList = LA.splitWord(str);

        SyntaxAnalyzer sa = new SyntaxAnalyzer();
//        boolean checkOE = sa.MST(finalList);
//        System.out.println(checkOE);
        boolean check = sa.syntaxChecker(finalList);
        System.out.println(check);
    }

}
