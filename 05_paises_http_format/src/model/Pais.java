package model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pais {
	private String name;
	@SerializedName("region")
	private String continente;
	private String capital;
	@SerializedName("population")
	private long habitantes;
	@SerializedName("alpha2Code")
	private String formatPais;

}
