package pl.myapp.java.problems.twoSum

import spock.lang.Specification

class SolutionSpec extends Specification {

    def "should return expected result"() {
        given:
        def solution = new Solution()
        int[] n = nums

        when:
        def result = solution.twoSum(n, target)

        then:
        result == expected

        where:
        nums                | target || expected
        [2, 7, 11, 15]     | 9      || [0, 1]
        [3, 2, 4]          | 6      || [1, 2]
        [3, 3]             | 6      || [0, 1]
    }
}
