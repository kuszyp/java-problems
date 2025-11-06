package pl.myapp.java.problems.queensAttack2

import spock.lang.Specification

class QueensAttack2Spec extends Specification {
    QueensAttack2 solution = new QueensAttack2();

    def "should calculate how many squares queen can attach"() {
        expect:
        solution.queensAttack(n, k, rq, cq, obstacles) == result

        where:
        [n, k, rq, cq, obstacles, result] << [
//                [8, 1, 4, 4, [[3, 5]], 24],
//                [1, 0, 1, 1, [], 0],
//                [4, 0, 4, 4, [], 9],
//                [5, 3, 4, 3, [[5, 5], [4, 2], [2, 3]], 10],
                [100000, 0, 4187, 5068, [], 308369]
        ]
    }
}
