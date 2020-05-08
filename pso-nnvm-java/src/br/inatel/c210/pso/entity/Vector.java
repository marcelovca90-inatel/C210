package br.inatel.c210.pso.entity;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Vector implements Cloneable
{
    private double[] values;

    public Vector(double[] values)
    {
        this.values = values;
    }

    public double getValueAt(int i)
    {
        return this.values[i];
    }

    public void setValueAt(int i, double value)
    {
        this.values[i] = value;
    }

    @Override
    public String toString()
    {
        return "[" + Arrays
            .stream(this.values)
            .mapToObj(v -> String.format("%.2f", v))
            .collect(Collectors.joining(" ")) + "]";
    }

    @Override
    public Vector clone()
    {
        return new Vector(Arrays.copyOf(this.values, this.values.length));
    }
}
