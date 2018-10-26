package com.mm.common.battle;

/**
 * 帧 时间的直接的转化
 * @author myb
 *
 */
public class FrameUtil {
	/**每秒25帧*/
	private final static int FRAME_PER_SECOND = 40;
	
	/**
	 * 把时间转换成帧数
	 * @param frame
	 * @return
	 */
	public static int frameToTime(int frame, float battleSpeed) {
		return (int) (frame * (FRAME_PER_SECOND / battleSpeed));
	}
	/**
	 * 把帧数转换成时间
	 * @param millisecond
	 * @return
	 */
	public static int timeToFrame(int millisecond, float battleSpeed) {
		return (int) (millisecond / (FRAME_PER_SECOND / battleSpeed));
	}
}
