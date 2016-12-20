package helper;

import dom.DecodedMessage;

public interface MessageDecoder {
	DecodedMessage decode(String message);
}
