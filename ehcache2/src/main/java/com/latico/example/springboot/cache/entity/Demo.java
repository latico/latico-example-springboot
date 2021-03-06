package com.latico.example.springboot.cache.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 11:55:39
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demo")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Demo implements Serializable {

	@Column(name = "auto_id", insertable = false, updatable = false)
	private Integer autoId;

	@Id
	private String id;

	@Column(name = "task_id")
	private Integer taskId;

	@Column(name = "exec_type")
	private String execType;

	@Column(name = "exec_status")
	private Integer execStatus;

	@Column(name = "status_descr")
	private String statusDescr;

	@Column(name = "call_param")
	private String callParam;

	@Column(name = "call_client_id")
	private String callClientId;

	@Column(name = "collect_server_id")
	private String collectServerId;

	@Column(name = "handle_server_id")
	private String handleServerId;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

	@Column(name = "handle_status")
	private Integer handleStatus;

	@Column(name = "handle_descr")
	private String handleDescr;

	@Column(name = "handle_start_time")
	private java.util.Date handleStartTime;

	@Column(name = "handle_end_time")
	private java.util.Date handleEndTime;

}
