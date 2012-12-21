package jp.co.geo.logviewer.model;

final public class LogItemType{
	private int index;
	private String name;
	private int original;
	private String description;
	
	private LogItemType(String name, int original, int index, String description) {
		this.index = index;
		this.name = name;
		this.original = original;
		this.description = description;
	}
	
	/**
	 * ����
	 */
	public static LogItemType TIME = new LogItemType("TIME", 0, 3, "����");
	
	/**
	 * HTTP�X�e�[�^�X�R�[�h
	 */
	public static LogItemType STATUS = new LogItemType("STATUS", 1, 5, "�X�e�[�^�X");
	
	/**
	 * ���O���x��
	 */
	public static LogItemType LOGLEVEL = new LogItemType("LOGLEVEL", 2, 3, "���O���x��");

	/**
	 * HTTP�̃��N�G�X�gURL
	 */
	public static LogItemType URL = new LogItemType("URL", 3, 4, "URL");

	/**
	 * �T�C�Y
	 */
	public static LogItemType SIZE = new LogItemType("SIZE", 4, 5, "�T�C�Y");
	
	/**
	 * ��������
	 */
	public static LogItemType PROCESSING_TIME = new LogItemType("PROCESSING_TIME", 5, 6, "��������");
	
	/**
	 * �\�̗�ԍ��ƂȂ� Index ��ݒ肷��
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * �\�̗񖼂ƂȂ镶�����ݒ肷��
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Index��Ԃ�
	 * @return
	 */
	public int values() {
		return index;
	}
	
	/**
	 * �\�ŕ\��������ԍ�
	 * @return
	 */
	public int index() {
		return index;
	}
	
	/**
	 * ��
	 * @return
	 */
	public String description() {
		return description;
	}
	
	/**
	 * �^�C�v�������̂܂ܕ�����ŕԂ�
	 */
	public String toString() {
		return name;
	}
	
	public static LogItemType getLogItemType(String type) {
		if(type.equals(TIME.toString())) {
			return TIME;
		} else if(type.equals(STATUS.toString())) {
			return STATUS;
		} else if(type.equals(LOGLEVEL.toString())) {
			return LOGLEVEL;
		} else if(type.equals(URL.toString())) {
			return URL;
		} else if(type.equals(PROCESSING_TIME.toString())){
			return PROCESSING_TIME;
		} else if(type.equals(SIZE.toString())){
			return SIZE;
		}else {
			return null;
		}
	}
}
