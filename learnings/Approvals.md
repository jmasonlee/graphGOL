# Approvals
## Assertions vs. Approvals?
### Tests as Documentation
Although they [rarely describe _why_ a piece of code behaves the way it does](https://www.youtube.com/watch?v=Gms8GijwO9Q), I firmly believe that a passing suite of unit tests is one of the quickest ways to document _what_ a piece of code is meant to do, for two reasons:

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

The end result in either language is a set of tests whose names _should_ be a list of required behaviours for the class. It _should_ be fairly quick and easy to read a test suite and understand what is expected of a class, and it _should_ be easy to understand which behaviours aren't working as expected when a test fails. Of course, this isn't always true and is usually impacted by cultural, time or management constraints.

###Tests as Documentation with Approvals
A suite of well-written unit tests keeps documentation close to your code, in a place where it changes if the code does. Assertion-based tests enable this documentation through many clearly named test methods, but one of the benefits of approvals is that you can succinctly write a single, fully covering, test case. Can a single test name give you the documentation you need?

This made me question why that documentation would be needed, and when it was valuable to me. I've used tests in the past to:
- understand what a class or function is meant to do, before I start changing things.
- identify exactly which behaviour is failing, and why it is a failure
- gain awareness of unexpected edge-cases that may indicate I have an incomplete understanding of the domain.



My first thought was that documentation would be covered by the approvals file, mainly because of point #2. if it was possible, the tests should be written in a way that the input variables provided in each case under test need to be clearly described, and labeled in the approvals file. 

This resulted in me starting to build [a fairly intricate and complex StringBuilder](https://github.com/jmasonlee/graphGOL/commit/07c33a2662ef9d38979e1f627309d57127fe93e0#diff-50614f5e42a3b3a703eb669b2a60817e033a5c92b29a47fb56e71a567e3255b2)... but I'm not actually sure if it provided the value I'm looking for. Yes, it's now easier to read the approvals file, but for whom? My guess is mainly English speakers. You also now need to try to understand the English printed with the approved file. The English bloats the file, adding a lot of bulk that is essentially hard-coded and not valuable unless you have a failing test. This is especially annoying if you are using the fact that you can test complete coverage of all cases with Approvals - in this case, your file could be thousands of tests. A line or two of extra bloat would increase the size of the file you're testing multiple times for something which is only ever mainly read by a machine.

One of the reasons parameterized testing is as valuable as it is, is that it lets test-writers specify a whole bunch of different inputs and provide them to a test function as parameters. This format of testing is also more legible than writing a single assertion test for each behaviour being checked. My next attempt will look at applying some ideas from parameterized testing to my approvals to see if they can be made more legible that way.

TODOs that came out of writing this section:
- [ ] Ensure all assertion tests in the code are using the above convention
- [ ] Read through approvals to make sure each test is English
- [ ] Figure out if it's possible to label approved cases more intelligently... or is it valuable? <-- Working on this one
- [ ] Examine legibility of parameterized tests
- [ ] Write a covering test for the header row
- [ ] Edit the paragraph on why my first approach was wrong

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
