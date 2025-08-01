package pl.myapp.java.problems.twoSum

import spock.lang.Specification

class SolutionsSpec extends Specification {

  Solution firstSolution = new Solution()
  SolutionWithMap secondSolution = new SolutionWithMap()

  def "should return expected result"() {
    given:
    int[] n = nums

    when:
    def firstResult = firstSolution.twoSum(n, target)

    then:
    firstResult == expected

    when:
    def secondResult = secondSolution.twoSum(n, target)

    then:
    secondResult == expected

    where:
    nums           | target || expected
    [2, 7, 11, 15] | 9      || [0, 1]
    [3, 2, 4]      | 6      || [1, 2]
    [3, 3]         | 6      || [0, 1]
  }
}
