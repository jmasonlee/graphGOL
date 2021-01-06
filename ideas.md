# Cool Things I've noticed, but haven't had the time to explore
These are all half-baked and not fully thought out, but I didn't want to forget them

### WTF! Euclidean distance vs, not within a range
I just saw something cool! I was using Euclidean distance to categorize the relationships between cells, but there is a much simpler heuristic! Not sure how it will map out in code, but you can figure out whether a cell belongs in the SELF, COPARENT, NEIGHBOUR or DISCONNECTED category by looking at it's coordinates, and allowing it to "fall" outwards (From SELF -> DISCONNECTED) depending on which allowed values the cell has in it's coordinate pair.

SELF can only contain the cell's coordinates

NEIGHBOUR cells are not SELF Cells, and will only contain the cells coordinates +/- 1

COPARENT cells are not SELF or NEIGHBOUR cells and will only contain the cells coordinates +/- 2

All other cells are DISCONNECTED.

This is not fully or even well written up yet, and I don't think I've fully wrapped my brain arond it... but it's cool :-)

### Coordinates
Don't necessarily need to be stored WRT the board. They should be stored WRT other living cells... Or not stored?

### Approvals
 - make your tests more accessible (Language/Ability are no longer blockers to writing a good test)
 - If you are parsing your tests instead of reading them, you're doing it wrong
 - When building new functionality using TDD with approvals, your test file shouldn't be so long you can't read it. (Not the same for legacy code)
 - Instead of thinking in terms of expanding the single test you've written, think in terms of expanding around the functionality of the method under test.
   - Example "God" class shouldn't need to test that all combinations of two cells are filtered, because that is already covered under the "LiveCellsGraphTest" It's sufficient to simply checkthat two cells are covered and then write a new assert for 3 cells to be absorbed into the approval.
 https://www.youtube.com/watch?v=dJl5FLOp5Hw&t=1479s
 - I like how Emily uses a separate sketch to keep track of her final desired state. Allows you to modify the approved file a little and save it when it gets better, even if it isn't all the way there.
 - Random unordered data (Set) doesn't fit nicely with Approvals.
 - BDD starts with user-facing scenarios that can 
 - Emily will drop down to unit tests if needed, Adrian thinks the finer-grained logic could have been fleshed out better with asserts
 - BDD with approvals provides short feedback loops.
 - Incremental approvals give the effect of being able to take small steps while keeping a larger test to implement.

