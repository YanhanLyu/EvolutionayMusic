/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.genes;

import org.jgap.RandomGenerator;

import java.util.Random;

/**
 * A utility class that generates random notes
 *
 * @author davide nunes
 * @author Hazel Que, Yanhan Lyu
 * @version 30 May 2017
 */
public class NoteGenerator implements RandomGenerator{
    private static Random randomGenerator;

    /**
     * @return a new random Note
     */
    public static Note nextNote(){
        return new Note(
                getRandomPitch(), 
                getRandomOctave(3,5), 
                getRandomAlteration(),
                getRandomDuration(4,16));
    }

    /**
     * @param minOctave
     * @param maxOctave
     * @param minRithm
     * @param maxRithm
     * @return a random Note generated within the allowed octave and rhythm ranges
     */
    public static Note nextNote(int minOctave, int maxOctave, int minRithm, int maxRithm){
         return getRandomNote(minOctave, maxOctave, minRithm, maxRithm);
    }

    /**
     * A utility method for nextNode
     *
     * @param minOctave
     * @param maxOctave
     * @param minRithm
     * @param maxRithm
     * @return a random Note generated within the given octave and rhythm ranges
     */
    public static Note getRandomNote(int minOctave, int maxOctave, int minRithm, int maxRithm){
         return new Note(
                getRandomPitch(), 
                getRandomOctave(minOctave,maxOctave), 
                getRandomAlteration(),
                getRandomDuration(minRithm,maxRithm));
    }

    /**
     * @return a random Note from the available pitch, or a pause.
     */
    public static Pitch getRandomPitch(){   
         Pitch[] possibleNotes = Pitch.values();
         int index = 0;
        do{
            randomGenerator = new Random();  
            index = randomGenerator.nextInt(possibleNotes.length);
        }while(possibleNotes[index] == Pitch.R);
        
        return possibleNotes[index];
    }
    
     /**
     * Get a random Alteration from the available alterations
     * or a pause
     * 
     * @return note Note a random note 
     */
    public static Alteration getRandomAlteration(){
        randomGenerator = new Random();  
        Alteration[] possibleAlterations = Alteration.values();
        int index = randomGenerator.nextInt(possibleAlterations.length);
           
        return possibleAlterations[index];
    }

    /**
     * @param minOctave
     * @param maxOctave
     * @return a random octave between minOctave and maxOctave
     */
    public static int getRandomOctave(int minOctave, int maxOctave){
        if(minOctave < Note.MIN_OCTAVE || maxOctave > Note.MAX_OCTAVE) //TODO some kind of warning

        randomGenerator = new Random();
        int [] octaves = new int[maxOctave-minOctave + 1];
        
        int index = 0;
        for(int i = minOctave; i<= maxOctave; i++){
            octaves[index++] = i;
        }
        
        int selected = 0;
        selected = randomGenerator.nextInt(octaves.length);

        return octaves[selected];     
    }

    /**
     * @param min minimum duration
     * @param max maximum duration
     * @return a random duration between min and max
     */
    public static int getRandomDuration(int min, int max){ //TODO add augmentation points later
        if(min % 2 != 0 || max % 2 != 0 || min < 1 || max > 32) // TODO some kind of warning
        
        randomGenerator = new Random();
        
        int numDurations = (int) (log2(max) - log2(min) + 1);
        int [] durations = new int[numDurations]; //1, 2, 4, 8, 16, 32 
        
        int index = 0;
        for(int i = min; i<= max; i=i*2){
            durations[index++] = i;
        }
        
        int selected = randomGenerator.nextInt(durations.length);
        randomGenerator = new Random();
        return durations[selected];     
    }

    @Override
    public int nextInt() {
        return randomGenerator.nextInt();
    }

    @Override
    public int nextInt(int i) {
        return randomGenerator.nextInt(i);
    }

    @Override
    public long nextLong() {
       return randomGenerator.nextLong();
    }

    @Override
    public double nextDouble() {
        return randomGenerator.nextDouble();
    }

    @Override
    public float nextFloat() {
       return randomGenerator.nextFloat();
    }

    @Override
    public boolean nextBoolean() {
        return randomGenerator.nextBoolean();
    }


    /**
     * A utility class to calculate log2 of a number
     * @param n
     * @return log2n
     */
    private static double log2(double n){
        return Math.log(n)/Math.log(2);
    }
    
}
