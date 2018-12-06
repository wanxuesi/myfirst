package bsw.fwk;

/**
 * @����:
 * @��λ: �������
 * @����: majianwei@bsw.gov.cn  
 * @����: ����
 *
 */
import java.io.Serializable;
import java.util.HashMap;

public class PageRoll implements Serializable {
	/**
	 * �ܼ�¼����
	 */
	public int totalCount = -1;

	/**
	 * ��ǰҳ��ȱʡֵΪ0����û��ҳ��
	 */
	public int currentPage = 0;

	/**
	 * ÿҳ��¼����ȱʡֵΪ5��
	 */
	public int pageSize = 5;

	/**
	 * ��ҳ����
	 */
	public int pageCount = 0;

	/**
	 * ��ѯ����.
	 */
	public String whereClause = "";

	/**
	 * �����¼���������
	 *
	 */
	public String countClause = "";

	/**
	 /**
	 * PageRollImpl ������ע�⡣
	 */
	public PageRoll() {
		super();
	}

	/**
	 * ���ص�ǰҳ��.
	 * �������ڣ�(2002-2-26 17:06:15)
	 * @return int
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * ��ȡ��ǰ��ѯ�����ֹλ�á�
	 * @return int ��ʼλ�ã�Ĭ�ϴ�1��ʼ��
	 */
	public int getEndPosition() {
		int startPosition = getStartPosition();
		if (pageSize > 0 && pageSize < Integer.MAX_VALUE)
			return startPosition + pageSize;
		else
			return pageSize;
	}

	/**
	 * ��ȡ��ǰ��ѯ�Ľ����ҳ����
	 * @return int ��ҳ����
	 */
	public int getPageCount() {
		return this.pageCount;
	}

	/**
	 * ���ص�ҳ��¼��
	 * @return int
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * ��ȡ��ǰ��ѯ�����ʼλ�á�
	 * @return int ��ʼλ�ã�Ĭ�ϴ�1��ʼ��
	 */
	public int getStartPosition() {
		if (currentPage > 0 && pageSize > 0)
			return (this.currentPage - 1) * this.pageSize + 1;
		else
			return 1;
	}

	/**
	 * ȡ��ǰ��ѯ��¼������
	 * �������ڣ�(2002-2-26 17:44:42)
	 * @return int ��ǰ��ѯ��¼������
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * ���ص�ǰ��ѯ����.
	 * �������ڣ�(2002-3-15 0:25:08)
	 * @return java.lang.String
	 */
	public java.lang.String getWhereClause() {
		return whereClause;
	}

	/**
	 * ���õ�ǰҳ�롣
	 * �������ڣ�(2002-2-26 17:42:22)
	 * @return boolean �Ƿ񳬳���ǰ���ҳ����true��������false��δ������
	 * @param count int
	 */
	public boolean setCurrentPage(int count) {
		this.currentPage = count;
		return this.currentPage > this.pageCount;
	}

	/**
	 * ����ÿһҳ�����ɵĲ�ѯ���������
	 * �������ڣ�(2002-2-26 17:44:24)
	 * @param size int ÿһҳ���ɵļ�¼������
	 */
	public void setPageSize(int size) {
		if (size == -1)
			size = Integer.MAX_VALUE;
		this.pageSize = size;
	}

	/**
	 * �˷����ṩ���ò�ѯ��������Ľӿڡ�
	 * �������ڣ�(2002-2-26 17:08:22)
	 * @param count int ��ѯ���������
	 * @return boolean �Ƿ񳬳��˵�ǰ���ҳ�������������Ӧ�ý���ǰҳ�滻Ϊ���һҳ��
	 * ���ڲ�ѯ������˵���������ΪӦ�������²�ѯ���һҳ�����ݡ�
	 */
	public boolean setTotalCount(int count) {
		this.totalCount = count;

		this.pageCount = this.totalCount / this.pageSize
				+ ((this.totalCount % this.pageSize) > 0 ? 1 : 0);

		if (this.pageCount > 0 && this.currentPage < 1) {
			this.currentPage = 1;
		}
		boolean overflow = this.currentPage > this.pageCount;
		//�����ǰҳ������ҳ����������ǰҳ�����һҳ
		//ע�⣺�˹�����Ҫ��ѯ�ķ���֧�֣�����ѯ�����������ҳ�����ٸ��ݵ�ǰҳ��ֵȡ�����
		if (overflow) {
			this.currentPage = this.pageCount;
		}
		return overflow;
	}

	/**
	 * ���õ�ǰ��ѯ����.
	 * �������ڣ�(2002-3-15 0:25:08)
	 * @param newWhereClause java.lang.String
	 */
	public void setWhereClause(java.lang.String newWhereClause) {
		whereClause = newWhereClause;
	}

	/**
	 * �õ���ѯ��¼�����Ĳ�ѯ���
	 * @return Returns the countClause.
	 */
	public java.lang.String getCountClause() {
		return countClause;
	}

	/**
	 * ���ò�ѯ��¼�����Ĳ�ѯ���
	 * @param countClause The countClause to set.
	 */
	public void setCountClause(String countClause) {
		this.countClause = countClause;
	}

}
