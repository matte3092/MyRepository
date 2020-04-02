package com.hermestrade.utility.library.util;

/**
 * The Class SummerConstants.
 */
public class LogConstants {

	/**
	 * Instantiates a new summer constants.
	 */
	private LogConstants() {
		// Nothing.
	}

	/** The Constant CONTROLLER_INFO_URL. */
	public static final String CONTROLLER_INFO_URL = "[{}][{}][URL] {}";

	/** The Constant CONTROLLER_INFO_PARAMS. */
	public static final String CONTROLLER_INFO_PARAMS = "[{}][{}][PARAM] {}";

	/** The Constant CONTROLLER_INFO_BODY. */
	public static final String CONTROLLER_INFO_BODY = "[{}][{}][BODY] {}";

	/** The Constant CONTROLLER_INFO_RETURNING_BODY. */
	public static final String CONTROLLER_INFO_RETURNING_BODY = "[{}] returning response with body {}";

	/** The Constant CONTROLLER_INFO_RESPONSE. */
	public static final String CONTROLLER_INFO_RESPONSE = "[{}][{}][RESPONSE] {}";

	/** The Constant BEFORE_BODY_WRITE_MESSAGE. */
	public static final String BEFORE_BODY_WRITE_MESSAGE = "[beforeBodyWrite]: error while parsing body: {}";

}
