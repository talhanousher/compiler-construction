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
public class WordAndClass {

    String word;
    String classPart;

    public WordAndClass(String w, String c) {
        this.word = w;
        this.classPart = c;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getClassPart() {
        return classPart;
    }

    public void setClassPart(String classPart) {
        this.classPart = classPart;
    }

}
