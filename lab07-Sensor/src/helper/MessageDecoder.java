package helper;

import helper.impl.DecodedMessage;

public interface MessageDecoder {
	DecodedMessage decode(String message);
}
