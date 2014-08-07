package com.schmapty.guvnah.model.result;

import java.util.ArrayList;
import java.util.List;

public class ElementResultDTO implements SortedResultDTO {

	private static final long serialVersionUID = -3748783498000673088L;
	
	private ElementDTO key;
	private List<RuleDTO> value;
	
	public ElementResultDTO() {
		super();
	}
	
	public ElementResultDTO(ElementDTO element) {
		this.key = element;
		this.value = new ArrayList<RuleDTO>();
	}

	public ElementDTO getKey() {
		return key;
	}

	public void setKey(ElementDTO key) {
		this.key = key;
	}

	public List<RuleDTO> getValue() {
		return value;
	}

	public void setValue(List<RuleDTO> value) {
		this.value = value;
	}
	
	public ElementResultDTO addValue(RuleDTO dto) {
		if(this.value==null) {
			this.value = new ArrayList<RuleDTO>();
		}
		this.value.add(dto); 
		return this;
	}

	public int compareTo(ResultDTO o) {
		if(!(o instanceof ElementResultDTO)) return 0;
		ElementResultDTO e = (ElementResultDTO)o;
		if(this.key==null && e.getKey() != null) {
			return -1;
		} else {
			return this.key.compareTo(e.getKey());
		}
	}
	
}
