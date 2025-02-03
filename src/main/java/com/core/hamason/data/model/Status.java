package com.core.hamason.data.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "STATUS")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;
	
	@NotNull
	@Column(nullable=false, columnDefinition="VARCHAR(50) DEFAULT 'STATUS'")
	@Size(max=50, message="{model.data.validation.status.statusName}")
	private String statusName;
	
}
