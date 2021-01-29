# Approvals
## Assertions vs. Approvals?
### Tests as Documentation
Although they rarely describe _why_ a piece of code behaves the way it does, I firmly believe that a passing suite of unit tests is one of the quickest ways to document _what_ a piece of code is meant to do, for two reasons:

1. Unit tests are generally kept close to the code they are working with, which makes them easy to find.
2. Requiring a passing suite of unit tests on each successful build ensures the tests are always updated with the current expected behaviour of your unit.

With assertion-based testing, tests need to be well-named in order to document your code's behaviour. Reading through a suite of tests for a unit reads like a list of requirements for the unit's behaviour. In JavaScript, testing frameworks like Jest have embraced this well-namingness with 'it' statements and the 'should' convention of naming tests.

```
// A test in jest
describe(`Cell equality`, () => {
    it(`should be true with two objects of identical coordinates`, () => {
        expect(isEqual(cell1, cell2)).toBeTrue
    })
})
```

When you read the above, you know immediately, in plain English, what is required for two cells to be equal. 

Similarly, in Junit, many different naming conventions exist, in order to clearly communicate what each assertion is meant to check. An example of this is the `Should_ExpectedBehavior_WhenStateUnderTest` naming convention, which creates tests named similarly to the above jest convention:

```
# A JUnit4 test

@Test
public void Should_BeEqualToAnotherCell_WithIdenticalCoordinates(){
    Cell cell1 = new Cell(1, 4);
    Cell cell2 = new Cell(1, 4);

    assertEquals(cell1, cell2);
}
```

The end result in either language is a set of tests whose names _should_ be a list of required behaviours for the class. It _should_ be fairly quick and easy to read a test suite and understand what is expected of a class, and it _should_ be easy to understand which behaviours aren't working as expected when a test fails. Of course, this isn't always true and different code smells can prevent tests from documenting correctly, if at all.

TODOs that came out of writing this section:
- [ ] Ensure all assertion tests in the code are using the above convention
- [ ] Read through approvals to make sure each test is English
- [ ] Figure out if it's possible to label approved cases more intelligently... or is it valuable?

### Parseability / Ease of Use
This is the coolest part of using Approvals as a testing library, and the one I'm most excited about. This is also why my BoardOutputter is so over-engineered.



I used to work in an office where taking time to ensure the parseability of your code and clear communication of requirements was particularly important, because it was particularly difficult. 

On this project, less than 20% of the developers and testers spoke English as a first language (or at all), and at one point, 3 of our 30 developers, had some degree of dyslexia. Until we agreed on communication standards, like writing code in the most common shared language (English), 

### Speed
### Coverage
### Test Strategy
#### Don't do this (Underchecking with spot checks)
#### Don't do this no. 2 (Overchecking because you can)
#### Do do this (Test functionality at the level it's implemented)


## Learning to work with Snapshot tests properly from an assertion only background
