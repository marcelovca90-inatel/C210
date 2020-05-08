package br.inatel.c210.pso.algorithm;

import java.util.Random;
import java.util.UUID;

public class RandomUtils
{
    public static final Random RANDOM = new Random(42L);

    public static String randomId()
    {
        return UUID.randomUUID().toString();
    }

    public static double randomDouble()
    {
        return RANDOM.nextDouble();
    }

    public static double[] zeroDoubles(int n)
    {
        double[] values = new double[n];
        for (int i = 0; i < n; i++)
            values[i] = 0.0;
        return values;
    }

    public static double[] randomDoubles(int n)
    {
        double[] values = new double[n];
        for (int i = 0; i < n; i++)
            values[i] = randomDouble();
        return values;
    }

    public static double randomSignedDouble()
    {
        return (RANDOM.nextBoolean() ? -1.0 : +1.0) * RANDOM.nextDouble();
    }

    public static double[] randomSignedDoubles(int n)
    {
        double[] values = new double[n];
        for (int i = 0; i < n; i++)
            values[i] = randomSignedDouble();
        return values;
    }
}
