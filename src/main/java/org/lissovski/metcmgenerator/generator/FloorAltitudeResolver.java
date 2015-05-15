package org.lissovski.metcmgenerator.generator;


/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class FloorAltitudeResolver {
	Integer[] heights = { 
		100, 350, 750, 1250, 1750, 2500, 3500, 4500, 5500, 7000, 9000, 11000, 13000, 15000,
		17000, 19000, 21000, 23000, 25000, 27000, 29000, 31000, 33000, 35000, 37000, 39000
	};

	/**
	 * @throws IllegalArgumentException  When negative or a floor higher than 26 is given
	 * 
	 * @param floor
	 * 
	 * @return Integer
	 */
	public Integer resolve(Integer floor) {
		if (floor < 1 || floor > (heights.length-1)) {
			throw new IllegalArgumentException("Invalid floor given - " + floor);
		}
		
		return heights[floor-1];
	}
}
