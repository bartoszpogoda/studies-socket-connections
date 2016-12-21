package test;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.MessageNotCorrectException;
import helper.Action;
import helper.MessageDecoder;
import helper.impl.DecodedMessage;
import helper.impl.MessageDecoderImpl;

public class MessageDecoderImplTest {

	@Test
	public void testSetMessage() {
		MessageDecoder messageDecoder = new MessageDecoderImpl();
		DecodedMessage decodedMessage = null;
		
		try {
			decodedMessage = messageDecoder.decode("S:1232,localhost,2001");
		} catch (MessageNotCorrectException e) {
			fail();
		}
		
		assertEquals(1232, decodedMessage.getInterval());
		assertEquals("localhost", decodedMessage.getHost());
		assertEquals(2001, decodedMessage.getPort());
	}
	
	@Test
	public void testRunMessage() {
		MessageDecoder messageDecoder = new MessageDecoderImpl();
		DecodedMessage decodedMessage = null;
		
		try {
			decodedMessage = messageDecoder.decode("P:");
		} catch (MessageNotCorrectException e) {
			fail();
		}
		
		assertEquals(Action.Run, decodedMessage.getAction());
	}
	
	@Test
	public void testEndMessage() {
		MessageDecoder messageDecoder = new MessageDecoderImpl();
		DecodedMessage decodedMessage = null;
		
		try {
			decodedMessage = messageDecoder.decode("K:");
		} catch (MessageNotCorrectException e) {
			fail();
		}
		
		assertEquals(Action.End, decodedMessage.getAction());
	}

}
