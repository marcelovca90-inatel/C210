import random

from .bitset import BitSet

class Chromossome:
    def __init__(self, x = None, y = None):
        if x == None:
            x = random.randint(-15, 15)

        if y == None:
            y = random.randint(-15, 15)

        self.__genes = Chromossome.get_genotype(x, y)

    def get_genes(self):
        return self.__genes

    def set_genes(self, genes):
        self.__genes = genes

    def to_string(self):
        chr_str = "G = ["

        for i in range(10):
            chr_str += (i == 5 and " " or "") + (self.__genes.get(i) and "1" or "0")

        x, y = Chromossome.get_fenotype(self.__genes)

        chr_str += "], F = [" + str(x) + ", " + str(y) + "]"

        return chr_str

    @staticmethod
    def get_genotype(x, y):
        bits = BitSet(10)

        xy_binary = "{:05b}".format(x) + "{:05b}".format(y)

        for i in range(10):
            bits.set(i, xy_binary[i] == '1')

        return bits

    @staticmethod
    def get_fenotype(genes):

        # XXXXX YYYYY (5 bits to each variable: 4 for value and 1 for sign pos/neg)
        # 01234 56789 (bits are indexed from left to right, adopting little-endian)
        # 8421K 8421K (bits on indices 4 and 9 represent the variables sign, i.e.
        #              0 represents a positive and 1 represents a negative value;
        #              https://en.wikipedia.org/wiki/Sign_bit for more information)

        x = (8 * genes.get(0) +
            4 * genes.get(1) +
            2 * genes.get(2) +
            1 * genes.get(3))

        if (genes.get(4) == 1):
            x *= -1

        y = (8 * genes.get(5) +
            4 * genes.get(6) +
            2 * genes.get(7) +
            1 * genes.get(8))

        if (genes.get(9) == 1):
            y *= -1

        return x, y
