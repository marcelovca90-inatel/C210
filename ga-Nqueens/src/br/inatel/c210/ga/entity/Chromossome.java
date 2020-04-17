package br.inatel.c210.ga.entity;

import static br.inatel.c210.ga.entity.Problem.N_QUEENS;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import br.inatel.c210.ga.algorithm.GeneticUtils;

public class Chromossome
{
    private Integer[] genes;

    public Chromossome()
    {
        this.genes = new Integer[N_QUEENS];
        for (int i = 0; i < N_QUEENS; i++)
            this.genes[i] = i;

        Collections.shuffle(Arrays.asList(this.genes), GeneticUtils.RANDOM);
    }

    public Chromossome(Integer[] genes)
    {
        this.genes = genes;
    }

    public Integer[] getGenes()
    {
        return this.genes;
    }

    public void setGenes(Integer[] genes)
    {
        this.genes = genes;
    }

    public String getFenotypeString()
    {
        char[][] matrix = new char[N_QUEENS][N_QUEENS];
        for (int i = 0; i < N_QUEENS; i++)
            for (int j = 0; j < N_QUEENS; j++)
                matrix[j][i] = (this.genes[i] == j ? '1' : '0');

        return Arrays.stream(matrix).map(String::new).collect(Collectors.joining("\n"));
    }

    public String getGenotypeString()
    {
        return Arrays.toString(this.genes);
    }

    @Override
    public String toString()
    {
        return this.getGenotypeString();
    }
}
