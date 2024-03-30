package org.example.hangedman.model;
import java.util.ArrayList;
import java.util.List;

public class SecretWord {

    private String word;

    private int wordLength;

    public SecretWord(String word){
        this.word = word;
        wordLength = word.length();
    }

    public boolean letterIsInWord(char letter){
        return word.contains(String.valueOf(letter));
    }

    public List<Integer> getIndicesOfChar(String word, char letter){
        List<Integer> indices= new ArrayList<>();
        for (int i = 0; i < wordLength; i++){
            if (word.charAt(i) == letter){
                indices.add(i);
            }
        }
        return indices;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
