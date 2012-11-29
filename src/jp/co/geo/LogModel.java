package jp.co.geo;

import java.text.MessageFormat;
import java.text.ParseException;

public class LogModel {
	
    MessageFormat format;
	
    Object[] message;
    
	public LogModel() {
        // �A�N�Z�X���O�̃t�H�[�}�b�g
        format = new MessageFormat("{0} {1} {2} [{3}] \"{4}\" {5} {6}");
	}
	
	
	public Object[] analyze(StringBuffer buffer) {
		return analyze(buffer.toString());
	}
	
	
	/**
	 * ���O�̃t�H�[�}�b�g�ɕϊ����ăI�u�W�F�N�g�̔z����쐬����
	 * @param str
	 * @return
	 */
	public Object[] analyze(String str) {
		try {
			message = format.parse(str);
			return message;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * HTTP�X�e�[�^�X�R�[�h���擾����
	 * @return
	 */
	public String getHttpStatusCode() {
		return (String) message[5];
	}
}
