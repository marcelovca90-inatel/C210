# Import modules
import numpy as np
import matplotlib.pyplot as plt
from IPython.display import Image

# Import PySwarms
import pyswarms as ps
from pyswarms.utils.functions import single_obj as fx

# Set-up hyperparameters
options = { 'c1': 0.5, 'c2': 0.3, 'w': 0.9 }

# Call instance of PSO
# https://pyswarms.readthedocs.io/en/latest/features.html#continuous
# https://pyswarms.readthedocs.io/en/latest/api/pyswarms.single.html
optimizer = ps.single.GlobalBestPSO(n_particles=10, dimensions=2, options=options)

# Perform optimization of the sphere function
# https://pyswarms.readthedocs.io/en/latest/api/pyswarms.utils.functions.html
cost, pos = optimizer.optimize(fx.sphere, iters=100)

# Plot the cost history
from pyswarms.utils.plotters import (plot_cost_history, plot_contour, plot_surface)
plot_cost_history(cost_history=optimizer.cost_history)
plt.show()

# Initialize mesher with sphere function
from pyswarms.utils.plotters.formatters import Mesher
m = Mesher(func=fx.sphere)

# Make animation
animation2d = plot_contour(pos_history=optimizer.pos_history,  # Use the cost_history we computed
                           mesher=m,                           # Customizations
                           mark=(0,0))                         # Mark minima

# Enables us to view it in a Jupyter notebook
animation2d.save('plot0.gif', writer='imagemagick', fps=10)
Image(url='plot0.gif')

# Obtain a position-fitness matrix using the Mesher.compute_history_3d()
# method. It requires a cost history obtainable from the optimizer class
pos_history_3d = m.compute_history_3d(optimizer.pos_history)

# Make a designer and set the x,y,z limits to (-1,1), (-1,1) and (-0.1,1) respectively
from pyswarms.utils.plotters.formatters import Designer
d = Designer(limits=[(-1,1), (-1,1), (-0.1,1)], label=['x-axis', 'y-axis', 'z-axis'])

# Make animation
animation3d = plot_surface(pos_history=pos_history_3d, # Use the cost_history we computed
                           mesher=m, designer=d,       # Customizations
                           mark=(0,0,0))               # Mark minima

# Enables us to view it in a Jupyter notebook
animation3d.save('plot1.gif', writer='imagemagick', fps=10)
Image(url='plot1.gif')