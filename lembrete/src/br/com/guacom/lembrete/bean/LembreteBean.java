package br.com.guacom.lembrete.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.guacom.lembrete.dao.LembreteDAO;
import br.com.guacom.lembrete.model.Lembrete;

@ManagedBean
@ViewScoped
public class LembreteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2752768787700923390L;
	private Lembrete lembrete = new Lembrete();
	private List<Lembrete> lembretes;

//	@PostConstruct
//	public void posCriacao() {
//		System.out.println("Objeto lembrete bean foi criado");
//	}

	public Lembrete getLembrete() {
		return lembrete;
	}

	public String save() {
		FacesContext context = FacesContext.getCurrentInstance();
		// Mantendo mensagens entre duas requisições.
		context.getExternalContext().getFlash().setKeepMessages(true);
		if (lembrete.getTitulo().isEmpty() || lembrete.getData() == null) {
			this.lembrete = new Lembrete("Digite seu título", "Descrição para o lembrete");
			context.addMessage("messages", new FacesMessage("Campos obrigatórios não podem ficar vázios"));
			return "cadastro?faces-redirect=true";
		}
		try (LembreteDAO dao = new LembreteDAO()) {
			dao.persist(lembrete);
		} catch (Exception e) {
			context.addMessage("messages", new FacesMessage("Erro: " + e.getMessage()));
		}
		return lembrete();
	}

	public String cadastro() {
		return "cadastro?faces-redirect=true";
	}
	
	public String lembrete() {
		return "lembretes?faces-redirect=true";
	}

	public List<Lembrete> loadReminders() {
		try (LembreteDAO dao = new LembreteDAO()) {
			if (lembretes == null) {
				lembretes = dao.findAll();
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messages", new FacesMessage("Erro: " + e.getMessage()));
		}
		return lembretes;
	}
}