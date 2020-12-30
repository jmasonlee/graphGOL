# Cool Things I've noticed, but haven't had the time to explore
These are all half-baked and not fully thought out, but I didn't want to forget them

### WTF! Euclidean distance vs, not within a range
I just saw something cool! I was using Euclidean distance to categorize the relationships between cells, but there is a much simpler heuristic! Not sure how it will map out in code, but you can figure out whether a cell belongs in the SELF, COPARENT, NEIGHBOUR or DISCONNECTED category by looking at it's coordinates, and allowing it to "fall" outwards (From SELF -> DISCONNECTED) depending on which allowed values the cell has in it's coordinate pair.

SELF can only contain the cell's coordinates

NEIGHBOUR cells are not SELF Cells, and will only contain the cells coordinates +/- 1

COPARENT cells are not SELF or NEIGHBOUR cells and will only contain the cells coordinates +/- 2

All other cells are DISCONNECTED.

This is not fully or even well written up yet, and I don't think I've fully wrapped my brain arond it... but it's cool :-)
