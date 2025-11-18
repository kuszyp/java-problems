package pl.myapp.java.codingproblems.queensattack2

import spock.lang.Rollup
import spock.lang.Specification

class QueensAttack2Spec extends Specification {
    QueensAttack2 solution = new QueensAttack2();

    @Rollup
    def "should calculate how many squares queen can attach"() {
        expect:
        solution.queensAttack(n, k, rq, cq, obstacles) == result

        where:
        [n, k, rq, cq, obstacles, result] << [
                [8, 1, 4, 4, [[3, 5]], 24],
                [1, 0, 1, 1, [], 0],
                [4, 0, 4, 4, [], 9],
                [5, 3, 4, 3, [[5, 5], [4, 2], [2, 3]], 10],
                [100000, 0, 4187, 5068, [], 308369]
        ]
    }

    @Rollup
    def "should calculate key properly"() {
        expect:
        solution.key(i, j, n) == result

        where:
        [i, j, n, result] << [
                [3, 3, 8, 27],
                [4, 4, 8, 36],
                [0, 3115, 10000, 3115],
                [4, 5, 100, 405],
                [5, 4, 1000, 5004]
        ]
    }
}
