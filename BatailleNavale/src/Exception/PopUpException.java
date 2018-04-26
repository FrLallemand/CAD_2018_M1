package Exception;

import javax.swing.JOptionPane;

public class PopUpException extends Exception {

	public PopUpException(String message){
		super(message);
	    Object[] options = {"OK"};
	    int n = JOptionPane.showOptionDialog(null,
	                   message,"Erreur",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   options,
	                   options[0]);
	}
}
