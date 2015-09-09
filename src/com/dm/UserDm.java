package com.dm;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.vo.UserVO;

@Repository
public class UserDm extends HibernateDaoSupport implements BeanFactoryAware {
	private SessionFactory sessionFacotry;
	@SuppressWarnings("unused")
	private HibernateTemplate hibernateTemplate;
	
    @Resource
    public void setMySessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;   
        this.hibernateTemplate=createHibernateTemplate(sessionFacotry);
    }
    
    protected HibernateTemplate createHibernateTemplate(  
    		 SessionFactory sessionFactory) {  
    	return new HibernateTemplate(sessionFactory);  
	}
    
    @PostConstruct
    public void injectSessionFactory() {
        super.setSessionFactory(sessionFacotry);
    }
	
	@SuppressWarnings("unchecked")
	public List<UserVO> queryUser(String username) {
		String sql = "from UserVO where UserName='"+username+"'";
		List<UserVO> result = (List<UserVO>) this.getHibernateTemplate().find(sql);
		return result;
	}
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
	}
}

