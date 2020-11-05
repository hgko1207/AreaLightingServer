package net.woorisys.lighting.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import net.woorisys.lighting.domain.Domain;

/**
 * 지하 층 관리 도메인
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_floor")
@Data
public class Floor implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 지하 층수 명 */
	@Column(nullable = false, length = 20)
	private String name;
	
	/** 채널 번호 */
	private int channel;

	@ManyToOne
	@JoinColumn(name = "apartment_id")
	@JsonIgnore
    private Apartment apartment;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
}
