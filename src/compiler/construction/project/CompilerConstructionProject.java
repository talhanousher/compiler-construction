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
        LA.splitWord("interface A_B_C::d\n"
                + "{{ while ( a <<<==b!=d)\n"
                + "string \"a+b+c--//a==b\\n\\\\"
                + "\\* a==b=\"*/\n"
                + "Float //3abc.9bb.888.a9bb.9bc++99.8e+55\n"
                + "struct **ptr = file(\"abc.txt+'\\\\'+'+'+='+\n"
                + "'return true;");
//        LA.splitWord("\"a+b+c--//a==b\\n\\\\");
    }

}
