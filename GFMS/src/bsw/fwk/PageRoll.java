package bsw.fwk;

/**
 * @描述:
 * @单位: 北软教育
 * @邮箱: majianwei@bsw.gov.cn  
 * @作者: 马剑威
 *
 */
import java.io.Serializable;
import java.util.HashMap;

public class PageRoll implements Serializable {
	/**
	 * 总记录数。
	 */
	public int totalCount = -1;

	/**
	 * 当前页，缺省值为0，即没有页。
	 */
	public int currentPage = 0;

	/**
	 * 每页记录数，缺省值为5。
	 */
	public int pageSize = 5;

	/**
	 * 总页数。
	 */
	public int pageCount = 0;

	/**
	 * 查询条件.
	 */
	public String whereClause = "";

	/**
	 * 计算记录条数的语句
	 *
	 */
	public String countClause = "";

	/**
	 /**
	 * PageRollImpl 构造子注解。
	 */
	public PageRoll() {
		super();
	}

	/**
	 * 返回当前页码.
	 * 创建日期：(2002-2-26 17:06:15)
	 * @return int
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * 获取当前查询结果终止位置。
	 * @return int 起始位置，默认从1开始。
	 */
	public int getEndPosition() {
		int startPosition = getStartPosition();
		if (pageSize > 0 && pageSize < Integer.MAX_VALUE)
			return startPosition + pageSize;
		else
			return pageSize;
	}

	/**
	 * 获取单前查询的结果总页数。
	 * @return int 总页数。
	 */
	public int getPageCount() {
		return this.pageCount;
	}

	/**
	 * 返回单页记录数
	 * @return int
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 获取当前查询结果起始位置。
	 * @return int 起始位置，默认从1开始。
	 */
	public int getStartPosition() {
		if (currentPage > 0 && pageSize > 0)
			return (this.currentPage - 1) * this.pageSize + 1;
		else
			return 1;
	}

	/**
	 * 取当前查询记录总数。
	 * 创建日期：(2002-2-26 17:44:42)
	 * @return int 当前查询记录总数。
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 返回当前查询条件.
	 * 创建日期：(2002-3-15 0:25:08)
	 * @return java.lang.String
	 */
	public java.lang.String getWhereClause() {
		return whereClause;
	}

	/**
	 * 设置单前页码。
	 * 创建日期：(2002-2-26 17:42:22)
	 * @return boolean 是否超出当前最大页数。true，超出；false，未超出。
	 * @param count int
	 */
	public boolean setCurrentPage(int count) {
		this.currentPage = count;
		return this.currentPage > this.pageCount;
	}

	/**
	 * 设置每一页可容纳的查询结果总数。
	 * 创建日期：(2002-2-26 17:44:24)
	 * @param size int 每一页容纳的记录总数。
	 */
	public void setPageSize(int size) {
		if (size == -1)
			size = Integer.MAX_VALUE;
		this.pageSize = size;
	}

	/**
	 * 此方法提供设置查询结果总数的接口。
	 * 创建日期：(2002-2-26 17:08:22)
	 * @param count int 查询结果总数。
	 * @return boolean 是否超出了当前最大页数，如果超出，应该将当前页替换为最后一页。
	 * 对于查询程序来说，合理的行为应该是重新查询最后一页的数据。
	 */
	public boolean setTotalCount(int count) {
		this.totalCount = count;

		this.pageCount = this.totalCount / this.pageSize
				+ ((this.totalCount % this.pageSize) > 0 ? 1 : 0);

		if (this.pageCount > 0 && this.currentPage < 1) {
			this.currentPage = 1;
		}
		boolean overflow = this.currentPage > this.pageCount;
		//如果当前页大于总页数，调整当前页到最后一页
		//注意：此功能需要查询的方法支持，即查询后，先设置最大页数，再根据当前页的值取结果集
		if (overflow) {
			this.currentPage = this.pageCount;
		}
		return overflow;
	}

	/**
	 * 设置当前查询条件.
	 * 创建日期：(2002-3-15 0:25:08)
	 * @param newWhereClause java.lang.String
	 */
	public void setWhereClause(java.lang.String newWhereClause) {
		whereClause = newWhereClause;
	}

	/**
	 * 得到查询记录个数的查询语句
	 * @return Returns the countClause.
	 */
	public java.lang.String getCountClause() {
		return countClause;
	}

	/**
	 * 设置查询记录个数的查询语句
	 * @param countClause The countClause to set.
	 */
	public void setCountClause(String countClause) {
		this.countClause = countClause;
	}

}
