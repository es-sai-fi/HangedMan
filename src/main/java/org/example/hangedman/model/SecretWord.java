package org.example.hangedman.model;

public class SecretWord {

    private String word;
    private char inputLetter;

    public SecretWord(String word){
        this.word = word;
    }

    /*public boolean compareLetterToWord(char inputLetter){

    }*/

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
