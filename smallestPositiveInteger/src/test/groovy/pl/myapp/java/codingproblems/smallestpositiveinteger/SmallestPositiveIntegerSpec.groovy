package pl.myapp.java.codingproblems.smallestpositiveinteger

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SmallestPositiveIntegerSpec extends Specification {

    @Subject
    SmallestPositiveInteger solution = new SmallestPositiveInteger()

    @Unroll
    def "should return smallest positive integer"() {
        expect:
        solution.smallestPositiveInteger(input) == output
        and:
        solution.smallestPositiveIntegerCycleSort(input) == output

        where:
        [input, output] << [
            [[1, 3, 6, 4, 1, 2] as int[], 5 ],
            [[1, 2, 3] as int[], 4],
            [[-1, -3] as int[], 1],
            [[2, -3, 4, 1, 1, 7] as int[], 3],
            [[5, 3, 2, 5, 1] as int[], 4],
            [[-8, 0, -1, -4, -3] as int[], 1],
            [[1, 2, 0] as int[], 3],
            [[3, 4, -1, 1] as int[], 2],
            [[7, 8, 9, 11, 12] as int[], 1],
            [[1] as int[], 2],
            [[-3,9,16,4,5,16,-4,9,26,2,1,19,-1,25,7,22,2,-7,14,2,5,-6,1,17,3,24,-4,17,15] as int[], 6],
            [[1,3,5,0] as int[], 2],
            [[1,2,2] as int[], 3],
            [[2,2] as int[], 1],
            [[1,1] as int[], 2]
        ]
    }
}
