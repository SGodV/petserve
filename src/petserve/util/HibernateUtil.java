package petserve.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sf = null;
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	public static Session getSession(){
		Session session = threadLocal.get();
		if(session != null && session.isOpen())
			return session;
		if(sf == null){
			Configuration config = new Configuration(); 
			config.configure();//读配置文件 
			sf = config.buildSessionFactory();//得到工厂 
		}
		session = sf.openSession();
		threadLocal.set(session);
		return session;
	}


}
