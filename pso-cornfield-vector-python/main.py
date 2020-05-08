from modules.functions import Functions
from modules.particle import Particle
from modules.pso import PSO

if __name__ == "__main__":

    num_dimensions = 2                  # number of dimensions in the search space
    cost_function = Functions.sphere    # cost function to be evaluated (sphere, rastrigin, etc.)
    input_bounds = [(-2,+2),(-2,+2)]    # input bounds [(x1_min,x1_max),(x2_min,x2_max)...]
    num_particles = 10                  # number of particles in the swarm
    max_iterations = 30                 # maximum number of iterations to perform

    PSO(num_dimensions,cost_function,input_bounds,num_particles,max_iterations)
