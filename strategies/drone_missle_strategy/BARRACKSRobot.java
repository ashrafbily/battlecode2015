package drone_missle_strategy;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.Team;

public class BARRACKSRobot extends BaseRobot {




	public BARRACKSRobot(RobotController rc) throws GameActionException {
		super(rc);
	}

	@Override
	public void run() {
		try {
            int droneCount = rc.readBroadcast(DRONE_PREVIOUS_CHAN);
            int tankCount = rc.readBroadcast(TANK_PREVIOUS_CHAN);
            int basherCount = rc.readBroadcast(BASHER_PREVIOUS_CHAN);
            if (basherCount < 10 || (withinRange(basherCount, tankCount, 1, 0.3)&&withinRange(tankCount,basherCount, 1, .3)) && rc.isCoreReady() && rc.getTeamOre()>80) {
                Direction newDir =  getSpawnDirection(RobotType.BASHER);
                if (newDir != null) {
                    rc.spawn(newDir, RobotType.BASHER);
                }
            }	    		    
			rc.broadcast(BARRACKS_CURRENT_CHAN, rc.readBroadcast(BARRACKS_CURRENT_CHAN)+1);

		} catch (Exception e) {
			//                    System.out.println("caught exception before it killed us:");
			//                    System.out.println(rc.getRobot().getID());
			//e.printStackTrace();
		}
	}
}
