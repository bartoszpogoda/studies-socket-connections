package helper;

import exception.MessageNotCorrectException;
import helper.impl.DecodedMessage;

public interface MessageDecoder {
	/**
	 * @param message
	 *            accepted Strings "S:{interval},{host},{port}\n" "P:\n" and "K:\n"
	 */
	DecodedMessage decode(String message) throws MessageNotCorrectException;
}
