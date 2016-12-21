package helper.impl;

import exception.MessageNotCorrectException;
import helper.Action;
import helper.MessageDecoder;

public class MessageDecoderImpl implements MessageDecoder {

	
	@Override
	public DecodedMessage decode(String message) throws MessageNotCorrectException {
		
		DecodedMessage decodedMessage = null;
		
		String[] typeAndMessage = message.split(":");
		
		if(typeAndMessage.length != 2) throw new MessageNotCorrectException();
		
		if(typeAndMessage[0].equalsIgnoreCase("s")){
			String[] parameters = typeAndMessage[1].split(",");
			
			if(parameters.length != 3) throw new MessageNotCorrectException();
			
			parameters[2] = parameters[2].replaceAll("\n", "");
			
			decodedMessage = new DecodedMessage(Action.Set);
			
			try{
				decodedMessage.setInterval(Integer.parseInt(parameters[0]));
				decodedMessage.setHost(parameters[1]);
				decodedMessage.setPort(Integer.parseInt(parameters[2]));
			} catch(NumberFormatException e){
				throw new MessageNotCorrectException();
			}
			
			
		} else if(typeAndMessage[0].equalsIgnoreCase("p")){
			if(!typeAndMessage[1].equalsIgnoreCase("\n")){
				throw new MessageNotCorrectException();
			}
			
			decodedMessage = new DecodedMessage(Action.Run);
			
		} else if(typeAndMessage[0].equalsIgnoreCase("k")){
			if(!typeAndMessage[1].equalsIgnoreCase("\n")){
				throw new MessageNotCorrectException();
			}

			decodedMessage = new DecodedMessage(Action.End);
		}
		
		return decodedMessage;
	}

}
