# A Graph Based Implementation of the Game of Life

This is an implementation of the Game of Life where living cells and their relationships with each other are stored in a graph. Each node of the graph represents a cell, while the edges of the graph represent the relationship each living cell has with the others.

There are 4 relationship types that could connect a cell with other cells in this graph:

![A diagram showing the different cells of different relationship types and how they would be laid out on a Game of Life board](assets/images/edge_types.png)

- __SELF__: Represents the cell itself
- __NEIGHBOUR__: In the visual representation of the Game of Life, this is a cell in the Moore Neighbourhood immediately surrounding the 'SELF' cell. These cells influence whether the 'SELF' cell survives to the next turn.
- __COPARENT__: A cell that can work in conjunction with the self cell to create new life. These cells can either also be NEIGHBOURS, or they can be part of the group of cells immediately surrounding the neighbours.
- __DISCONNECTED__: A cell that has no impact on the SELF cell at all. It's too far away.