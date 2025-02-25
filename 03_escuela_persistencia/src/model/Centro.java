package model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "centros")
public class Centro {
	@EmbeddedId
	private CentrosPk id;
	private int capacidad;
	private String categoria;
}
