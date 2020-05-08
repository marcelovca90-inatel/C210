import math

class Functions:

    def rastrigin(x):
        ans = 10*len(x)
        for i in range(len(x)):
            ans += (x[i]**2 - (10 * math.cos(2 * math.pi * x[i])))
        return ans

    def sphere(x):
        ans = 0.0
        for i in range(len(x)):
            ans += x[i]**2
        return ans