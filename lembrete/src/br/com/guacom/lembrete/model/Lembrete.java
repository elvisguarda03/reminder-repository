package br.com.guacom.lembrete.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lembrete implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5527064419229196010L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private String hora;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	public Lembrete(String titulo, String descricao, String hora, Date data) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
	}
	
	public Lembrete(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Lembrete(String titulo, String hora, Date data) {
		this.titulo = titulo;
		this.hora = hora;
		this.data = data;
	}
	
	public Lembrete(String titulo, Date data) {
		this.titulo = titulo;
		this.data = data;
	}

	@Deprecated
	public Lembrete() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("O campo título está vázio!");
		this.titulo = titulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lembrete other = (Lembrete) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		if(hora == null)
			throw new IllegalArgumentException("O campo de hora está vázio!");
		this.hora = hora;
	}
	
	public String formatHour() {
		return (String) hora.subSequence(11, 16);
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String dataToString() {
		return data.toString();
	}

	public String formatDate() {
		String date = data.toString();
		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String d = out.format(in.parse(date));
			return d.replace("00", "20");
		} catch (ParseException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		if (data == null)
			throw new IllegalArgumentException("O campo de data está vázio!");
		this.data = data;
	}
}