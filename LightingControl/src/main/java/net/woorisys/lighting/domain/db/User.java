package net.woorisys.lighting.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import net.woorisys.lighting.domain.Domain;

/**
 * 유저 관리 테이블 도메인(모바일 로그인 시 사용)
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_user")
@Data
public class User implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 20)
	private String userId;
	
	@Column(nullable = false, length = 20)
	private String password;
	
	/** 도시 */
	@OneToOne
    @JoinColumn(name = "city_id")
	private City city;
	
	/** 단지 */
	@OneToOne
    @JoinColumn(name = "apartment_id")
	private Apartment apartment;
	
	@Transient
	private int cityId;
	
	@Transient
	private long apartmentId;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
}
