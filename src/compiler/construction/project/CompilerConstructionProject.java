/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.construction.project;

/**
 *
 * @author Dell
 */
public class CompilerConstructionProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LexicalAnalyzer LA = new LexicalAnalyzer();
//        LA.isKeyword("asd");
        LA.splitWord("void main()\n" + "{{\n" + "Int a = 659,b,c,d;\n" + "while(a<==a!=b&&&c)\n" + "if_else(\n" + "char a = '\\n'+'+'+a++b!\n" + "String *ptr = \"abc++-b*c/=d\"\n" + "return;\n" + "}");
//        boolean validateIdentifiers = LA.validateString("\"1aasgvjhgjhv123456789876543213!@###$$$$^$%^%^%$$%%%dlkjas\"");
//        System.out.println(validateIdentifiers);
//        System.out.println("");
    }

}
