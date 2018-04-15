package org.zyj.shsf.webadmin.service.base.impl;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zyj.shsf.webadmin.exception.DalException;
import org.zyj.shsf.webadmin.service.base.BaseService;


/**
 * 所有 Service父类接口实现类,也是所有实现类的父类
 * 
 * 在此配置事务,应用到所有子类中
 * 
 * 
 * @version 1.0
 * 
 */

@Component("baseService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DalException.class)
public class BaseServiceImpl implements BaseService {

}
