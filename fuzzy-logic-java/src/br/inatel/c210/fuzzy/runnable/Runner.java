package br.inatel.c210.fuzzy.runnable;

import net.sourceforge.jFuzzyLogic.FIS;

public class Runner
{
    public static void main(String[] args)
    {
        // First load an FCL file, using FIS.load(fileName) function.
        String fileName = "res/tipper.fcl";
        FIS fis = FIS.load(fileName, true);

        // When an error is detected during FIS.load(), a null object is returned.
        if (fis == null)
        { // Error while loading?
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Now we can plot the FIS. Actually, we plot each Variable in
        // the FUNCTION_BLOCK (and each LinguisticTerm in each Variable).
        // This might only be usefull when you are debugging your code.
        fis.chart();

        // Now we use the FIS. We start by setting FIS' inputs
        fis.setVariable("service", 3);
        fis.setVariable("food", 7);

        // And we run the system
        fis.evaluate();

        // If we want to know the system's output
        // (in this case there's only one output variable 'tip')
        fis.getVariable("tip").getLatestDefuzzifiedValue();

        // We can also plot output's defuzzifier (probably only during debuging)
        fis.getVariable("tip").chartDefuzzifier(true);

        // And, of course, we can print the FIS
        System.out.println(fis);
    }
}
