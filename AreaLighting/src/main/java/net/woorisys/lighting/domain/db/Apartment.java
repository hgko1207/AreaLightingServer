package net.woorisys.lighting.domain.db;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;
import net.woorisys.lighting.domain.Domain;

/**
 * 아파트 단지 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_apartment")
@Data
@ToString(exclude = { "floors" })
public class Apartment implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 단지 명 */
	@Column(nullable = false, length = 45)
	private String name;
	
	/** 비밀번호 */
	@Column(nullable = false, length = 20)
	private String password;
	
//	@ManyToOne
//	@JoinColumn(name = "city_id")
//    @JsonIgnore
//    private City city;
	
	@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//	@Fetch(FetchMode.SUBSELECT)
	private List<Floor> floors;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	private int cityId;
}
