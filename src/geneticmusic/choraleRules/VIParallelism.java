/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticmusic.choraleRules;

import geneticmusic.genes.Note;
import geneticmusic.fitness.AbstractCompositionRule;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 * Voice Independence Rule -
 *
 * Calculate the distances between the same pair of voices in the current chord and the next chord,
 * if the distances are not octave or fifth at the same time, then parallelism is not found.
 * Fitness score increases.
 * 
 * @author davide
 * @author Hazel Que, Yanhan Lyu
 * @version 30 May 2017
 */
public class VIParallelism extends AbstractCompositionRule{
    private static final double OCTAVE = 6;
    private static final double FIFTH = 3.5;
    
    public VIParallelism(double weight){
        super(weight);
    }

    @Override
    protected double evaluation(IChromosome ic) {
        double result = 0.0;
        
        Gene[] genes = ic.getGenes();
        
        for(int i = 0; i<genes.length-1; i++){
             Note[] currentChord = (Note[]) genes[i].getAllele(); // get the current chord
             Note[] nextChord = (Note[]) genes[i+1].getAllele(); // get the next chord
             boolean parallelismFound = false;
             
             for(int k = 0; k <= 3; k++){
                 for(int j= 0; j <= 3; j++){
                     if(k!=j){
                         double distance = Math.abs(currentChord[k].distance(currentChord[j]));
                         if(distance == OCTAVE || distance == FIFTH){
                             double distanceNext = Math.abs(nextChord[k].distance(nextChord[j]));
                             if(distanceNext == distance)
                                 parallelismFound = true;
                         }
                     }
                 }
             }
             if(!parallelismFound)
                 result+= 1/(genes.length-1);
        }
        return result;
    }

    @Override
    public String getName() {
        return "No Parallelism Rule";
    }
}
