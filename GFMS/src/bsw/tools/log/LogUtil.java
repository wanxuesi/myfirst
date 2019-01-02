package bsw.tools.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @����: ��־���ߣ������������ͼ�������ڿ���������ֱ�����õ���������־�ࡣ ����־������ʹ��JCL�ṩ��־���ܣ�Jakarta Commons
 *      Logging (JCL)�ṩ����һ����־(Log) �ӿ�(interface)��ͬʱ����������Ͳ������ھ������־ʵ�ֹ��ߡ�
 *      ���ṩ���м��/��־���� ������һ���򵥵���־��������������򿪷���Աʹ�ò�ͬ�ľ�����־ʵ�ֹ��ߡ��û����ٶ���
 *      ��Ϥĳ����־ʵ�ֹ��ߵĸ��߼����ϸ�ڡ�JCL�ṩ�Ľӿڣ�������һЩ��־���ߣ�����Log4J, Avalon LogKit, and JDK
 *      1.4�ȣ������˼򵥵İ�װ���˽ӿڸ��ӽ���Log4J��LogKit��ʵ��.
 *      JCL�����������ĳ����ࣺLog(������¼��)��LogFactory(���𴴽�Logʵ��)����commons-logging.jar
 *      �����뵽CLASSPATH֮�������Ŀ��ܺ���ز²���ϲ������־���ߣ�Ȼ������������ã�
 *      �û���������Ҫ���κ����á�Ĭ�ϵ�LogFactory�ǰ������еĲ���ȥ���ֲ������Ǹ���־���߽���ʹ��
 *      �ģ�����˳��Ѱ�ҹ��̻����ҵ���һ������ʱ��ֹ��:
 *      1.Ѱ�ҵ�ǰfactory������org.apache.commons.logging.Log�������Ե�ֵ
 *      2.Ѱ��ϵͳ������������org.apache.commons.logging.Log��ֵ
 *      3.���Ӧ�ó����classpath����log4j,��ʹ����صİ�װ(wrapper)��(Log4JLogger)
 *      4.���Ӧ�ó���������jdk1.4��ϵͳ�У�ʹ����صİ�װ��(Jdk14Logger) 5.ʹ�ü�����־��װ��(SimpleLog)
 *      fatal�ǳ����صĴ��󣬵���ϵͳ��ֹ������������Ϣ��������ʾ��״̬����̨�ϡ�
 *      error���������ڴ������Ԥ�ڵ�����������������Ϣ��������ʾ��״̬����̨�ϡ� warnʹ���˲��޳�ʹ�õ�API���ǳ�׾��ʹ��API,
 *      '��������'����, ��������ʱ������Ҫ�Ͳ���Ԥ�ڵ�״̬����û��Ҫ��Ϊ "����"������������Ϣ��������ʾ��״̬����̨�ϡ�
 *      info����ʱ��������������¼�������������Ϣ��������ʾ��״̬����̨�ϡ� debugϵͳ�����е�ϸ����Ϣ������������Ϣ����д��log�ļ��С�
 *      trace����ϸ�ڵ���Ϣ������������Ϣ����д��log�ļ��С�
 * 
 * ע�⣬��JCL��LogFactory��ʵ�����Ѿ�ʹ�û���������Դ˴��ڻ�ȡ�ض���logʵ��ʱ����Ҫ����ӻ������
 *
 * @��λ: ������ѧԺ
 * @����: wanxuesi@163.com
 * @����: ��ѧ˼ 
 */
public class LogUtil {

	/**
	 * �������󣬵���ϵͳ��ֹ��
	 * 
	 * @param logName
	 *            ���õ���־ģ������
	 * @param message
	 *            ��־��Ϣ
	 * @throws gsf_tool_log_LogUtil_fatal1_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void fatal(String logName, Object message) {
		Log log = LogFactory.getLog(logName);
		log.fatal(message);
	}

	/**
	 * �������󣬵���ϵͳ��ֹ��
	 * 
	 * @param logName
	 *            ���õ���־ģ������
	 * @param message
	 *            ��־��Ϣ ��param t ϵͳ�������
	 * @throws gsf_tool_log_LogUtil_fatal2_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void fatal(String logName, Object message, Throwable t) {
		Log log = LogFactory.getLog(logName);
		log.fatal(message,t);
	}

	/**
	 * ��¼��ͨ�߼����󣬵��ǲ��ᵼ��ϵͳ��ֹ��
	 * 
	 * @param logName
	 *            ��־ʵ����
	 * @param message
	 *            ��¼����־��Ϣ����
	 * @throws gsf_tool_log_LogUtil_error1_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void error(String logName, Object message) {
		Log log = LogFactory.getLog(logName);
		log.error(message);
	}

	/**
	 * ��¼��ͨ�߼����󣬲��ᵼ��ϵͳ��ֹ
	 * 
	 * @param logName
	 *            ��־ʵ����
	 * @param message
	 *            ��־��Ϣ
	 * @param t
	 *            �������
	 * @throws gsf_tool_log_LogUtil_error2_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void error(String logName, Object message, Throwable t) {
		Log log = LogFactory.getLog(logName);
		log.error(message,t);
	}

	/**
	 * ��¼������Ϣ
	 * 
	 * @param logName
	 *            ��־ʵ����
	 * @param message
	 *            ��־��Ϣ
	 * @throws gsf_tool_log_LogUtil_warn1_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void warn(String logName, Object message) {
		Log log = LogFactory.getLog(logName);
		log.warn(message);

	}

	/**
	 * ��¼������Ϣ��
	 * 
	 * @param logName
	 *            ��־ʵ����
	 * @param message
	 *            ��־��Ϣ
	 * @param t
	 *            ����ʵ��
	 * @throws gsf_tool_log_LogUtil_warn2_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void warn(String logName, Object message, Throwable t) {
		Log log = LogFactory.getLog(logName);
		log.warn(message,t);

	}

	/**
	 * ��¼��ʾ��Ϣ
	 * 
	 * @param logName
	 *            ��־ʵ������
	 * @param message
	 *            ��־��Ϣ
	 * @throws gsf_tool_log_LogUtil_info1_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void info(String logName, Object message) {
		Log log = LogFactory.getLog(logName);
		log.info(message);
	}

	/**
	 * ��¼��ʾ��Ϣ��
	 * 
	 * @param logName
	 *            ��־ʵ������
	 * @param message
	 *            ��־��Ϣ
	 * @param t
	 *            ���������Ϣ
	 * @throws gsf_tool_log_LogUtil_info2_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void info(String logName, Object message, Throwable t) {
		Log log = LogFactory.getLog(logName);
		log.info(message,t);
	}

	/**
	 * ��¼������Ϣ
	 * 
	 * @param logName
	 *            ��־ʵ������
	 * @param message
	 *            ��־��Ϣ
	 * @throws gsf_tool_log_LogUtil_debug1_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void debug(String logName, Object message) {
		Log log = LogFactory.getLog(logName);
		log.debug(message);
	}

	/**
	 * ��¼������Ϣ��
	 * 
	 * @param logName
	 *            ��־ʵ������
	 * @param message
	 *            ��־��Ϣ
	 * @param t
	 *            �������
	 * @throws gsf_tool_log_LogUtil_debug2_1
	 *             δ�ܻ�ȡlogName��Ӧ����־ʵ������
	 */
	public static void debug(String logName, Object message, Throwable t) {
		Log log = LogFactory.getLog(logName);
		log.debug(message,t);
	}

}
