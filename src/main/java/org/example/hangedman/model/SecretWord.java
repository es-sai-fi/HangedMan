package org.example.hangedman.model;
import java.util.ArrayList;
import java.util.List;

public class SecretWord {

    private String word;
    private int wordLength;
    private String[] splitWordArray;

    public SecretWord(String word){
        this.word = word;
        wordLength = word.length();
        splitWordArray = word.split("");
    }

    public boolean letterIsInWord(String letter){
        return word.contains(letter);
    }

    public List<Integer> getIndicesOfChar(String inputLetter){
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < wordLength; i++){
            String letterAtWord = splitWordArray[i].toLowerCase();
            if (letterAtWord.equals(inputLetter)){
                indices.add(i);
            }
        }
        return indices;
    }

    public String getWord() {
        return word;
    }

    public int getWordLength() {
        return wordLength;
    }

    public String[] getSplitWordArray() {
        return splitWordArray;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
