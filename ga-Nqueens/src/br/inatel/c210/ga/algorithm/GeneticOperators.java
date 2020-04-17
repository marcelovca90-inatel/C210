package br.inatel.c210.ga.algorithm;

import static br.inatel.c210.ga.algorithm.GeneticUtils.RANDOM;
import static br.inatel.c210.ga.entity.Problem.N_QUEENS;

import java.util.List;

import br.inatel.c210.ga.entity.Chromossome;

public class GeneticOperators
{
    private static final double MUTATION_PROBABILITY = 0.05;

    public static Chromossome[] selection(List<Chromossome> population)
    {
        // choose parents for crossover; they must not be the same individual
        int parent1Index = -1, parent2Index = -1;
        do
        {
            parent1Index = RANDOM.nextInt(population.size());
            parent2Index = RANDOM.nextInt(population.size());
        } while (parent1Index == parent2Index);

        Chromossome parent1 = population.get(parent1Index);
        System.out.println("1st parent chosen for crossover: " + GeneticUtils.formatChromossome(parent1));
        Chromossome parent2 = population.get(parent2Index);
        System.out.println("2nd parent chosen for crossover: " + GeneticUtils.formatChromossome(parent2));

        return new Chromossome[] { parent1, parent2 };
    }

    public static void crossover(List<Chromossome> population, Chromossome parent1, Chromossome parent2)
    {
        // choose point for crossover; it must be within genes indices
        int crossoverPoint = 0;
        do
        {
            crossoverPoint = RANDOM.nextInt(N_QUEENS);
        } while (crossoverPoint == 0);
        System.out.println("Crossover will happen at point " + crossoverPoint);

        // initialize genes to perform the crossover
        Integer[] child1genes = new Integer[N_QUEENS];
        Integer[] child2genes = new Integer[N_QUEENS];

        // copy genes before the crossover point
        for (int i = 0; i < crossoverPoint; i++)
        {
            child1genes[i] = parent1.getGenes()[i];
            child2genes[i] = parent2.getGenes()[i];
        }

        // copy genes from the crossover point on
        for (int i = crossoverPoint; i < N_QUEENS; i++)
        {
            child1genes[i] = parent2.getGenes()[i];
            child2genes[i] = parent1.getGenes()[i];
        }

        // instantiate the first child
        Chromossome child1 = new Chromossome(child1genes);
        System.out.println("1st child generated from crossover: " + GeneticUtils.formatChromossome(child1));

        // instantiate the second child
        Chromossome child2 = new Chromossome(child2genes);
        System.out.println("2nd child generated from crossover: " + GeneticUtils.formatChromossome(child2));

        // add children to population
        population.add(child1);
        population.add(child2);
    }

    public static void mutation(List<Chromossome> population)
    {
        // determine if there will be a mutation
        double prob = RANDOM.nextDouble();
        if (prob < MUTATION_PROBABILITY)
        {
            // choose individual for mutation
            int mutationIndividualIndex = RANDOM.nextInt(population.size());
            Chromossome target = population.get(mutationIndividualIndex);

            // choose mutation points
            int p1 = -1, p2 = -1;
            do
            {
                p1 = RANDOM.nextInt(N_QUEENS);
                p2 = RANDOM.nextInt(N_QUEENS);
            } while (p1 == p2);
            System.out.println("Individual " + target + " will mutate at points (" + p1 + "," + p2 + ")");

            // perform swap mutation
            Integer[] genes = target.getGenes();
            Integer aux = genes[p1];
            genes[p1] = genes[p2];
            genes[p2] = aux;
            target.setGenes(genes);
            System.out.println("Individual " + target + " mutated at point (" + p1 + "," + p2 + ")");

            // update the population with the mutated individual
            population.set(mutationIndividualIndex, target);
        }
    }

    public static void elitism(List<Chromossome> population)
    {
        // remove the worst two individuals from population
        for (int i = 0; i < 2; i++)
        {
            int worstIndividualIndex = GeneticUtils.findWorstChromossomeIndex(population);
            Chromossome worstIndividual = population.get(worstIndividualIndex);
            System.out.println("Removing worst individual from population: " + GeneticUtils.formatChromossome(worstIndividual));
            population.remove(worstIndividualIndex);
        }
    }
}
