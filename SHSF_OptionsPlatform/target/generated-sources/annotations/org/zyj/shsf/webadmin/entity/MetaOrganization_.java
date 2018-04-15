package org.zyj.shsf.webadmin.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MetaOrganization.class)
public abstract class MetaOrganization_ {

	public static volatile SingularAttribute<MetaOrganization, Integer> orgId;
	public static volatile CollectionAttribute<MetaOrganization, MetaSystem> system;
	public static volatile SingularAttribute<MetaOrganization, String> orgName;

}

