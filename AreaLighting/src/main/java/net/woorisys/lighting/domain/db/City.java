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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;
import net.woorisys.lighting.domain.Domain;

/**
 * 특별시, 시, 도 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_city")
@Data
//@ToString(exclude = { "apartments" })
public class City implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** 지역 명 */
	@Column(nullable = false, length = 20)
	private String name;

	/** 순서 */
	private int num;

//	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SUBSELECT)
//	private List<Apartment> apartments;

	@CreationTimestamp
	private LocalDateTime createDate;

	@UpdateTimestamp
	private LocalDateTime updateDate;
}
