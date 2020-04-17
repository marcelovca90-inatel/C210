package br.inatel.c210.ga.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import br.inatel.c210.ga.algorithm.GeneticUtils;

public class Chromossome
{
    private Integer[] genes;

    public Chromossome()
    {
        this.genes = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 };

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
        char[][] matrix = new char[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
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
