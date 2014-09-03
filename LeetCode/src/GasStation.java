/**
 * Created by yuantian on 9/3/14.
 */
public class GasStation {
    /**
     * from the discussion
     *

     I have thought for a long time and got two ideas:

     If car starts at A and can not reach B. Any station between A and B can not reach B.(B is the first station that A can not reach.)
     If the total number of gas is bigger than the total number of cost. There must be a solution.
     (Should I prove them?)

     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        // one pass with constant variables solution
        // req: is the required gas to go through all previous stations (from 0 to i), or defiet
        // tank: is current gas in tank
        // start: the index of the starting station
        int req = 0, tank = 0, start = 0;
        for (int i = 0; i < len; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                req += -tank;
                tank = 0;
            }
        }
        // if current tank can not cover required gas, it can not go through all previous stations
        return tank >= req ? start : -1;
    }
}
