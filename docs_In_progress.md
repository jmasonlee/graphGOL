I'm taking time to order my thoughts, so my in-progress notes are here... You are welcome to look, just keep in mind that anything here isn't ready for feedback

# A Relationship Based Representation of the Game of Life
###  What is a graph?
## Why?
## The Algorithm
### The data structure
### Determining which cells are born
### WTF! Euclidean distance vs, not within a range
I just saw something cool! I was using Euclidean distance to categorize the relationships between cells, but there is a much simpler heuristic! Not sure how it will map out in code, but you can figure out whether a cell belongs in the SELF, COPARENT, NEIGHBOUR or DISCONNECTED category by looking at it's coordinates, and allowing it to "fall" outwards (From SELF -> DISCONNECTED) depending on which allowed values the cell has in it's coordinate pair.

SELF can only contain the cell's coordinates

NEIGHBOUR cells are not SELF Cells, and will only contain the cells coordinates +/- 1

COPARENT cells are not SELF or NEIGHBOUR cells and will only contain the cells coordinates +/- 2

All other cells are DISCONNECTED.

This is not fully or even well written up yet, and I don't think I've fully wrapped my brain arond it... but it's cool :-)
## Approval Tests
### Approvals vs. Assertions
### TDD with Approvals
#### Assertion
#### Prettify
#### Expand
##### Don't do this #1
##### Don't do this #2
##### How I tested all combinations of neighbours
#### Absorb
### Recipes
#### CombinationApprovals and arrays of lists
