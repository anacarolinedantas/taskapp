package br.com.esig.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.util.LangUtils;

import br.com.esig.model.Tarefa;

@Named
@SessionScoped
public class TarefaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tarefa tarefa;
	
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
			
	public boolean globalFilterFunction(String filter) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        System.out.println(filterText);
        
        return tarefa.getTitulo().toLowerCase().contains(filterText)
                || tarefa.getDescricao().toLowerCase().contains(filterText)
                || tarefa.getResponsavel().toLowerCase().contains(filterText)
                || tarefa.getPrioridade().toLowerCase().contains(filterText)
                || tarefa.getDeadline().toLowerCase().contains(filterText)
                || tarefa.getStatus().toLowerCase().contains(filterText);
    }
	
	public String adicionarTarefa() {
		tarefa.setStatus("Em Andamento");
		tarefas.add(tarefa);
		System.out.println("Tarefa " + tarefa.getTitulo() + " cadastrada com sucesso! Status: " + tarefa.getStatus());
		clean();
		return null;
	}
	
	public String excluirTarefa(Tarefa deletedTarefa) {
		tarefas.remove(deletedTarefa);
		System.out.println("Tarefa " + deletedTarefa.getTitulo() + " removida com sucesso!");
		return null;
	}
	
	public String concluirTarefa(Tarefa tarefaConcluida) {
		tarefaConcluida.setStatus("Concluída");
		System.out.println("Tarefa " + tarefaConcluida.getTitulo() + tarefaConcluida.getStatus());
		return null;
	}
		
	private void clean() {
		this.tarefa = new Tarefa();
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	
}