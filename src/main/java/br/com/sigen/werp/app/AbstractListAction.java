package br.com.sigen.werp.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class AbstractListAction<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();

	@Inject
	private Conversation conversation;

	private LazyDataModel<T> instances;

	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	@SuppressWarnings("serial")
	@PostConstruct
	public void init() {
		this.instances = new LazyDataModel<T>() {
			@Override
			public List<T> load(int pageIndex, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				try {
					instances.setRowCount(getTotalRegistros());
					return carregarRegistrosParaExibicao(pageIndex, pageSize);
				} catch (Exception ex) {
					ex.printStackTrace();
					String errorMessage = "Erro ao carregar dados ";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMessage, errorMessage));
					return null;
				}
			}
		};

		createDynamicColumns();

	}

	public LazyDataModel<T> getInstances() {
		return instances;
	}

	public void setInstances(LazyDataModel<T> instances) {
		this.instances = instances;
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public abstract void delete(Object object);

	protected abstract int getTotalRegistros();

	protected abstract List<T> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize);

	protected abstract Map<String, String> getColumnsMap();

	private void createDynamicColumns() {
		Map<String, String> columnsMap = getColumnsMap();
		for (String columnId : columnsMap.keySet()) {
			columns.add(new ColumnModel(columnsMap.get(columnId), columnId));
		}
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	static public class ColumnModel implements Serializable {

		private static final long serialVersionUID = 1L;

		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

}
