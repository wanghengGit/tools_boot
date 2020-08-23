package com.kit.vo;

import com.mcep.commons.pojo.BaseEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name="demo")
public class DemoEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date date;
	private Double value;
	
	private String filename;
	private String orgname;
	private Date filelastupdate;

}
