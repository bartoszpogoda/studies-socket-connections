package helper;

import exception.MessageNotCorrectException;

public interface MessageDecoder {
	/**
	 * @param message
	 *            accepted Strings "S:{interval},{host},{port}" "P:" and "K:"
	 */
	DecodedMessage decode(String message) throws MessageNotCorrectException;
}
